package storytime.data_processes.onTxtFile;

import com.google.common.base.Strings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingTxtFileAsList {

    private List<String> listString = new ArrayList<>();

    public ReadingTxtFileAsList() throws IOException {
        String fileName = "message.txt";
        //BufferedReader reader = new BufferedReader(new FileReader(fileName));

        BufferedReader reader  = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        //max Num Of Characters Per Paragraph
        int max = 2000;
        String paragraph = "";
        String line = "";

        //read until the whole file.txt is done.
        while (reader.ready()){     //reader.ready() indicates "true" when has something more to read.
            line = reader.readLine();
            paragraph += line+"\n";

            //size of line+paragraph
            int size = (line+paragraph).length();
            System.out.println("size: "+size);

            if (!reader.ready() || size > max){
                System.out.println("paragraph: "+paragraph);
                listString.add(paragraph);
                paragraph = "";
            }


        }

    }

    /*
    Private method:
     */

    /**
     * detecting if the string "line" is null
     * @param someString
     * @return
     */
    private boolean isNull(String someString){
        try {
            someString.equalsIgnoreCase(null);
        } catch (NullPointerException npe) {
            return true;
        }
        if (someString.trim().toLowerCase().equals(null) ||
                someString.trim().equalsIgnoreCase("null") ||
                "null".equalsIgnoreCase(someString) ||
                !(someString != null) ||
                Strings.nullToEmpty(someString).trim().isEmpty() ||
        someString.isEmpty() || someString.isBlank()) {
            return true;
        }
        return false;
    }





    /*
    Public method:
     */
    public List<String> getListString() {
        return listString;
    }

}
