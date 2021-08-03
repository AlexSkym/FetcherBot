package forvo_API;

import forvo_API.data_processes.ItemForXML;
import forvo_API.data_processes.ItemLists_processes.Lists_Handler_flexible;
import forvo_API.data_processes.ItemLists_processes.Lists_Handler_strict;
import forvo_API.data_processes.ItemLists_processes.Searching_inside_ItemsList;
import forvo_API.data_processes.fixedVariables.AutomatedMessages;
import forvo_API.data_processes.xml_processes.ReaderForXML;
import forvo_API.data_processes.xml_processes.WriterForXML;

import java.io.IOException;
import java.util.List;

/**
 * This will procure to be flexible and to send around what the user asked for, and no more.
 */
public class UserPetition_Handler_withThreads {

    //Writing (an only single thread)
    WriterForXML writerForXML = null;
    AutomatedMessages automatedMessages = new AutomatedMessages(); //Exception handled with: automatedMessages

    //Reading (an only single thread)
    ReaderForXML readerForXML = new ReaderForXML();

    //List handler (creating a new thread)
    Lists_Handler_flexible.GettingASortedList_withUserNeeds sortingFlexibly = new Lists_Handler_flexible.GettingASortedList_withUserNeeds();
    Lists_Handler_strict.GettingASortedList_withUserNeeds sortingStrictly = new Lists_Handler_strict.GettingASortedList_withUserNeeds();
    Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs searching = new Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs();

    List<ItemForXML> list = readerForXML.read_XMLFile();

    UserPetition_Handler_withThreads(String word, String language, String sex, String country){

        //step 1
        setWriterForXML(word);

        //step 2

    }

    /**
     * 1 - Start creating the URL we need to tell forvo what word we are looking for
     * 2 - Get the XML file that forvo gives, in a java String format
     * 3 - Convert that java String format into a XML file.
     * @param word audio with the word which user is looking for
     * Note: if the webpage not found the word, it'll send a automated message with the error.
     */
    void setWriterForXML (String word){
        try {
            writerForXML = new WriterForXML(word);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(automatedMessages.getMessageOfError_wordNotFound());
        }
    }





}
