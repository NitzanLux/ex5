package DirectoryProcessor.SecssionProcessor;

import java.io.File;
import java.io.FileFilter;

/*
 * singlton of filterFactory, genrate an FileFilter for sorting file.
 */
public class FilterFactory {
    private static FilterFactory instance = new FilterFactory();

    private FilterFactory() {
    }

    static FilterFactory getInstance() {
        return instance;
    }

    private enum MathComperison {
        GREATER_THAN() {
            boolean filterd(double fileSize, double threshold) {
                return fileSize > threshold;
            }
        },
        SMALLER_THAN() {
            boolean filterd(double fileSize, double threshold) {
                return fileSize < threshold;
            }
        };

        abstract boolean filterd(double fileSize, double threshold);

        boolean isFilterd(File file, double threshold) {
            return filterd(file.length()/1024, threshold);
        }
        /**
         * override the enum metod return the enum name with lowercase.
         * @return enum name with lower case .
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

    }


    private enum NameComperison {
        FILE() {
            boolean isFilterd(File file, String parameter) {
                return file.getName().equals(parameter);
            }
        },
        CONTAINS() {
            boolean isFilterd(File file, String parameter) {
                return file.getName().contains(parameter);
            }
        },
        PREFIX() {
            boolean isFilterd(File file, String parameter) {
                return prefixEquals(file, parameter, true);
            }
        },
        SUFFIX() {
            boolean isFilterd(File file, String parameter) {
                return prefixEquals(file, parameter, false);
            }
        };

        abstract boolean isFilterd(File file, String parameter);
        /**
         * override the enum metod return the enum name with lowercase.
         * @return enum name with lower case .
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        static private boolean prefixEquals(File file, String parameter, boolean fromStart) {
            String fileName = file.getName();
            if (!fromStart) {
                fileName = reversString(fileName);
                parameter = reversString(parameter);
            }
            for (int i = 0; i < parameter.length(); i++) {
                if (parameter.charAt(i) != fileName.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        static private String reversString(String value) {
            StringBuilder stringBuilder = new StringBuilder(value);
            return stringBuilder.reverse().toString();
        }
    }

    private enum FileStateComperison {
        WRITABLE() {
            boolean isFilterd(File file) {
                return file.canWrite();
            }
        },
        EXECUTABLE() {
            boolean isFilterd(File file) {
                return file.canExecute();
            }
        },
        HIDDEN() {
            boolean isFilterd(File file) {
                return file.isHidden();
            }
        };

        abstract boolean isFilterd(File file);
        /**
         * override the enum metod return the enum name with lowercase.
         * @return enum name with lower case .
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }


    /*
     * get filter using a threshold.
     * @param filterName name of the filter.
     * @param threshold the threshold.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, double threshold,boolean isNot) {
        MathComperison mathFilter = null;
        for (MathComperison filter : MathComperison.values()) {
            if (filterName.equals(filter.toString())) {
                mathFilter = filter;
            }
        }
        if (mathFilter == null) {
            return null;
        } else {
            final MathComperison mathComperisonFilter = mathFilter;
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return mathComperisonFilter.isFilterd(pathname, threshold)!=isNot;
                }
            };
        }
    }//todo lambda

    /*
     * get filter from between filter.
     * @param filterName name of the filter.
     * @param threshold the max threshold.
     * @param minBar the minimum threshold.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, double threshold, double minBar,boolean isNot) {
        if (!filterName.equals("between")||minBar>threshold) {
            return null;
        }
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (MathComperison.GREATER_THAN.isFilterd(pathname, minBar) &&
                        MathComperison.SMALLER_THAN.isFilterd(pathname, threshold))!=isNot;
            }
        };
    }//todo megic number lambda

    /*
     * get filter using search key .
     * @param filterName the filter name.
     * @param searchValue a String key for searching in the file name.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, String searchValue,boolean isNot) {
        NameComperison nameComperisonFilter=null;
        for (NameComperison currentValue:NameComperison.values()){
            if (filterName.equals(currentValue.toString())){
                nameComperisonFilter=currentValue;
            }
        }
        if (nameComperisonFilter==null){
            return null;
        }else {
            final NameComperison nameComperison = nameComperisonFilter;
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return nameComperison.isFilterd(pathname,searchValue)!=isNot;
                }
            };
        }
    }//todo lambda

    /*
     * get filter based on file State.
     * @param filterName the filter name.
     * @param isUphold is the uphold the current demend.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, boolean isUphold,boolean isNot){
        FileStateComperison fileStateComperisonFilter=null;
        for (FileStateComperison currntFilter:FileStateComperison.values()) {
            if (filterName.equals(currntFilter.toString())){
                fileStateComperisonFilter=currntFilter;
            }
        }
        if (fileStateComperisonFilter==null){
            return null;
        }else {
            final FileStateComperison fileStateComperison=fileStateComperisonFilter;
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    boolean answe = fileStateComperison.isFilterd(pathname);
                    return answe==isUphold&& answe!=isNot;
                }
            };
        }
    }

    /*
     *get filter based on none critiryon (all filter).
     * @param filterName the filter name.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName,boolean isNot){
        if (filterName.equals("all")) {//todo megic num
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {//todo lambda
                    return !isNot;
                }
            } ;
            }
            return null;
        }
    public FileFilter getAllFilter(){
        return getFilter("all",false);//todo megic numb
    }
}
