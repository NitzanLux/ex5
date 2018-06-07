package DirectoryProcessor;

import DirectoryProcessor.SecssionProcessor.CurrentSecssion;
import DirectoryProcessor.SecssionProcessor.SecessionCreationException;

import java.util.ArrayList;
import java.lang.*;

/**
 *
 */
public class FileAnalyzer {
    /*--constants--*/
    private final static String FILTER = "FILTER";
    private final static String ORDER  = "ORDER";
    private static final String TYPE_I_ERROR_MSG_STR_FORMAT = "Warning in line %d";
    private final int JUMP_TO_ORDER = 2;
    private static final String DEFAULT_SORTER = "abs";

    /* This method goes through the array list that contains the file data, First,
    * it looks for type 2 exceptions, if found,
    * it throws exceptions. If there are no type 2 exceptions, it goes over the file, and for each section
    *it filters and orders the files
    */
    void analyzeStringList(ArrayList<String> fileData) throws TypeTwoExceptions.BadFilterSectionName,
            TypeTwoExceptions.BadOrderSectionName {

        checkTypeTwoErrors(fileData); // Checks if there are type 2 errors in the file
        String line = "";
        String filterValue = "";
        String orderValue = "";
        String nextLine = "";
        int filterLine=0;
        int orderLine=0;
        // This loop goes over the array list and filters and sorters files by each section
        for (int i = 0; i < fileData.size(); ) {
            line = fileData.get(i);
            // Checks if the current line equals FILTER, if yes, will save the filter value
            if (line.equals(FILTER)) {
                filterValue = fileData.get(i+1);
                filterLine=i+1;
                i += JUMP_TO_ORDER;
                line = fileData.get(i);
            }
            // Checks if the current line equals ORDER, if yes, will save the order value
            if (line.equals(ORDER)) {
                //if (fileData.get(i + INCREASE_ONE) != null) {
                int j = i+1;
                if(j < fileData.size()){
                    nextLine = fileData.get(i + 1);
                    if (nextLine.equals(FILTER)) {
                        orderValue = DEFAULT_SORTER;
                        i ++;
                    } else {
                        orderValue = nextLine;
                        orderLine=i+1;
                        i += JUMP_TO_ORDER;
                    }
                } else {
                    orderValue = DEFAULT_SORTER;
                }
            }
            try {
                CurrentSecssion.getInstance().setFilterAndSorter(filterValue, orderValue);
            } catch (SecessionCreationException.FilterCreationException e) {
                System.err.printf(TYPE_I_ERROR_MSG_STR_FORMAT, filterLine);
            }catch (SecessionCreationException.SorterCreationException e) {
                System.err.printf(TYPE_I_ERROR_MSG_STR_FORMAT, orderLine);
            }
            String[] outputData = CurrentSecssion.getInstance().getCurrentSessionOutput();
            for (int j = 0; j <outputData.length ; j++) {
                System.out.println(outputData[j]);
            }
        }
    }

    // private method to print the names of the filtered and sorted files
    private void printFiles(String[] filesToPrint){
        for (int i = 0; i< filesToPrint.length; i++){
            System.out.println(filesToPrint[i]);
        }
    }


     private void checkTypeTwoErrors(ArrayList<String> fileData) throws TypeTwoExceptions.BadFilterSectionName,
                                                                TypeTwoExceptions.BadOrderSectionName {
            // This loop iterates through the array, for each section checks if in the section exists type 2 errors
            for (int i = 0; i < fileData.size(); i++) {

                i = checkFilter(fileData, i);
                i = checkOrder(fileData, i);
            }
     } // end of checkTypeTwoErrors method




    private int checkFilter(ArrayList<String> fileData, int lineNumber) throws TypeTwoExceptions.BadFilterSectionName,
            TypeTwoExceptions.BadOrderSectionName {
        boolean isFilterHeadLine=false;
        boolean isFilterValue=false;
        for (int i = 0; i <3; i++) {
            if(fileData.get(lineNumber).equals(FILTER)&&!isFilterHeadLine){
                isFilterHeadLine=true;
                lineNumber++;
            }else if (isFilterHeadLine&&!isFilterValue&&!(fileData.get(lineNumber).equals(ORDER))){
                //current filterValue;
                isFilterValue=true;
                lineNumber++;
            }else if (isFilterHeadLine&&isFilterValue&&fileData.get(lineNumber).equals(ORDER)){
                return lineNumber;
            }
        }//if the
        if (fileData.get(lineNumber).toUpperCase().equals(ORDER)||fileData.get(lineNumber).
                toUpperCase().equals(FILTER)){
            throw new  TypeTwoExceptions.BadFilterSectionName();
        }else {
            throw new TypeTwoExceptions.BadOrderSectionName();
        }
    } // end of checkFilter method

    private int checkOrder(ArrayList<String> fileData, int lineNumber) throws TypeTwoExceptions.BadOrderSectionName,
            TypeTwoExceptions.BadFilterSectionName {

        boolean isSortHeadLine=false;
        boolean isSortValue=false;
        for (int i = 0; i <3; i++) {
            if (!isSortHeadLine&&fileData.get(lineNumber).equals(ORDER)){
                isSortHeadLine=true;
                lineNumber++;
            }else if (!fileData.get(lineNumber).equals(ORDER)){
                if (fileData.get(lineNumber).equals(FILTER)){
                    return lineNumber;
                }else if (!isSortValue){
                    lineNumber++;
                    isSortValue=true;
                }
            }
        }
        if (fileData.get(lineNumber).toUpperCase().equals(ORDER)||fileData.get(lineNumber).
                toUpperCase().equals(FILTER)){
            throw new  TypeTwoExceptions.BadFilterSectionName();
        }else {
            throw new TypeTwoExceptions.BadOrderSectionName();
        }
        }
}
