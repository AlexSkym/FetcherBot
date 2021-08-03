package discord_bot;

import google_sheets.Library;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class SubmainTextFetching extends ListenerAdapter {		//example taken from https://github.com/DV8FromTheWorld/JDA/wiki
																//other examples https://github.com/DV8FromTheWorld/JDA/tree/master/src/examples/java

    String alias1="ftxt", alias2="ftext", alias3="fetchtxt", alias4="fetchtext";

    public static int num_of_paragraph_eng_easy = 1;
    public static int num_of_paragraph_eng_intermediate = 1;
    public static int num_of_paragraph_eng_advanced = 1;

    public static int num_of_paragraph_esp_easy = 1;
    public static int num_of_paragraph_esp_intermediate = 1;
    public static int num_of_paragraph_esp_advanced = 1;

    public static String theCounter = "";

    public static JDA jda;
    public static String prefix = ".,";

    SubmainTextFetching(String prefix, JDA jda){

        this.prefix = prefix;
        this.jda = jda;
    }


    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("API is ready! (SubmainTextFetching)");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        Message msg = event.getMessage();
        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    msg.getContentDisplay());

        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    msg.getContentDisplay());
        }

        //this evaluate that the content sent by the user is exactly the same as we have.
        if ((!event.getAuthor().isBot()) && (msg.getContentRaw().contains(prefix+alias1) || msg.getContentRaw().contains(prefix+alias2) ||
                msg.getContentRaw().contains(prefix+alias3) || msg.getContentRaw().contains(prefix+alias4) ) ) {
            MessageChannel channel = event.getChannel();
            //long time = System.currentTimeMillis();
            channel.sendTyping();
            Library library = new Library();
            String command = msg.getContentRaw();
            channel.sendMessage(library.getContext(command) + "" + theCounter) /* => RestAction<Message> */
                    .queue(//response /* => Message */ -> {
                        //response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();}
                    );
            theCounter = "";
        }

        //String prefix_help = prefix + "help";
        if (msg.getContentDisplay().equals(prefix+"help")){
            Library library = new Library();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(library.getHelp()).queue();
            System.out.println("cuaaaack");
        }

    }

}