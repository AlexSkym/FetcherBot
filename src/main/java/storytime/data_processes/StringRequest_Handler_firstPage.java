package storytime.data_processes;

import net.dv8tion.jda.api.entities.Message;
import storytime.data_processes.fixedVariables.AutomatedMessages;
import storytime.data_processes.fixedVariables.VerifyingMsg;
import storytime.data_processes.onTxtFile.ReadingTxtFileAsList;
import storytime.data_processes.onTxtFile.WritingTxtFile;
import storytime.data_processes.paragraphs.GettingParagraphs;

import java.io.IOException;
import java.util.List;

/**
 * This class will handle:
 *      * all the classes we actually have
 *      * exceptions that some uses could cause
 */
public class StringRequest_Handler_firstPage {

    //instances
    private AutomatedMessages automatedMessages = new AutomatedMessages();
    private VerifyingMsg verifyingMsg;
    WritingTxtFile writingTxtFile;
    ReadingTxtFileAsList readingTxtFileAsList;
    GettingParagraphs gettingParagraphs = GettingParagraphs.getNewInstanceOfParagraphs();

    //useful variables
    private Boolean nextStep = false;
    List<String> list_paragraphs;

    //final results
    private String finalSimpleMessage;
    private String finalCustomMessage;


    /**
     * constructor
     * @param msg Message (object)
     */
    public StringRequest_Handler_firstPage(Message msg) {
        nextStep = false;
        //initializing variables
        finalCustomMessage = "";
        finalSimpleMessage = "";

        //step 1 - verifying that the input is right
        verifyingUserInput(msg);

        //step 2 - saving file.txt
        if (nextStep) savingFile(msg);

        //step 3 - readingFile
        //if (nextStep) readingFile_and_gettingList();

        //step 4 - reading the very first paragraph and adding one (++) to the static Iterator of paragraphs
        if (nextStep) readingFirstParagraph();

        //step 5 -
        //use public methods


    }


    /*
    Private methods
     */

    /** Step 1
     * Verifying if the user write finely the command, and have uploaded the correct file.txt
     * @param msg
     */
    private void verifyingUserInput(Message msg) {
        verifyingMsg = new VerifyingMsg(msg);

        //working
        if (verifyingMsg.isAllRight()){
            nextStep = true;
            System.out.println("working 1");
            //adding a pretty emoji to the user message to indicate the bot have read.
            msg.addReaction(":grow:823705887469993984").queue(); //♥
        }//not working
        else{
            //finalSimpleMessage = automatedMessages.getErrorMessage_sentenceTypo();
            if (verifyingMsg.isCommandRight() && !verifyingMsg.isTxtAttachmentRight()){
                finalSimpleMessage = automatedMessages.getErrorMessage_AttachmentNotFound();
            }

            nextStep = false;
        }
    }

    /** Step 2
     * Saving the text file once the verification was rightly fine.
     * @param msg object Message.
     */
    private void savingFile(Message msg) {
        writingTxtFile = new WritingTxtFile(msg);
        nextStep = true;
        System.out.println("working 2");
    }

    /** Step 3
     * Reading the file orderly to get a beautiful list of paragraphs.
     * @return a list with every paragraph of the file
     */
    private List readingFile_and_gettingList(){
        try {//working
            readingTxtFileAsList = new ReadingTxtFileAsList();
            nextStep = true;
            System.out.println("working 3");
        }//not working
        catch (IOException e) {
            finalSimpleMessage = automatedMessages.getErrorMessage_UnexpectedError();
            nextStep = false;
            e.printStackTrace();
        }

        return readingTxtFileAsList.getListString();
    }

    /** Step 4
     * Just to read the very first paragraph, and adding one (++) to the static Iterator of paragraphs.
     *      The other paragraphs will have be be read by another class.
     *  Note: This will read also the text file.
     *
     */
    private void readingFirstParagraph() {
        String temporalString = null;

        try { //working
            temporalString = gettingParagraphs.getVeryFirstParagraph();
            finalCustomMessage = temporalString;
            nextStep = true;
            System.out.println("working 4");
        } //not working
        catch (IOException e) {
            nextStep = false;
            finalSimpleMessage = automatedMessages.getErrorMessage_UnexpectedError();
            e.printStackTrace();
        }


        //not working
        if (temporalString == null || temporalString.equals("")){
            finalSimpleMessage = automatedMessages.getErrorMessage_AttachmentNullPoint();
            nextStep = false;
        } //working
        else{
            finalCustomMessage = temporalString;
            nextStep = true;
        }

    }




    /*
    Public methods
     */

    public String getFinalSimpleMessage() {
        //if fine, fine!!
        if (nextStep) {
            System.out.println("finalSimpleMessage: " + finalSimpleMessage);
            return finalSimpleMessage = automatedMessages.getSuccessMessage();
        }
        //else, sad:
        return finalSimpleMessage;
    }

    public String getFinalCustomMessage() {
        //something went wrong:
        if (!nextStep){
            System.out.println("finalCustomMessage: " + finalCustomMessage);
            return finalCustomMessage = "";
        }
        //else, fine:
        return finalCustomMessage;
    }

    public boolean isAllRight(){
        System.out.println("all is right");
        return nextStep;
    }


}
