package discord_bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import vocaroo_scraping.Getting_the_MP3_link_2;

import java.io.File;
import java.io.IOException;

public class SubmainVocaroo extends ListenerAdapter {

    private final JDA jda;

    SubmainVocaroo(JDA jda){
        //just to make the "JDABuilder" work in "jda" variable. "main" method

        this.jda = jda;
    }


/*
    public static void main(String[] args) {

        try {
            //FetcherBot: ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0
            // PruebaBot: ODU0NzUzODUyNTg1NDEwNTgw.YMohjw.zcsyfgroGI8gY1cSqST5iidsRus
            jda = JDABuilder.createDefault("ODU0NzUzODUyNTg1NDEwNTgw.YMohjw.zcsyfgroGI8gY1cSqST5iidsRus")
                .addEventListeners(new SubmainVocaroo()) //after "new" I have to call the constructor, in this case, the constructor is in SubmainTextFetching.class
                .setActivity(Activity.playing("!p "))
                .build();
            jda.awaitReady();

        } catch (InterruptedException | LoginException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }


    }

 */

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
        if (!event.getAuthor().isBot()) {

            Getting_the_MP3_link_2 scraping = new Getting_the_MP3_link_2();


            //theURL got from discord related to vocaroo.com
            String theMessage = event.getMessage().getContentDisplay();
            System.out.println("theMessage: " + theMessage);

            if (event.getMessage().getContentDisplay().contains("https://voca")){
                System.out.println("hey");
                try {
                    String pathFile = scraping.Scraping_to_get_the_link_2(theMessage);

                    File file = new File(pathFile);

                    MessageChannel channel = event.getChannel();
                    //long time = System.currentTimeMillis();
                    channel.sendTyping();
                    channel.sendMessage("hola") /* => RestAction<Message> */
                            .addFile(file)
                            .queue(response /* => Message */ -> {
                                    response.editMessageFormat("T: %d ms", (int) (System.currentTimeMillis() * 0.0001 * 0.0001)).queue();}
                            );
                }catch (IOException e){
                    //e.printStackTrace();
                    System.out.println("link no encontrado");
                }
            }

        }

    }


}
