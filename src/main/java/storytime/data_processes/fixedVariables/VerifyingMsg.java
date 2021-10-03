package storytime.data_processes.fixedVariables;

import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class VerifyingMsg {
    //instances
    FixedVariables fixedVariables = new FixedVariables();

    //useful variables of the class
    private Boolean commandRight;

    private Boolean txtAttachmentRight;

    private Boolean nextAction;


    public VerifyingMsg(Message msg) {

        commandRight = false;
        txtAttachmentRight = false;
        nextAction = false;

        //1- verifying command
        isCommandRight(msg.getContentDisplay());

        //2- verifying if Attachment exists
        if (nextAction) isAttachmentTxt(msg);

        //3- sending the conclusion
        if (nextAction) isAllRight();

    }



    /*
    Private Methods
     */

    private void isCommandRight(String contentDisplay){
        List<String> list = new ArrayList<>();
        list = fixedVariables.getAliasComplexes();

        //verifying if contentDisplay is equals to aliases
        for (String oneAlias :
                list) {
            System.out.println("oneAlias: "+oneAlias);
            if (contentDisplay.trim().toLowerCase()
                    .equalsIgnoreCase(
                            oneAlias)){
                commandRight = true;
                nextAction = true;

                break;
            }
            else {
                commandRight = false;
                nextAction = false;
            }
        }

    }

    private void isAttachmentTxt(Message msg) {

        //verifying if, in the middle of every attachments a user could send, exists a txt file
        try {
            Message.Attachment attachment = msg.getAttachments().get(0);

            System.out.println("fileExtension: " + attachment.getFileExtension());
            if (attachment.getFileExtension().equalsIgnoreCase("txt")) {
                txtAttachmentRight = true;
                nextAction = true;
                System.out.println("file Exists");
            } else {
                txtAttachmentRight = false;
                nextAction = false;
            }
        }
        catch (IndexOutOfBoundsException e){
            txtAttachmentRight = false;
            nextAction = false;
            System.out.println("no file");
        }

    }


    /*
    Public method
     */

    public Boolean isCommandRight() {
        return commandRight;
    }

    public Boolean isTxtAttachmentRight() {
        return txtAttachmentRight;
    }

    public boolean isAllRight(){
        return nextAction;
    }


}
