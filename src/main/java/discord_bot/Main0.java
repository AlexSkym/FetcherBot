package discord_bot;

//JDA full guide: https://ci.dv8tion.net/job/JDA/javadoc/index.html
// String spanish = "\u00E1\u00E9\u00ED\u00F3\u00FA"; = áéíóú
// JavaDocs: https://ibb.co/dfTcjx2    / https://www.tutorialspoint.com/java/java_documentation.htm#

import discord_bot.submains.SubmainWOTD;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

// unicode table = https://codestall.files.wordpress.com/2017/08/unicode-table.jpg?w=564
//  Documentation JDA:
//source: https://javadoc.io/static/net.dv8tion/JDA/4.0.0_66/net/dv8tion/jda/api/entities/MessageReaction.html
//source: https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/entities
public class Main0 {

    public static JDA jda;
    public static String prefix = ".,";

    /**
     * Main class of the whole project
     *
     * @param args
     * @throws LoginException
     */
    public static void main(String[] args) throws LoginException {

        //to invite it: https://discord.com/api/oauth2/authorize?client_id=817821150855823412&permissions=2148005952&scope=bot
        jda = JDABuilder
                .createDefault(System.getenv("TOKEN01")) //FetcherBot's token
                .addEventListeners(new Main_EventManager())
                .setActivity(Activity.playing(" " + prefix + "ftxt"))
                .build();

        System.out.println("-----------------------Starting-----------------------");

        //new Main_EventManager(prefix, jda);

        GettingJDA gettingJDA = GettingJDA.getInstance();
        gettingJDA.setJDA(jda);


        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }

        System.out.println("Finished Building JDA!");

        //it's necessary to have this instance below of "jda"
        new SubmainWOTD();

    }

}
