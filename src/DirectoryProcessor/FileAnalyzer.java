package DirectoryProcessor;

import DirectoryProcessor.SecssionProcessor.CurrentSecssion;
import DirectoryProcessor.SecssionProcessor.SecessionCreationException;

import java.util.ArrayList;
import java.lang.*;

/**
 *
 */
public class FileAnalyzer {

    private final static String FILTER = "FILTER";
    private final static String ORDER  = "ORDER";
    private final int INCREASE_TWO = 2;
    private static final String ABS_KEY_WORD = "abs";

    // This method goes through the array list that contains the file data, Firstable, it looks for type 2 exceptions, if found,
    // it throws exceptions. If there are no type 2 exceptions, it goes over the file, and for each section it filters and orders the files
    void analyzeStringList(ArrayList<String> fileData) throws TypeTwoExceptions.BadFilterSectionName, TypeTwoExceptions.BadOrderSectionName {

        checkTypeTwoErrors(fileData); // Checks if there are type 2 errors in the file
        String line = "";
        String filterValue = "";
        String orderValue = "";
        String nextLine = "";

        // This loop goes over the array list and filters and sorters files by each section
        for (int i = 0; i < fileData.size(); ) {
            line = fileData.get(i);
            // Checks if the current line equals FILTER, if yes, will save the filter value
            if (line.compareTo(FILTER) == 0) {
                nextLine = fileData.get(i +1);
                filterValue = nextLine;
                i += INCREASE_TWO;
                line = fileData.get(i);
            }
            // Checks if the current line equals ORDER, if yes, will save the order value
            if (line.compareTo(ORDER) == 0) {
                //if (fileData.get(i + INCREASE_ONE) != null) {
                int j = i+1;
                if(j < fileData.size()){
                    nextLine = fileData.get(i + 1);
                    if (nextLine.compareTo(FILTER) == 0) {
                        orderValue = ABS_KEY_WORD;
                        i ++;
                    } else {
                        orderValue = nextLine;
                        i += INCREASE_TWO;
                    }
                } else {
                    orderValue = ABS_KEY_WORD;
                }
            }

            System.out.println("current: " + i + filterValue + " " + orderValue);

            try {
                CurrentSecssion.getInstance().setFilterAndSorter(filterValue, orderValue);
            } catch (SecessionCreationException.FilterCreationException e) {
                //todo somsthing
            }catch (SecessionCreationException.SorterCreationException e) {
                // todo something
            }
            //String[] filesToPrint = CurrentSecssion.getInstance().getCurrentSessionOutput();
          //  printFiles(filesToPrint);
            //out.printf("the index is now: %d filterValue: %s orderValue: %s%n", i, filterValue, orderValue);
        } // end of for loop
    }

    // private method to print the names of the filtered and sortered files
    private void printFiles(String[] filesToPrint){
        for (int i = 0; i< filesToPrint.length; i++){
            System.out.println(filesToPrint[i]);
        }
    }


     private void checkTypeTwoErrors(ArrayList<String> fileData) throws TypeTwoExceptions.BadFilterSectionName,
                                                                TypeTwoExceptions.BadOrderSectionName {
            String line ="";
            String filterValue = "";
            String orderValue  = "";

            // This loop iterates through the array, for each section checks if in the section exists type 2 errors
            for (int i = 0; i < fileData.size(); i++) {

                    i = checKFilter(fileData, i);
                    filterValue = fileData.get(i);

                    i = checkOrder(fileData, i);


              //  if (i < fileData.size()){
              //      line = fileData.get(i);
               //     if (line.compareTo(FILTER) == 0) {
               //         orderValue = ABS_KEY_WORD;
               //         i += INCREASE_ONE;
              //      } else {
              //          orderValue = line;
               //         i += INCREASE_TWO;
               //     }todo ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooowhata to do
                //}
            } // end of for loop
     } // end of checkTypeTwoErrors method




    private int checKFilter(ArrayList<String> fileData, int lineNumber) throws TypeTwoExceptions.BadFilterSectionName {
        String line     = "";
        String nextLine = "";

        if (fileData.get(lineNumber) != null) {
            line = fileData.get(lineNumber);
            if (line.compareTo(FILTER) == 0) { // If the current line equals FILTER
                if (fileData.get(lineNumber+1) != null) {
                    nextLine = fileData.get(lineNumber + 1);
                    // Checks if the nextLine is also FILTER, in this case prints error message
                    if (nextLine.compareTo(FILTER) == 0) {
                        System.err.print(String.format("Warning in line: %d%d", lineNumber, 1));
                    } else {
                        lineNumber += INCREASE_TWO;
                        return lineNumber;
                    }
                }
            }
            else {
                throw new TypeTwoExceptions.BadFilterSectionName();
            }
        }
        return lineNumber;
    } // end of checkFilter method

    private int checkOrder(ArrayList<String> fileData, int lineNumber) throws TypeTwoExceptions.BadOrderSectionName {
        String line     = "";
        String nextLine = "";

        if (fileData.get(lineNumber) != null) {
            line = fileData.get(lineNumber);
            if (line.compareTo(ORDER) == 0) { // If the current line equals ORDER
                if (fileData.get(lineNumber + 1) != null) {
                    nextLine = fileData.get(lineNumber + 1);
                    if (nextLine.compareTo(ORDER) == 0) {
                        System.err.print(String.format("Warning in line: %d%d", lineNumber, 1));
                    } if (nextLine.compareTo(FILTER) != 0) {
                        lineNumber += 1;
                        return lineNumber;
                    }
                }
            } else {
                throw new TypeTwoExceptions.BadOrderSectionName();
            }
        }
        return lineNumber;
    } // end of checkOrder method
}
