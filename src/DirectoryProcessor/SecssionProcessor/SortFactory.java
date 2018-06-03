package DirectoryProcessor.SecssionProcessor;

import DirectoryProcessor.FileFacade;

import java.util.Comparator;

class SortFactory {
    private static SortFactory instance = new SortFactory();

    private SortFactory() {
    }
    public static SortFactory getInstance() {
        return instance;
    }
    /*
        *  enum of all kind of sort.
         */
    private enum Sort {
        ABS() {
            int compareIt(FileFacade currentFile, FileFacade fileToComper) {
                return currentFile.getAbsolutePath().compareTo(fileToComper.getAbsolutePath());
            }
        },
        TYPE() {
            int compareIt(FileFacade currentFile, FileFacade fileToComper) {
                int toReturn= currentFile.getType().compareTo(currentFile.getType());
                if (toReturn==0) {//todo megic number
                    toReturn=ABS.compareIt(currentFile,fileToComper);
                }
                    return toReturn;
            }
        },
        SIZE() {
            int compareIt(FileFacade currentFile, FileFacade fileToComper) {
                int toReturn=(int) Math.signum(currentFile.length() - fileToComper.length());
                if (toReturn==0) {//todo megic number
                    toReturn=ABS.compareIt(currentFile,fileToComper);
                }
                return toReturn;
            }
        };
        /*
        * abstract method for compering 2 files the fires one is the current file and the outher one is the one to compere with
         */
        abstract int compareIt(FileFacade currentFile, FileFacade fileToComper);

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
     * genrate comperator object.
     * @param sortName the name of the sort operation.
     * @param isReversed is the sort operation is reversed.
     * @return comparator for the designed propertis.
     */
    Comparator<FileFacade> getComperator(String sortName, boolean isReversed) {
        int reversFactor = 1;//todo megic number
        if (isReversed) {
            reversFactor = -1;//todo megic number
        }
        Sort currntSorter = null;
        for (Sort sort : Sort.values()) {
            if (sortName.equals(sort.toString())) {
                currntSorter = sort;
            }
        }
        if (currntSorter == null) {
            return null;
        }
        final Sort sorter = currntSorter;
        final int revers = reversFactor;
        return new Comparator<FileFacade>() {
            @Override
            public int compare(FileFacade fileFacade, FileFacade t1) {
                return sorter.compareIt(fileFacade, t1) * revers;
            }
        };
    }
    Comparator<FileFacade> getAbsComperator(){
        return getComperator(Sort.ABS.toString(),true);
    }
}

