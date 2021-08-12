package forvo_API;

import forvo_API.data_processes.DownloadingForvoAudioMP3;
import forvo_API.data_processes.ItemForXML;
import forvo_API.data_processes.ItemLists_processes.Lists_Handler_flexible;
import forvo_API.data_processes.ItemLists_processes.Lists_Handler_strict;
import forvo_API.data_processes.ItemLists_processes.Searching_inside_ItemsList;
import forvo_API.data_processes.CreatingTheURL_and_writing;
import forvo_API.data_processes.fixedVariables.AutomatedMessages;
import forvo_API.data_processes.fixedVariables.AcceptableValues_ofAUserString;
import forvo_API.data_processes.fixedVariables.MapperEnumFixedVar;
import forvo_API.data_processes.xml_processes.ReaderForXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * This will procure to be strict and to send around what the user asked for, and no more.
 */
public class UserPetition_Handler_simpleMode {

    //Getting user string (one only single thread)
    AcceptableValues_ofAUserString acceptableValues = new AcceptableValues_ofAUserString();
    String word, language, sex, country;

    //Writing (one only single thread)
    CreatingTheURL_and_writing creatingTheURLAndWriting;
    AutomatedMessages automatedMessages = new AutomatedMessages(); //Exception handled with: automatedMessages

    //Reading (one only single thread)
    ReaderForXML readerForXML = new ReaderForXML();
    List<ItemForXML> globalTemporalList = new ArrayList<>();

    //List handler (creating a new thread)
    Lists_Handler_flexible.GettingASortedList_withUserNeeds sortingFlexibly = new Lists_Handler_flexible.GettingASortedList_withUserNeeds();
    Lists_Handler_strict.GettingASortedList_withUserNeeds sortingStrictly = new Lists_Handler_strict.GettingASortedList_withUserNeeds();
    Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs searching = new Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs();

    //Downloading audio
    DownloadingForvoAudioMP3 downloadingForvoAudioMP3 = null;

    /*if everything goes fine in a method, then it has to be "true"
        to indicate that the next action has to be executed */
    boolean nextAction = false;


    //final message to the user. Even if it is a error or not
    String finalMessage;


    /*
    Starting. Public method:
     */



    /**
     * constructor
     * @param userMessage string with the message of the user
     */
    public UserPetition_Handler_simpleMode(String userMessage){
        finalMessage = "";

        //step 0    //getting the help if user asked for it
        getHelp(userMessage);


        //step 1    //getting the values from the user String
        if (nextAction) getAcceptableValues_ofAUserString(userMessage);

        //step 2    //getting the url and write the xml file
        if (nextAction) setCreatingUrl_and_WritingForXML(word);


        //step 3    //getting the list<ItemForXML> from the xml file
        if (nextAction) globalTemporalList = getReaderForXML();


        //step 4    //getting the definitive ordered list
        if (nextAction) globalTemporalList = getDefinitiveList(globalTemporalList, language, sex, country);


        //step 5    //getting the audio after downloading it
        if (nextAction) downloadingAudio(globalTemporalList);


        //step 6    //sending the final message to the user.


    }







    /*
    private methods
     */




    /** step 0.
     * getting help
     * @param userMessage the string a discord user sent
     */
    private void getHelp(String userMessage) {
        MapperEnumFixedVar mapper = new MapperEnumFixedVar();

        /*if the user wrote "*oneAlias* + -h" or "*oneAlias* + --help"
           then (s)he will get the help */
        for (String oneAlias :
                mapper.getAliasesComplexes()) {

            //working
            if (userMessage.trim().equalsIgnoreCase(oneAlias+" -h")){
                finalMessage = automatedMessages.getHelp();
                nextAction = false;
                System.out.println("it is giving help");
                break;
            }//working
            else if (userMessage.trim().equalsIgnoreCase(oneAlias+" --help")){
                finalMessage = automatedMessages.getHelp();
                nextAction = false;
                System.out.println("it is giving help");
                break;
            }//not working
            else{
                nextAction = true;
            }
        }


    }

