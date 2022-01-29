package testing2;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Main_EventManager2 extends ListenerAdapter{

    String prefix = ".,";
    static JDA jda;

    static Main_EventManager2 main_eventManager2;


    //constructor
    public static Main_EventManager2 getInstance(){
        if (main_eventManager2 == null){
            main_eventManager2 = new Main_EventManager2();
            return main_eventManager2;
        }
        return main_eventManager2;
    }

    //constructor
    private Main_EventManager2(){

    }

    public static void setJda(JDA jda) {
        Main_EventManager2.jda = jda;
    }

    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Starting");
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

        if (!event.getAuthor().isBot()) {

            if (jda == null) System.out.println("jda is null");
            else System.out.println("jda is right");

            jda = Main0Listener.jda;

            MessageChannel messageChannel = event.getChannel();

            User user = event.getAuthor();
            Role roleNews = jda.getRoleById("823692348361932842");

            event.getTextChannel().sendMessage("hola " + user.getAsMention() + " . " + roleNews.getAsMention()).queue();

            messageChannel.sendMessage((Message) new EmbedBuilder().setDescription("Hola " + user.getAsMention() + " . " + roleNews.getAsMention()).build()).queue();



            //messageChannel.sendMessage("hola " + user + " . " + jda.getUserById("808309885078994986").getName()).queue();


        }

    }



    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {


    }


    /*----------------
      Private methods
     ----------------*/


}
