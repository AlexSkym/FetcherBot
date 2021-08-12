package storytime.data_processes.paragraphs;

import storytime.data_processes.onTxtFile.ReadingTxtFile_AsListOfParagraphs;

import java.io.IOException;
import java.util.List;

public class GettingParagraphs {

    static GettingParagraphs gettingParagraphs; //instance of this class itself

    //Recurrent variables
    static List<String> paragraphsList; //list with all the texts to be sent

    int iterator = 0;
    int listSize;

    //constructor
    private GettingParagraphs() {

    }




    /*
    Instances: close to Singleton Pattern
     */

    /** Creator of new Instance.
     * Every time you start the method "getVeryFirstParagraph" you have to get a new
     * instance of the class to start from scratch.
     * @return a new instance of this class
     */
    public static GettingParagraphs getNewInstanceOfParagraphs(){
        gettingParagraphs = new GettingParagraphs();
        return gettingParagraphs;
    }

    /** Getter of current Instance.
     * Every time you want to use the methods "getPreviousParagraph" and "getNextParagraph"
     * you have to use the same instance that we used previously.
     * @return the same
     */
    public static GettingParagraphs getCurrentInstanceOfParagraphs(){
        return gettingParagraphs;
    }





    /*
    Getters for paragraphs:
     */


    /**
     * Only once per call
     * @return the very First Paragraph
     * @throws IOException
     */
    public String getVeryFirstParagraph(List<String> paragraphsList) throws IOException {
        iterator = 0;

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
