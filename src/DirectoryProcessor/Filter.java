package DirectoryProcessor;

import java.io.FileFilter;

public class Filter {
    public static Filter filter =new Filter();
    private Filter(){}


    enum MathComperison{
            GREATER_THAN(){boolean filterd(long fileSize,long threshold){return fileSize>threshold;}},
            SMALLER_THAN(){boolean filterd(long fileSize,long threshold){return fileSize<threshold;}};
            abstract boolean filterd(long fileSize,long threshold);
            boolean isFilterd(FileFacade file,long threshold){
                return filterd(file.length(),threshold);
            }

    }
    enum NameComperison{
            FILE(){boolean isFilterd(FileFacade file,String parameter){return file.getName().equals(parameter);}}
            ,CONTAINS(){boolean isFilterd(FileFacade file,String parameter){return file.getName().contains(parameter);}}
            ,PREFIX(){boolean isFilterd(FileFacade file,String parameter){return prefixEquals(file,parameter,true);}}
            ,SUFFIX(){boolean isFilterd(FileFacade file,String parameter){return prefixEquals(file,parameter,false);}};
            abstract boolean isFilterd(FileFacade file,String parameter);
            static private boolean prefixEquals(FileFacade file,String parameter,boolean fromStart){
                String fileName=file.getName();
                if (!fromStart){
                    fileName= reversString(fileName);
                    parameter=reversString(parameter);
                }
                for (int i = 0; i <parameter.length() ; i++) {
                    if (parameter.charAt(i)!=fileName.charAt(i)){
                        return false;
                    }
                }return true;
            }
            static private String reversString(String value){
                StringBuilder stringBuilder =new StringBuilder(value);
                return stringBuilder.reverse().toString();
            }
    }
    enum FileStateComperison {
        WRITABLE(){ boolean isFilterd(FileFacade file){return file.canWrite();}},
        EXECUTABLE(){ boolean isFilterd(FileFacade file){return file.canExecute();}},
        HIDDEN() {boolean isFilterd(FileFacade file){return file.isHidden();}};
        abstract boolean isFilterd(FileFacade file);
    }
}
