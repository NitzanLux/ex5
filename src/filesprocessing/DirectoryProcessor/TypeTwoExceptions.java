package filesprocessing.DirectoryProcessor;

public abstract class TypeTwoExceptions extends Exception {
    private static final long serialVersionUID=1L;
    private static final String TYPE_2_ERROR_PREFIX = "ERROR: ";

    TypeTwoExceptions(String msg){
        super(TYPE_2_ERROR_PREFIX+msg);
    }

    static class BadFilterSectionName extends TypeTwoExceptions {
        BadFilterSectionName() {
            super("FILTER sub-section missing"); // todo magic
        }
    }
    static class BadOrderSectionName extends TypeTwoExceptions{
        BadOrderSectionName() {
            super("ORDER sub-section missing"); // todo magic
        }
    }
    static class BadFormatFile extends TypeTwoExceptions{
        BadFormatFile(){
            super("Bad format");
        }
    }
    public static abstract class IOExceptions extends TypeTwoExceptions{
        IOExceptions(String msg) {
            super(msg);
        }
    }
    static class FileNotFoundException extends TypeTwoExceptions{
        FileNotFoundException(){
            super("file not found");
        }
    }
    static class IncorrentAmountOfArguments extends TypeTwoExceptions{
        IncorrentAmountOfArguments(){
            super("Wrong usage. Should receive 2 arguments");
        }
    }
    static class NoFilesInSourceDir extends TypeTwoExceptions{
        NoFilesInSourceDir(){
            super("No files in sourcedir");
        }
    }

}
