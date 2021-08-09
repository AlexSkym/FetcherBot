package storytime;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import storytime.data_processes.StringRequest_Handler_firstPage;
import storytime.data_processes.paragraphs.GettingParagraphs;

import java.util.List;

public class SubmainStoryTime extends ListenerAdapter {

    JDA jda;

    //Useful variables
    String ID_thisBot = "854753852585410580";

    String emojiPrevious = ":previous:873773670273781760";
    String emojiStop = ":stop:873771979273043978";
    String emojiNext = ":next:873771979285602366";

    Boolean isAllRight_temporal = false;    //used to know if add or not emojis to the bot message

    Message editThismessage;  //used to know what message has to be overwrite when using the emojis mentioned

    //instances
    //StringRequest_Handler_firstPage stringRequestHandler_firstPage;

    public SubmainStoryTime(JDA jda) {
        this.jda = jda;
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("API is ready! (SubmainStoryTime)");
    }



    /*--------------------------------
    Dealing only with the user message
    ----------------------------------*/


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage(); //object of the message of a user
        MessageChannel messageChannel = msg.getChannel(); //obj


        //To start with a command sent by a discord user
        if (!event.getAuthor().isBot()) {

            SendingFinalMessage(msg, messageChannel);
        }

        //Adding to the bot the corresponding emojis if the user request is all right.
        if (event.getAuthor().isBot() &&
                event.getAuthor().getId().equals(ID_thisBot) &&
                isAllRight_temporal){

            editThismessage = msg; //ID of the bot message

            msg.addReaction(emojiPrevious).queue(); // <-
            msg.addReaction(emojiStop).queue();     //  X
            msg.addReaction(emojiNext).queue();     // ->

            isAllRight_temporal = false;
        }

    }

        /*------------------
         if reaction are....
         ------------------*/


    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event){

        MessageReaction messageReaction = event.getReaction();
        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
        MessageChannel messageChannel = event.getChannel();

        //getting the actual emote a user utilized
        String reactionEmote_data = ":"+messageReaction.getReactionEmote().getName()+":"+messageReaction.getReactionEmote().getId();

        if (reactionEmote_data.equalsIgnoreCase(emojiNext) && !event.getUser().isBot()){
            System.out.println("next");
            GettingParagraphs gettingParagraphs = GettingParagraphs.getCurrentInstanceOfParagraphs();
            editThismessage.editMessage(new EmbedBuilder()
                    .setDescription(gettingParagraphs.getNextParagraph()).build()).queue();
        }
        if (reactionEmote_data.equalsIgnoreCase(emojiPrevious) && !event.getUser().isBot()){
            System.out.println("previous");
            GettingParagraphs gettingParagraphs = GettingParagraphs.getCurrentInstanceOfParagraphs();
            editThismessage.editMessage(new EmbedBuilder()
                    .setDescription(gettingParagraphs.getPreviousParagraph()).build()).queue();
        }
        if (reactionEmote_data.equalsIgnoreCase(emojiStop) && !event.getUser().isBot()){
            System.out.println("stop");
            editThismessage.editMessage(new EmbedBuilder().setDescription("^^").build()).queue();
        }



    }





    /**
     * To send the final message after validating and processing the user request.
     * @param msg the user message
     * @param messageChannel the channel where that message is in
     */
    private void SendingFinalMessage(Message msg, MessageChannel messageChannel){
        //Starting with the user request
        StringRequest_Handler_firstPage stringRequestHandler_firstPage = new StringRequest_Handler_firstPage(msg);


        String finalSimpleMessage = stringRequestHandler_firstPage.getFinalSimpleMessage();
        String finalCustomMessage = stringRequestHandler_firstPage.getFinalCustomMessage();

        isAllRight_temporal = stringRequestHandler_firstPage.isAllRight();
        //if reading the file went finely, let's send everything
        if (isAllRight_temporal){
            //messageChannel.sendMessage(finalSimpleMessage + "\n" +
            //                                finalCustomMessage).queue();

            messageChannel.sendMessage(new EmbedBuilder()
                    .setDescription(finalSimpleMessage + "\n" + finalCustomMessage)
                    .build()).queue();
            System.out.println("if");
        }
        //if something wrong, let's send the error message
        else {
            messageChannel.sendMessage(new EmbedBuilder()
                                    .setDescription(stringRequestHandler_firstPage.getFinalSimpleMessage())
                                    .build()).queue();
            System.out.println("else");
        }


    }




}
