package discord_bot.submains;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import storytime.data_processes.StringRequest_Handler_firstPage;
import storytime.data_processes.paragraphs.GettingParagraphs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Note: what a mess of code!! xD. it was so hard

public class SubmainStorytime {

    private JDA jda;

    //Useful variables
    //String ID_thisBot = "854753852585410580";

    private String emojiPrevious = ":previous:873773670273781760";
    private String emojiStop = ":stop:873771979273043978";
    private String emojiNext = ":next:873771979285602366";

    private Boolean isAllRight_temporal = false;    //used to know if add or not emojis to the bot message

    private List<Message> editThis_messagesOfTheBot = new ArrayList<>();  //used to know what message has to be overwrite when using the emojis mentioned

    //numOfMessage_nameOfUser
    //this is only to grab the name of the user and the message that user received to give him permissions to use Next, Previous and Stop.
    private User temporalUser;
    private Map<Message,User> map_numOfMessageToBeEdited_and_nameOfUserWhoSentRequest = new LinkedHashMap<>();

                    //Map <numOfMessage, Map<List<paragraphs>, iterator>
    //numOfMessage_listWithParagraphs
    private List<String> temporalList;
    private Map<String, List<String>> map_numOfMessageToBeEdited_and_listWithParagraphs;
    private Map<List<String>, Integer> map_listWithParagraphs_and_iteratorOfParagraphs;

    //instances
    //StringRequest_Handler_firstPage stringRequestHandler_firstPage;

    public SubmainStorytime(JDA jda) {
        this.jda = jda;
    }




    /*------------------
    -if user message is-
    -------------------*/


    public void start_onMessageReceived(MessageReceivedEvent event) {

        System.out.println("storytime Starting. Msg: "+event.getMessage().getContentDisplay());

        Message msg = event.getMessage(); //object of the message of a user
        MessageChannel messageChannel = msg.getChannel(); //obj

        /*-------
          Step 1
         -------*/

        //To start with a command sent by a discord user
        if (!event.getAuthor().isBot()) {

            //it is used to determine if the bot should send or not the emojis (next,previous,stop) over the message.
            isAllRight_temporal = sendingFirstFinalMessage(msg, messageChannel);
        }

        /*-------
          Step 2
         -------*/

        if (jda == null) System.out.println("is null");

        //after executing all rightly the user request, the bot add the corresponding emojis to the message of the bot.
        if (isAllRight_temporal && event.getAuthor().isBot() && event.getAuthor().equals(jda.getSelfUser())){

            addingEmojisToBotMessage(msg);

        }

    }

        /*------------------
         if reaction are....
         ------------------*/


    public void start_onMessageReactionAdd(MessageReactionAddEvent event){

        /*-------
          Step 3
         -------*/

        MessageReaction messageReaction = event.getReaction();
        //getting the actual emote a user utilized
        String reactionEmote_data = ":"+messageReaction.getReactionEmote().getName()+":"+messageReaction.getReactionEmote().getId();
        //calling the method
        handlingUserReactions(reactionEmote_data, event);

    }







    /*---------------
     Private methods
    ----------------*/


    /**
     * To send the final message after validating and processing the user request.
     * @param msg the user message
     * @param messageChannel the channel where that message is in
     */
    private boolean sendingFirstFinalMessage(Message msg, MessageChannel messageChannel){

        //Starting with the user request
        StringRequest_Handler_firstPage stringRequestHandler_firstPage = new StringRequest_Handler_firstPage(msg);

        String finalSimpleMessage = stringRequestHandler_firstPage.getFinalSimpleMessage();
        String finalCustomMessage = stringRequestHandler_firstPage.getFinalCustomMessage();

        System.out.println("finalSimpleMessage: "+finalSimpleMessage+
                "\n"+"finalCustomMessage: "+finalCustomMessage);

        //if reading the file went finely, let's send everything
        if (stringRequestHandler_firstPage.isAllRight()){
            messageChannel.sendMessage(new EmbedBuilder()
                    .setDescription(finalSimpleMessage + "\n" + finalCustomMessage)
                    .build()).queue();

            temporalList = stringRequestHandler_firstPage.getList_paragraphs();
            temporalUser = msg.getAuthor();     //this is for HandlingReaction method actually.
        }
        //if something wrong, let's send the error message
        else {
            messageChannel.sendMessage(new EmbedBuilder()
                                    .setDescription(stringRequestHandler_firstPage.getFinalSimpleMessage())
                                    .build()).queue();
        }

        return stringRequestHandler_firstPage.isAllRight();
    }

