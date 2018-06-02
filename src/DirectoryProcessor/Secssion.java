package DirectoryProcessor;

import java.io.File;
import java.io.FileFilter;

public abstract class Secssion {
    FileFilter fileFilter;
    abstract int getValueForSorting(FileFacade file);
}

