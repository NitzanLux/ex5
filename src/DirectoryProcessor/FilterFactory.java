package DirectoryProcessor;

import java.io.File;
import java.io.FileFilter;

/*
 * singlton of filterFactory, genrate an FileFilter for sorting file.
 */
class FilterFactory {
    private static FilterFactory instance = new FilterFactory();

    private FilterFactory() {
    }


    private enum MathComperison {
        GREATER_THAN() {
            boolean filterd(long fileSize, long threshold) {
                return fileSize > threshold;
            }
        },
        SMALLER_THAN() {
            boolean filterd(long fileSize, long threshold) {
                return fileSize < threshold;
            }
        };

        abstract boolean filterd(long fileSize, long threshold);

        boolean isFilterd(File file, long threshold) {
            return filterd(file.length(), threshold);
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
    }

    static FilterFactory getInstance() {
        return instance;
    }

    /*
     * get filter using a threshold.
     * @param filterName name of the filter.
     * @param threshold the threshold.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, int threshold) {
        MathComperison mathFilter = null;
        for (MathComperison filter : MathComperison.values()) {
            if (filterName.equals(filter.toString().toLowerCase())) {
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
                    return mathComperisonFilter.isFilterd(pathname, threshold);
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
    FileFilter getFilter(String filterName, int threshold, int minBar) {
        if (!filterName.equals("between")) {
            return null;
        }
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return MathComperison.GREATER_THAN.isFilterd(pathname, minBar) &&
                        MathComperison.SMALLER_THAN.isFilterd(pathname, threshold);
            }
        };
    }//todo megic number lambda

    /*
     * get filter using search key .
     * @param filterName the filter name.
     * @param searchValue a String key for searching in the file name.
     * @return FileFilter instance.
     */
    FileFilter getFilter(String filterName, String searchValue) {
        NameComperison nameComperisonFilter=null;
        for (NameComperison currentValue:NameComperison.values()){
            if (filterName.equals(currentValue.toString().toLowerCase())){
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
                    return nameComperison.isFilterd(pathname,searchValue);
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
    FileFilter getFilter(String filterName, boolean isUphold){
        FileStateComperison fileStateComperisonFilter=null;
        for (FileStateComperison currantFilter:FileStateComperison.values()) {
            if (filterName.equals(currantFilter.toString().toLowerCase())){
                fileStateComperisonFilter=currantFilter;
            }
        }
        if (fileStateComperisonFilter==null){
            return null;
        }else {
            final FileStateComperison fileStateComperison=fileStateComperisonFilter;
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return fileStateComperison.isFilterd(pathname)==isUphold;
                }
            };
        }
    }
    FileFilter getFilter(String filterName){
        if (filterName.equals("all")) {//todo megic num
            return new FileFilter() {
                @Override
                public boolean accept(File pathname) {//todo lambda
                    return true;
                }
            } ;
            }
            return null;
        }
    }