    /** Step 1.
     * Getting the values from the user String if it is well written.
     * @param userString a string (command) that a user sent in Discord.
     */
    @SuppressWarnings("unchecked")
    private void getAcceptableValues_ofAUserString(String userString){
        Map<String, String> values = acceptableValues.verifying_aUserString(userString);

        //not working
        if (values == null || (values.get("word") == null || values.get("word").equals(""))) {
            //finalMessage = automatedMessages.getMessageOfError_sentenceTypo();
            System.out.println(automatedMessages.getMessageOfError_sentenceTypo());
            nextAction = false;

        }//working
        else{
            word = values.get("word").trim().toLowerCase();
            language = values.get("language").trim().toLowerCase();
            sex = values.get("sex").trim().toLowerCase();
            country = values.get("country").trim().toLowerCase();
            nextAction = true;

            System.out.println("step 1: working");
        }

        //printing everything
        //System.out.println("" +
        //        "word: "+word+", "+"language: "+language+", "+"sex: "+sex+", "+"country: "+country+".");


    }

    /** Step 2.
     * 1 - Start creating the URL we need to tell forvo what word we are looking for
     * 2 - Get the XML file that forvo gives, in a java String format
     * 3 - Convert that java String format into a XML file.
     * @param word audio with the word which user is looking for
     * Note: if the webpage not found the word, it'll send a automated message with the error.
     */
    private void setCreatingUrl_and_WritingForXML(String word){

        //working
        try {
            creatingTheURLAndWriting = new CreatingTheURL_and_writing(word);
            nextAction = true;
            System.out.println("step 2: working");
        }//not working
        catch (IOException e) {
            nextAction = false;
            finalMessage = automatedMessages.getMessageOfError_wordNotFound();
            System.out.println(automatedMessages.getMessageOfError_wordNotFound());
            e.printStackTrace();
        }
    }

    /** Step 3.
     * get an item list from the xml file
     * @return a list<ItemForXML>
     */
    private List<ItemForXML> getReaderForXML() {


        //not working
        if (readerForXML.read_XMLFile() == null){
            System.out.println(automatedMessages.getMessageOfError_unexpectedFileError());
            finalMessage = automatedMessages.getMessageOfError_unexpectedFileError();
            nextAction = false;
            return null;
        }//working
        else{
            nextAction = true;

            System.out.println("step 3: working");

            return readerForXML.read_XMLFile();
        }


    }

    /** Step 4.
     * Getting the ending list with elements of type Item.class
     * @param list List<ItemForXML> from the XML file previously obtained
     * @param language "spanish"/"english"/""(both)
     * @param sex f/m
     * @param country prettyName of the country
     * @return ending definitive List<ItemForXML>
     */
    private List<ItemForXML> getDefinitiveList(List<ItemForXML> list,String language,
                                              String sex,String country){

        //language controlling
        list = sortingStrictly.handling_lists_ByLanguages(list, language);

        //sex controlling
        list = sortingStrictly.handling_lists_BySex(list, sex);

        //country controlling
        list = sortingStrictly.handling_lists_ByCountry(list, country);

        //not working
        if (list == null || list.size() <= 0){
            nextAction = false;
            System.out.println(automatedMessages.getMessageOfError_specsNotFound());
            finalMessage = automatedMessages.getMessageOfError_specsNotFound();
            return null;
        }//working
        else {
            System.out.println("step 4: working");

            nextAction = true;
            return list;
        }

    }

    /** Step 5.
     * Getting the first audio of the first item.
     * @param globalTemporalList the last ordered List<ItemForXML>
     */
    private void downloadingAudio(List<ItemForXML> globalTemporalList) {
        String theUrl = String.valueOf(globalTemporalList.get(0).getPathmp3().trim());

        System.out.println("theUrl " + theUrl);

        //working
        try {
            downloadingForvoAudioMP3 = new DownloadingForvoAudioMP3(theUrl);
            System.out.println("step 5: working");
            finalMessage = automatedMessages.getSuccessMessage();

        }//not working
        catch (IOException e) {
            System.out.println(automatedMessages.getMessageOfError_audioDeprecated());
            finalMessage = automatedMessages.getMessageOfError_audioDeprecated();
            e.printStackTrace();
        }
    }



    /*
    public method:
     */

    /** Step 6.
     * send the final message to the Discord user
     * @return string with a message
     */
    public String getFinalMessage(){
        return finalMessage;
    }

    /** Step 7.
     * send a confirmation of a new action
     * @return boolean with a confirmation
     */
    public boolean isNextAction() {
        return nextAction;
    }
}
