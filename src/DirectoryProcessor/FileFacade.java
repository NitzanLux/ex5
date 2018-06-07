package DirectoryProcessor;
import java.io.File;
import java.io.FileFilter;

/**
 * an abstract class which is a facade of java.io.File.
 */
public class FileFacade  {

    private static final int NO_DOT_IN_FILE = -1;
    private File file;

    /**
     * creates a new FileFacade instance
     * @param s - the path of the file to create
     */
    public FileFacade(String s) {
        file=new File(s);
    }

    /*
     * creates a new FileFacade instance by a File object that it gets
     * a private method that is used only in this class.
     */
    private FileFacade(File file){
        this.file=file;
    }

    /**
     * Returns the name of the file
     * @return
     */
    public String getName() {
        return file.getName();
    }

    /**
     *
     * @return
     */
    public long length() {
        return file.length();
    }

    public boolean isFile() {
        return file.isFile();
    }
    public boolean isDirectory(){
        return file.isDirectory();
    }

    public FileFacade[] listFiles(FileFilter fileFilter){
        File[] listFiles=file.listFiles(fileFilter);
        FileFacade[] listFilesFacade;
        if (listFiles==null){//if this abstract pathname does not denote a directory, or if an I/O error occurs.
            return null;
        }
        listFilesFacade=new FileFacade[listFiles.length];
        for (int i = 0; i <listFiles.length ; i++) {
            listFilesFacade[i]=new FileFacade(listFiles[i]);
            }
        return listFilesFacade;
    }

    public String getAbsolutePath(){
        return file.getAbsolutePath();
    }

    /**
     * get the fileType by last dot.
     * @return fileType
     */
    public String getType(){
       String fileType=null;
        int lastDot=file.getName().lastIndexOf(".");//todo megic number
        String fileName=file.getName();
        if (lastDot!= NO_DOT_IN_FILE){
        fileType=fileName.substring(++lastDot);//TODO megic number
       }
        return fileType;
    }
}
