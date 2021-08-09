package storytime;

import java.util.ArrayList;
import java.util.List;

public class MessageThreads extends Thread{

    private List<String> listOfMessageIDs = new ArrayList<>();

    private String messageID = "";
    private String messageID_fromThread = "";

    /**
     * Constructor
     * @param messageID needed to be used specially in Threads, in the override method "run()".
     */
    MessageThreads(String messageID){
        //just to have a log of every messageID
        listOfMessageIDs.add(messageID);
    }


    /**
     * let's work with Threads (?)
     *
     * it saves the successful message IDs of the BOT.
     */
    @Override
    public void run(){
        this.messageID_fromThread = this.messageID;
    }


    /*
    Setters and Getters
     */

    public String getMessageID_fromThread() {
        return messageID_fromThread;
    }

    public List<String> getListOfMessageIDs() {
        return listOfMessageIDs;
    }

}
