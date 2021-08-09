package storytime;

public class MessageReaction_Handler {

    //using the Singleton Pattern Design
    private static MessageReaction_Handler instance = null;


    boolean definitiveStop;
    boolean nextText_Action;

    //constructor
    public MessageReaction_Handler() {

    }


    /** Using the Singleton Pattern Design
     * @return instance of the class if it haven't been called before.
     */
    public static MessageReaction_Handler getInstance() {
        if (instance == null){
            instance = new MessageReaction_Handler();
        }
        return instance;
    }


    /*
     * Getters and Setters
     */

    /**
     * To make the bot work for next action/s about receiving the next paragraph of the file.txt
     * @param nextText_Action boolean variable
     */
    public void setNextText_Action(boolean nextText_Action) {
        this.nextText_Action = nextText_Action;
    }
    public boolean isNextText_Action() {
        return nextText_Action;
    }

    /** ---Maybe it will never be used---
     * If the bot stopped, then the file.txt has to be cleared to null.
     * @return boolean to know if the bot has stopped reading the file.txt
     */
    public boolean isDefinitiveStop() {
        return definitiveStop;
    }

    /** ---Maybe it will never be used---
     * If the bot stopped, then the file.txt has to be cleared to null.
     * @param definitiveStop set if the bot has to stop reading the file.txt or just start.
     */
    public void setDefinitiveStop(boolean definitiveStop) {
        this.definitiveStop = definitiveStop;
    }


}
