package DirectoryProcessor;

import DirectoryProcessor.SecssionProcessor.CurrentSecssion;

import java.util.ArrayList;

public class Test {
    public static void main(String [] args) throws TypeTwoExceptions.BadFilterSectionName, TypeTwoExceptions.BadOrderSectionName {

        String name = "/cs/usr/liorait/IdeaProjects/ex5/text2.txt";
        ArrayList<String> as = new ArrayList<String>();
        CommendFile cm = new CommendFile(name);
        if (cm.isFile()) {
            //System.out.println("h");
            //try {
                as = cm.readFile();
            //}
            //catch(){

  //          }
        }
        CurrentSecssion.getInstance().setPathName(name);
        FileAnalyzer fa = new FileAnalyzer();

        fa.analyzeStringList(as);

    }
}
