package DirectoryProcessor;

import DirectoryProcessor.SecssionProcessor.CurrentSecssion;

import java.util.ArrayList;

/**
 * The main class. receives two arguments - the directory of files to filter&sort, and the command file.
 * If the arguments are valid, will filter and sort the files, for each section in the command file.
 */
public class FilesProcessor {

    static final int MIN_NUM_OF_ARGUMENTS = 1;

    public static void main(String [] args) throws TypeTwoExceptions.BadFilterSectionName, TypeTwoExceptions.BadOrderSectionName,
                                                   TypeTwoExceptions.IncorrentAmountOfArguments,
                                                   TypeTwoExceptions.FileNotFoundException {
        checkArgs(args); // checks the validation of arguments, if valid, continues
        String commendFileName = args[1];
        CommendFile commandFile = new CommendFile(commendFileName); // creates a new instance of CommendFile
        // Checks if the commend file is a valid file, if not, throws exception
        if (!(commandFile.isFile())){
            throw new TypeTwoExceptions.FileNotFoundException();
        }
        else{
            ArrayList<String> commendFileData = new ArrayList<String>();
            commendFileData = commandFile.readFile(); // saves the command file's data to an array list of Strings
            CurrentSecssion.getInstance().setPathName(args[0]); // sets the path of the files
            FileAnalyzer fileAnalyzer = new FileAnalyzer();
            fileAnalyzer.analyzeStringList(commendFileData);

        }
    }

    public static void checkArgs(String[]args) throws TypeTwoExceptions.IncorrentAmountOfArguments { // todo add errors if the files or command file doesnt exist

        // Checks if the number of arguments is smaller then 2 - in this case throws exception and stops
        if (args.length < MIN_NUM_OF_ARGUMENTS){
            throw new TypeTwoExceptions.IncorrentAmountOfArguments();
        }
        // todo try accesing the command file if does not exist error
    }

}
