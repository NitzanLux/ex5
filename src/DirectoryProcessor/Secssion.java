package DirectoryProcessor;

import java.io.File;
import java.io.FileFilter;

public interface Secssion {
    public class Filter{}

    boolean isFilterd(FileFacade file);
    int getValueForSorting(FileFacade file);
}

