package google_sheets;

import google_sheets.library.PreparingTextsToBeSent;
import google_sheets.library.TreatingCommands;

/**
 * The final steps:
 * Receiving the command... using the TreatingCommands.java
 * Responding through PreparingTextsToBeSent.java
 */
public class MainLibraryHandler {
    //classes
    TreatingCommands treatingCommands = new TreatingCommands();
    PreparingTextsToBeSent preparingTextsToBeSent = new PreparingTextsToBeSent();


    public MainLibraryHandler(){

    }

    public String getEndingContent(String command){
        return preparingTextsToBeSent.getEndingText(
                treatingCommands.getTreatedText_fromCommand(command)
        );
    }

}
