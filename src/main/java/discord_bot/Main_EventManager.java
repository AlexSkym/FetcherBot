package discord_bot;

import discord_bot.submains.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main_EventManager extends ListenerAdapter{

    String prefix = ".,";
    static JDA jda;

    //Instances
//    SubmainWOTD WOTD = new SubmainWOTD();     //defined directly in Main0.class
    SubmainTextFetching textFetching;
    SubmainForvo forvo;
    SubmainVocaroo vocaroo;
    SubmainStorytime storytime;


    //constructor
    public Main_EventManager(){

    }


    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("API is ready! (Main_EventManager)");
        System.out.println("API is ready! (SubmainTextFetching)");
        System.out.println("API is ready! (SubmainVocaroo)");
        System.out.println("API is ready! (SubmainWOTD)");
        System.out.println("API is ready! (SubmainStorytime)");


//        new SubmainWOTD();    //it is not necessary
        jda = GettingJDA.getInstance().getJDA();
        textFetching = new SubmainTextFetching(prefix,jda);
        forvo = new SubmainForvo();
        vocaroo = new SubmainVocaroo(jda);
        storytime = new SubmainStorytime(jda);

    }



    /*--------------
      Event methods
     --------------*/



    /**
     * Determining what kind of events (messages) every functionality of the bot will receive.
     *  Ex. Storytime only will receive the messages of our bot itself and the commands needed from the user.
     *
     * @param event MessageReceivedEvent.
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {


        /*------------------------------------
        -When to activate every functionality-
        --------------------------------------*/

        //getting the first part of message displayed. Only to get the alias of the command
        String split_msg = event.getMessage().getContentDisplay();
        split_msg = split_msg.split(" ")[0];
        System.out.println("split_msg: " + split_msg);


        //id of the user itself
        System.out.println("name: " + jda.getSelfUser().getName() + ", id: "+ jda.getSelfUser().getId());
        System.out.println("msg.name: " + event.getAuthor().getName() + ", msg.id: " + event.getAuthor().getId());

        //receiving the user command and our bot messages
        if (isAlias(Aliases.STORYTIME_ALIASES, split_msg) || event.getAuthor().getId().equalsIgnoreCase(jda.getSelfUser().getId())) {
            //Submain Storytime
            storytime.start_onMessageReceived(event);
        }

        if (isAlias(Aliases.FORVO_ALIASES, split_msg)) {
            //Submain Forvo
            forvo.start_onMessageReceived(event);
        }

        if (isAlias(Aliases.TEXTFETCHING_ALIASES, split_msg)) {
            System.out.println("received");
            //Submain TextFetching
            textFetching.start_onMessageReceived(event);
        }

        //Submain Vocaroo
        vocaroo.start_onMessageReceived(event);


    }



    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        //if reactions aren't from a bot         //|| if reactions are applied over a message from our bot
        if (!event.getUser().isBot()){
            //submain Storytime
            storytime.start_onMessageReactionAdd(event);
        }

    }


    /*----------------
      Private methods
     ----------------*/


    private boolean isAlias(Aliases aliases, String msgDisplay){

        for (String alias :
                aliases.aliases) {
            if (alias.equalsIgnoreCase(msgDisplay)){
                return true;
            }
        }
        return false;
    }



}
