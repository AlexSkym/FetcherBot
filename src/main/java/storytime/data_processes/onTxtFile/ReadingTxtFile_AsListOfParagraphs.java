package storytime.data_processes.onTxtFile;

import com.google.common.base.Strings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingTxtFile_AsListOfParagraphs {

    private List<String> listString;

    public ReadingTxtFile_AsListOfParagraphs() throws IOException {
        String fileName = "message.txt";
        //BufferedReader reader = new BufferedReader(new FileReader(fileName));

        BufferedReader reader  = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "UTF-8"));


        List<String> listOfSentences = new ArrayList<>();

        //reading all the document by lines (a line could have more than 3000 characters, or much more)
        while (reader.ready()){
            String line = reader.readLine();

            //I had to add this method to split bigger paragraphs in sentences (split by commas and periods)
            listOfSentences.addAll(splitting_byCommaAndPeriod(line));
        }

        listString = (formingAParagraph(listOfSentences));

    }






    /*
    Private method:
     */

    /**
     *
     * @param listOfSentences list of sentences (List<String>)
     * @return a list with paragraphs formed by the sentences.
     */
    private List<String> formingAParagraph(List<String> listOfSentences) {
        List<String> localList = new ArrayList<>();

        int max = 1850; //max Num Of Characters Per Paragraph
        String paragraph = ""; //one paragraph per one space in the "localList"

        //reading "listOfSentence" to determine how many sentences we will allow
        int i = 0;
        for (String sentence :
                listOfSentences) {

            //size of line+paragraph
            int size = (sentence+paragraph).length();
            //System.out.println("paragraph (actual): "+paragraph);

            if (size > max || i++ >= listOfSentences.size()-1){
                //System.out.println("\nsize (paragraph): "+ paragraph.length());
                System.out.println("paragraph (final): "+paragraph);
                localList.add(paragraph);
                paragraph = "";
            }

            paragraph += sentence;
        }

        return localList;
    }

    /**
     * Split several lines of text (one by one) by periods and commas.
     *   Note: this method will read blank or null lines as a line break.
     * @param line a string which will be split by commas and periods.
     * @return List<String>
     */
    private List<String> splitting_byCommaAndPeriod(String line) {
        List<String> list = new ArrayList<>();

        System.out.println("sizeLine: " + line.length());

        //a line break in a string for every break line in the txt file.
        if (isNull(line)) {
            System.out.println("line is null");
            list.add("\n\n");
        }//dividing by period
        else {
            String[] sentenceByPeriod = line.split("\\.");
            for (String sentence :
                    sentenceByPeriod) {
                System.out.println("sentence2: " + sentence + ".");


                //dividing "sentence" by comma
                int j = 0;
                String[] sentenceByComma = sentence.split(",");
                for (String subSentence :
                        sentenceByComma) {
                    //if it is the last sub sentence, add sentence + period
                    if (j++ >= sentenceByComma.length - 1) {
                        list.add(subSentence + ".");
                    }
                    //if it is not the last sub sentence
                    else {
                        list.add(subSentence + ",");
                    }
                }
            }

        }

        return list;
    }

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

    /**
     * get a list
     * @return {@code List<String>} with paragraphs
     */
    public List<String> getListString() {
        return listString;
    }

}
