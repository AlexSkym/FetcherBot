package discord_bot;

//JDA full guide: https://ci.dv8tion.net/job/JDA/javadoc/index.html

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

// String spanish = "\u00E1\u00E9\u00ED\u00F3\u00FA"; = áéíóú
// unicode table = https://codestall.files.wordpress.com/2017/08/unicode-table.jpg?w=564

//source: https://javadoc.io/static/net.dv8tion/JDA/4.0.0_66/net/dv8tion/jda/api/entities/MessageReaction.html
//source: https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/entities
public class Main0 {


    public static JDA jda;
    public static String prefix = ".,";

    public static void main(String[] args) throws LoginException {

        //Actual token: ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0     //to invite it: https://discord.com/api/oauth2/authorize?client_id=817821150855823412&permissions=2148005952&scope=bot
        jda = JDABuilder
                .createDefault("ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0")
                .addEventListeners(new SubmainTextFetching(prefix, jda)) //after "new" I have to call the constructor, in this case, the constructor is in SubmainTextFetching.class
                .addEventListeners(new SubmainVocaroo(jda))
                .addEventListeners(new SubmainForvo(jda))
                .setActivity(Activity.playing("Type " + prefix + " help"))
                .build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }

        System.out.println("Finished Building JDA!");


        /**
         * Calling the main methods
         *
         * @param jda is used to start using the bot with... since it were "TextFectcher" or "WOTD" or "AudioCallerFromVocaroo", etc.
         *
         * @param prefix is used to maintain the rule of using ".," to call the bot
         *
        */
        //Call the TextFetcherBot (it is not necessary to call)
        //SubmainTextFetching mainTextFetching = new SubmainTextFetching(prefix, jda);

        //Call the WordOfTheDay_FetcherBot
        SubmainWOTD submainWOTD = new SubmainWOTD(prefix, jda);

    }

}
