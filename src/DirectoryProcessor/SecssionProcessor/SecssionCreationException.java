package DirectoryProcessor.SecssionProcessor;

public abstract class SecssionCreationException extends Exception {
    private static final long serialVersionUID=1L;
    SecssionCreationException(String msg){
        super(msg);
    }
    public static class FilterCreationException extends SecssionCreationException{
        FilterCreationException(){
            super("cannot create filter.");//todo megic number.
        }
    }
    public static class SorterCreationException extends SecssionCreationException{
        SorterCreationException(){
            super("cannot create sorter.");//todo megic number.
        }
    }
}
