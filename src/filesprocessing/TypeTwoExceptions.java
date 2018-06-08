package filesprocessing;

/*
 * The class represents an abstract type two expcion ,exclude io excption.
 */
abstract class TypeTwoExceptions extends Exception {
    /*--constance--*/
    private static final long serialVersionUID = 1L;
    private static final String TYPE_2_ERROR_PREFIX = "ERROR: %s\n";
    private static final String FILTER_SUB_SECTION_MISSING = "FILTER sub-section missing";
    private static final String ORDER_SUB_SECTION_MISSING = "ORDER sub-section missing";
    private static final String BAD_FORMAT = "Bad format";
    private static final String FILE_NOT_FOUND = "file not found";
    private static final String WRONG_USAGE_SHOULD_RECEIVE_2_ARGUMENTS = "Wrong usage. Should receive 2" +
            " arguments";
    private static final String NO_FILES_IN_SOURCEDIR = "No files in sourcedir";

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
            super(FILTER_SUB_SECTION_MISSING);
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
            super(ORDER_SUB_SECTION_MISSING);
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
            super(BAD_FORMAT);
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
            super(FILE_NOT_FOUND);
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
            super(WRONG_USAGE_SHOULD_RECEIVE_2_ARGUMENTS);
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
