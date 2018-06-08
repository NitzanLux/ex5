package filesprocessing;

/*
 * The class represents an abstract type two expcion ,exclude io excption.
 */
abstract class TypeTwoExceptions extends Exception {
    /*--constance--*/
    private static final long serialVersionUID = 1L;
    private static final String TYPE_2_ERROR_PREFIX = "ERROR: %s";
    private static final String BAD_FILTER_MSG = "FILTER sub-section missing";
    private static final String BAD_ORDER_MSG = "ORDER sub-section missing";
    private static final String BAD_FORMAT_MSG = "Bad format";
    private static final String FILE_NOT_FOUND_MSG = "file not found";
    private static final String INCORRECT_AMOUNT_MSG = "Wrong usage. Should receive 2" +
            " arguments";
    private static final String NO_FILES_IN_SOURCEDIR = "No files in sourcedir";
    private static final String IO_COMMANDFILE_ERROR_MSG = "problem with accessing commandFile";

    /*
     * constractor rais exception which is type two exption constractor.
     * @param msg
     */
    TypeTwoExceptions(String msg) {
        super(String.format(TYPE_2_ERROR_PREFIX, msg));
    }

    /*
     * bad filter section name exeption.
     */
    static class BadFilterSectionName extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        BadFilterSectionName() {
            super(BAD_FILTER_MSG);
        }
    }

    /*
     * bad order section name exception.
     */
    static class BadOrderSectionName extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        BadOrderSectionName() {
            super(BAD_ORDER_MSG);
        }
    }

    /*
     * bad format file exception class
     */
    static class BadFormatFile extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        BadFormatFile() {
            super(BAD_FORMAT_MSG);
        }
    }

    /*
     * file not found exception.
     */
    static class FileNotFoundException extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        FileNotFoundException() {
            super(FILE_NOT_FOUND_MSG);
        }
    }
    /*
     * indicate IO error has occure.
     */
    static class IOProblemInCommandFile extends TypeTwoExceptions{
        /*
        * a default constructor.
         */
        IOProblemInCommandFile(){
            super(IO_COMMANDFILE_ERROR_MSG);
        }
    }

    /*
     * incorrect amount of arguments exception.
     */
    static class IncorrectAmountOfArguments extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        IncorrectAmountOfArguments() {
            super(INCORRECT_AMOUNT_MSG);
        }
    }

    /*
     * no files in source dir exception.
     */
    static class NoFilesInSourceDir extends TypeTwoExceptions {
        /*
         * a default constructor.
         */
        NoFilesInSourceDir() {
            super(NO_FILES_IN_SOURCEDIR);
        }
    }
}
