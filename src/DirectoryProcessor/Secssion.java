package DirectoryProcessor;

import java.io.File;
import java.io.FileFilter;

public abstract class Secssion implements FileFilter{
   // FileFilter fileFilter;

    abstract int getValueForSorting(FileFacade file);
}

