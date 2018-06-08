package filesprocessing;

import filesprocessing.secssionsprocessor.CurrentSecession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The main class. receives two arguments - the directory of files to filter&sort, and the command file.
 * If the arguments are valid, will filter and sort the files, for each section in the command file.
 * @author liorit,nlux
 */
public class DirectoryProcessor {
    /*--constants--*/
    private static final int MIN_NUM_OF_ARGUMENTS = 1;
    private static final int PATH_NAME_POSITION = 0;
    private static final int COMMAND_NAME_POSITION = 1;

    public static void main(String[] args) {
        boolean excepsionOccure = false;
        try {
            checkArgs(args); // checks the validation of arguments, if valid, continues
        } catch (TypeTwoExceptions.IncorrectAmountOfArguments incorrectAmountOfArguments) {
            excepsionOccure = true;
            System.err.println(incorrectAmountOfArguments.getMessage());
        }
        if (!excepsionOccure) {
            String commendFileName = args[COMMAND_NAME_POSITION];
            CommandFile commandFile = new CommandFile(commendFileName);//creates a new instance of CommandFile
            FileFacade path = new FileFacade(args[PATH_NAME_POSITION]);
            // Checks if the commend file is a valid file, if not, throws exception
            if (!(commandFile.isFile())) {
                System.err.println(new TypeTwoExceptions.FileNotFoundException().getMessage());
            } else if (!(path.isDirectory())) {
                System.err.println(new TypeTwoExceptions.NoFilesInSourceDir().getMessage());
            } else {
                ArrayList<String> commendFileData = null;// saves the command file's data to
                try {
                    commendFileData = commandFile.readFile();
                } catch (TypeTwoExceptions.IOProblemInCommandFile ioProblemInCommandFile) {
                    System.err.println(ioProblemInCommandFile.getMessage());
                    excepsionOccure = true;
                }
                if (!excepsionOccure) {
                    // an array list of Strings
                    CurrentSecession.getInstance().setCurrentPath(path);
                    try {
                        FileAnalyzer.getInstance().analyzeStringList(commendFileData);
                    } catch (TypeTwoExceptions typeTwoExceptions) {
                        System.err.println(typeTwoExceptions.getMessage());
                    }
                }
            }
        }
    }

    private static void checkArgs(String[] args) throws TypeTwoExceptions.IncorrectAmountOfArguments {
        // Checks if the number of arguments is smaller then 2 - in this case throws exception and stops
        if (args.length < MIN_NUM_OF_ARGUMENTS) {
            throw new TypeTwoExceptions.IncorrectAmountOfArguments();
        }
    }

}
