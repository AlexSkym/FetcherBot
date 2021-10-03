package storytime.data_processes;

import storytime.data_processes.onTxtFile.ReadingTxtFile_AsListOfParagraphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GettingParagraphs {

    ReadingTxtFile_AsListOfParagraphs readingFile;

    //Recurrent variables
    List<String> paragraphsList = new ArrayList<>(); //list with all the texts to be sent

    int iterator = 0;
    int listSize;

    //management of instances of this class
    static GettingParagraphs singleton; //instance of this class itself
    //String messageID;
    static Map<String, GettingParagraphs> map_getInstance = new LinkedHashMap<>();


    //constructor
    private GettingParagraphs() {

    }




    /*-------------------------------------
     Instances: close to Singleton Pattern
    --------------------------------------*/

    /** Creator of new Instance.
     * Every time you start the method "getVeryFirstParagraph" you have to get a new
     * instance of the class to start from scratch.
     * @return a new instance of this class
     */
    public static GettingParagraphs getNewInstanceOfParagraphs(){
        singleton = new GettingParagraphs();
        return singleton;
    }

    /** Getter of current Instance.
     * Every time you want to use the methods "getPreviousParagraph" and "getNextParagraph"
     * you have to use the same instance that we used previously.
     * @return the same
     */
    public static GettingParagraphs getCurrentInstanceOfParagraphs(String messageID){
        if (map_getInstance.containsKey(messageID)){
            System.out.println("---------------------------" +
                                "....id...: " + messageID + "" +
                                "--------------------------");
            System.out.println("I think it works");
            System.out.println("--actual iterator: "+map_getInstance.get(messageID).iterator);
            System.out.println("--actual listSize: "+map_getInstance.get(messageID).listSize);

            return map_getInstance.get(messageID);
        }else {
            System.out.println("it's null");
            return null;
        }
    }

    /**
     * This id, will be saved in a map that contains the instance of this class
     * @param messageID string, id of the message
     */
    public static void setMessageID(String messageID) {
        //this.messageID = messageID;
        map_getInstance.put(messageID, singleton);

    }







    /*-----------------------
     Getters for paragraphs:
    ------------------------*/


    /**
     * Only once per call
     * @return the very First Paragraph
     * @throws IOException
     */
    public String getVeryFirstParagraph() throws IOException {
        iterator = 0;

        readingFile = new ReadingTxtFile_AsListOfParagraphs();
        paragraphsList = readingFile.getListString();
        listSize = paragraphsList.size();

        String firstParagraph = paragraphsList.get(0);

        return "Page num: " + (iterator+1) + ".  |  P\u00E1gina num: " + (iterator+1) + "." +
                "```"+firstParagraph+"```";
    }

    /**
     * Get always the next paragraph
     * @return string: next paragraph
     */
    public String getNextParagraph (){
        iterator ++;

        //not working
        if (iterator >= listSize){

            if (iterator > listSize) {
                iterator--;
            }
            String str = "" +
                    "Page num: " + (iterator+1) + ".  |  P\u00E1gina num: " + (iterator+1) + "." +
                    "```--------------------```" + "\n" + "\n" +
                    "The end." + "\n" +
                    "El fin." + "\n" +
                    "                      <:dot_green:869956933476565062>" + "\n" +
                    "";
            return  str;

        }//working
        else {
            String nextParagraph = paragraphsList.get(iterator);

            return  "Page num: " + (iterator+1) + ".  |  P\u00E1gina num: " + (iterator+1) + "." +
                    "```"+nextParagraph+"```";
        }
    }

    /**
     * Get always the previous paragraph
     * @return string: previous paragraph
     */
    public String getPreviousParagraph (){
        iterator --;

        //working
        if (iterator >= 0){
            String previousParagraph = paragraphsList.get(iterator);

            return  "Page num: " + (iterator+1) + ".  |  P\u00E1gina num: " + (iterator+1) + "." +
                    "```"+previousParagraph+"```";
        }//not working
        else {

            if (iterator <= -2) {
                iterator ++;
            }
            String str = "" +
                    "Page num: " + (iterator+1) + ".  |  P\u00E1gina num: " + (iterator+1) + "." +
                    "```--------------------```" + "\n" + "\n" +
                    "Start there" + "\n" +
                    "Comienza all\u00ED" + "\n" +
                    "                      <:next:873771979285602366>" + "\n" +
                    "";
            return str;
        }

    }





}
