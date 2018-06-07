package DirectoryProcessor;

public abstract class TypeTwoExceptions extends Exception {
    private static final long serialVersionUID=1L;
    TypeTwoExceptions(String msg){
        super("ERROR: " + msg + "\n");
    }

    public static class BadFilterSectionName extends TypeTwoExceptions {
        BadFilterSectionName() {
            super("FILTER sub-section missing"); // todo magic
        }
    }
    public static class BadOrderSectionName extends TypeTwoExceptions{
        BadOrderSectionName() {
            super("ORDER sub-section missing"); // todo magic
        }
    }
    public static class BadFormatFile extends TypeTwoExceptions{
        BadFormatFile(){
            super("Bad format");
        }
    }
    public static abstract class IOExceptions extends TypeTwoExceptions{
        IOExceptions(String msg) {
            super(msg);
        }
    }
    public static class FileNotFoundException extends TypeTwoExceptions{
        FileNotFoundException(){
            super("file not found");
        }
    }
    public static class IncorrentAmountOfArguments extends TypeTwoExceptions{
        IncorrentAmountOfArguments(){
            super("Wrong usage. Should receive 2 arguments");
        }
    }

}
