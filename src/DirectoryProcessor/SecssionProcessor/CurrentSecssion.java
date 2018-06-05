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
    public static CurrentSecssion getInstance() {
        return instance;
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

    private void setFileFilter(String filterKey) {
        FileFilter currentFileFilter = readFilterKey(filterKey);
        if (currentFileFilter == null){
            this.currentFileFilter=FilterFactory.getInstance().getAllFilter();
            //todo throw
            }else {
                this.currentFileFilter=currentFileFilter;
            }
        }

    /*
     * convert filter string into a FileFilter Object.
      * @param filterKey the filter requst string.
     * @return FileFilter
     */
    private FileFilter readFilterKey(String filterKey){
        String[] values=filterKey.split("#");//todo megic number
        String filterName=values[0];
        //indicative variables( if varible does not exists then it is null).
        Double firstDouble=null;
        Double secondDouble=null;
        Boolean firstBoolean=null;
        String stringToFilter=null;
        int notOperationInclude=0;
        boolean notOperation=false;
        for (int i = 1; i <values.length ; i++) {
            if (isDouble(values[i])){
                if (firstDouble==null){
                    firstDouble=Double.parseDouble(values[i]);
                }else {
                    secondDouble=Double.parseDouble(values[i]);
                }
            }else if (isBoolean(values[i])!=null){
                firstBoolean=isBoolean(values[i]);
            }else if (values[i].equals("NOT")&&!notOperation){//todo megic number.
                notOperation=true;
                notOperationInclude++;
            }else {
                stringToFilter=values[i];
            }
        }
        return getFilter(values.length-notOperationInclude, filterName,firstDouble,secondDouble,
                stringToFilter,firstBoolean, notOperation);
    }//todo make it shorter!.

    /*
     * this method return a filter by the veriabls that had been read from the filter string
     * @param currentSize the number of valus without the not operation.
     * @param filterName the filter key name
     * @param firstDouble the double arg(if exists)
     * @param secondDouble the second doubler arg(if exists , for between filter)
     * @param stringToFilter the String search key (if exists)
     * @param firstBoolean the isUphold boolean(if exists)
     * @param notOperation is the Not operation exists
     * @return FileFilter if args are valid.
     */
    private FileFilter getFilter(int currentSize,String filterName,Double firstDouble,Double secondDouble,
                                 String stringToFilter,Boolean firstBoolean,boolean notOperation){
        switch (currentSize){
            case 1:
                return FilterFactory.getInstance().getFilter(filterName,notOperation);
            case 2:
                if (firstBoolean!=null){
                    return FilterFactory.getInstance().getFilter(filterName,firstBoolean,notOperation);
                }else if (firstDouble!=null){
                    return FilterFactory.getInstance().getFilter(filterName,firstDouble,notOperation);
                }else if (stringToFilter!=null){
                    return FilterFactory.getInstance().getFilter(filterName,stringToFilter,notOperation);
                }break;
            case 3:
                if (firstDouble!=null&&secondDouble!=null){
                    return FilterFactory.getInstance().getFilter(filterName,secondDouble,firstDouble,notOperation);
                }
        }
        return null;
    }
    /*
    * return true if the string is boolean yes and false if it is no, return null if the string isnt yes/no
     */
    private Boolean isBoolean(String stringToCheck){
        if (stringToCheck.equals("NO")){//todo megic number
            return false;
        }else if (stringToCheck.equals("YES")){//todo megic number
            return true;
        }
        return null;
    }

    /*
     * determine if the corrnt string is double.
     * @param stringToCheck
     * @return true if it can be pars into double.
     */
    private boolean isDouble(String stringToCheck){
        boolean isDotExists=false;
        for (int i = 0; i <stringToCheck.length(); i++) {
            int asciiOfChar=(int)stringToCheck.charAt(i);
            if (!((asciiOfChar<=57&&asciiOfChar>=48)||(asciiOfChar==46&&!isDotExists&&i!=0 && i!=(stringToCheck.length()-1)))){//todo megic number
                return false;
            }
            if (asciiOfChar==46){//todo megic number
                isDotExists=true;
            }
        }
        return true;
    }
    private void setSorter(String sorterKey){
        if (sorterKey==null){
            currentSort= SortFactory.getInstance().getAbsComperator();
            //todo throw the exption
        }
    }
    private void readSortKey(String sorterKey){

    }

    public void creatSecssion(String[] secssionStrings){
    }

}
