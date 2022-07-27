package discord_bot.submains;

import google_sheets.MainLibraryHandler;
import google_sheets.library.TreatingCommands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class SubmainTextFetching{		//example taken from https://github.com/DV8FromTheWorld/JDA/wiki
																//other examples https://github.com/DV8FromTheWorld/JDA/tree/master/src/examples/java

    public static JDA jda;
    public static String prefix = ".,";

    MainLibraryHandler mainLibraryHandler = new MainLibraryHandler();

    public static String textForCounting = "";

    /**
     * Constructor 1
     */
    public SubmainTextFetching(){}

    /**
     * Constructor 2
     */
    public SubmainTextFetching(String prefix, JDA jda){

        System.out.println("Fetching Text...");

        this.prefix = prefix;
        this.jda = jda;
    }


    /**
     * The Beginning and the Ending are here...
     * @param event I'm extending this method to get this Event.
     */
    public void start_onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();

        //If it's not a Bot, let's continue!
        if (!event.getAuthor().isBot()) {
            MessageChannel channel = event.getChannel();
            //long time = System.currentTimeMillis();
            channel.sendTyping();

            String command = msg.getContentRaw();
            channel.sendMessage(mainLibraryHandler.getEndingContent(command) + "" + textForCounting) /* => RestAction<Message> */
                    .queue();
            textForCounting = "";
        }

    }

}