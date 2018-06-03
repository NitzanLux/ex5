package DirectoryProcessor.SecssionProcessor;

import DirectoryProcessor.FileFacade;

import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class CurrentSecssion {
    private static CurrentSecssion instance=new CurrentSecssion();
    private FileFacade pathName=null;
    private FileFilter currentFileFilter;
    private Comparator<FileFacade> currentSort;
    private CurrentSecssion(){
        currentSort =null;
        currentFileFilter=null;
    }
    public void setPathName(String pathName) {
        this.pathName = new FileFacade(pathName);
    }
    public String[] getCurrentSessionOutput(String filterName,String orderName){
        setFileFilter(filterName);
        setSorter(orderName);
        FileFacade[] files=pathName.listFiles(currentFileFilter);
        Arrays.sort(files,currentSort);
    }
    private void setFileFilter(String filterKey){
        if (filterKey==null){
            currentFileFilter= FilterFactory.getInstance().getAllFilter();
            //todo throw the exeption
        }else {

        }

    }
    private void setSorter(String sorterKey){
        if (sorterKey==null){
            currentSort= SortFactory.getInstance().getAbsComperator();
            //todo throw the exption
        }
    }

    public static CurrentSecssion getInstance() {
        return instance;
    }
    public void creatSecssion(String[] secssionStrings){
    }

}
