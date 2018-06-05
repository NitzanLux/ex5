package DirectoryProcessor;
import java.io.*;
import java.util.ArrayList;

public class CommendFile extends FileFacade {
    public CommendFile(String s) {
        super(s);
    }

    // The method reads the file, and returns an arrayList containing all the lines in the file
    ArrayList<String> readFile() {

        ArrayList<String> fileData = new ArrayList<String>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getName()))) {

            String line = null;
            int i = 0;

            // runs all over the file, while the file not over, and adds each line to the arrayList
            while ((line = bufferedReader.readLine()) != null) {
                fileData.add(i, line); // adds the current line to the arrayList in the next empty index
                i++; // Increases the iterator through the arrayList
            }
        } catch (IOException ioe) {
            System.out.println("error");
        }
        return fileData;
    }
}

            /**
                while (line != null){
                if (line.contains("FILTER")){
                    //System.out.println(line);
                    line = bufferedReader.readLine();
                    //System.out.println(line);
                    filterValue = line;
                }
                if (line.contains("ORDER")){
                    //System.out.println(line);
                    line = bufferedReader.readLine();
                    if(line.contains("FILTER")){
                        //System.out.println(line);
                        orderValue = "abs";
                        continue;

                    }
                    else {
                        //System.out.println(line);
                        orderValue = line;
                    }
                }
                //SectionBuilder = new SectionBuilder(filterValue,orderValue);
                line = bufferedReader.readLine();
                System.out.println(filterValue);
                System.out.println(orderValue);
             }
          //   System.out.println(filterValue);
            // System.out.println(orderValue);

            //bufferedReader.close();
*/