    /** Step 2
     * After executing all rightly the user request, the bot add the corresponding emojis to the message of the bot.
     * @param msg Message from an Event (MessageReceivedEvent)
     */
    private void addingEmojisToBotMessage(Message msg){

        System.out.println("executing emojis....");

        map_numOfMessageToBeEdited_and_nameOfUserWhoSentRequest.put(msg,temporalUser);
        map_numOfMessageToBeEdited_and_listWithParagraphs.put(msg.getId(), temporalList);

        editThis_messagesOfTheBot.add(msg); //ID of the bot message

        msg.addReaction(emojiPrevious).queue(); // <-
        msg.addReaction(emojiStop).queue();     //  X
        msg.addReaction(emojiNext).queue();     // ->

        isAllRight_temporal = false;
    }


    /**
     * Every reaction (if "next", "previous" or "stop") will handled, in order to send the right answer
     * @param reactionEmote_data a string, ReactionEmote_data. to know exactly what is that reaction.
     * @param event the MessageReactionEvent
     */
    private void handlingUserReactions(String reactionEmote_data, MessageReactionAddEvent event) {

        //if next
        if (reactionEmote_data.equalsIgnoreCase(emojiNext) && !event.getUser().isBot()){
            System.out.println("next");
            GettingParagraphs gettingParagraphs = GettingParagraphs.getCurrentInstanceOfParagraphs();

            for (Message message :
                    editThis_messagesOfTheBot) {
                /* 1- Comparing two IDs. The ID from any of the messages of the bot with the
                 actual ID that this reaction have.
                   2- if the user pressed the icon, and that user was who asked for the command,
                 then the bot will serve a new paragraph.
                 */
                if (message.getId().equalsIgnoreCase(event.getMessageId()) &&
                    event.getUser().equals(map_numOfMessageToBeEdited_and_nameOfUserWhoSentRequest.get(message)) &&
                    (map_numOfMessageToBeEdited_and_listWithParagraphs.containsKey(event.getMessageId()))){

                    //getting iterator from the specified message
                    int iterator = map_listWithParagraphs_and_iteratorOfParagraphs.get(map_numOfMessageToBeEdited_and_listWithParagraphs.get(event.getMessageId()));

                    //editing the message
                    message.editMessage(new EmbedBuilder()
                            .setDescription(gettingParagraphs.getNextParagraph()).build()).queue();
                }
            }

            event.getReaction().removeReaction(event.getUser());    //just to clean the reactions from users
        }

        //if next
        else if (reactionEmote_data.equalsIgnoreCase(emojiPrevious) && !event.getUser().isBot()){
            System.out.println("previous");
            GettingParagraphs gettingParagraphs = GettingParagraphs.getCurrentInstanceOfParagraphs();

            for (Message message :
                    editThis_messagesOfTheBot) {
                /* 1- Comparing two IDs. The ID from any of the messages of the bot with the
                 actual ID that this reaction have.
                   2- if the user pressed the icon, and that user was who asked for the command,
                 then the bot will serve a new paragraph.
                 */
                if (message.getId().equalsIgnoreCase(event.getMessageId()) &&
                        event.getUser().equals(map_numOfMessageToBeEdited_and_nameOfUserWhoSentRequest.get(message))){
                    //editing the message
                    message.editMessage(new EmbedBuilder()
                            .setDescription(gettingParagraphs.getPreviousParagraph()).build()).queue();
                }
            }

            event.getReaction().removeReaction(event.getUser());    //just to clean the reactions from users
        }

        //if stop
        else if (reactionEmote_data.equalsIgnoreCase(emojiStop) && !event.getUser().isBot()){
            System.out.println("stop");
            event.getMessageId();
            for (Message message :  //for every firstFinalMessage from the bot
                    editThis_messagesOfTheBot) {
                /* 1- Comparing two IDs. The ID from any of the messages of the bot with the
                 actual ID that this reaction have.
                   2- if the user pressed the icon, and that user was who asked for the command,
                 then the bot will serve a new paragraph.
                 */
                if (message.getId().equalsIgnoreCase(event.getMessageId()) &&
                        event.getUser().equals(map_numOfMessageToBeEdited_and_nameOfUserWhoSentRequest.get(message))){
                    //editing the message
                    message.editMessage(new EmbedBuilder()
                            .setDescription("<:duowave:874469322947457075>").build()).queue();
                }
            }

            event.getReaction().removeReaction(event.getUser());    //just to clean the reactions from users
        }

    }




}
