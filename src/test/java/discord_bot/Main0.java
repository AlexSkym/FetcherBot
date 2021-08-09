package discord_bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main0 {


    public static JDA jda;
    public static String prefix = ".,";

    public static void main(String[] args) throws LoginException {

        //Actual token: ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0     //to invite it: https://discord.com/api/oauth2/authorize?client_id=817821150855823412&permissions=2148005952&scope=bot
        jda = JDABuilder.createDefault("ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0")
                .addEventListeners(new SubmainTextFetching(prefix, jda)) //after "new" I have to call the constructor, in this case, the constructor is in SubmainTextFetching.class
                .addEventListeners(new SubmainVocaroo(jda))
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
        //Call the TextFetcherBot
//        SubmainTextFetching mainTextFetching = new SubmainTextFetching(prefix, jda);

        //Call the WordOfTheDay_FetcherBot
        SubmainWOTD mainWOTD = new SubmainWOTD(prefix, jda);
        SubmainVocaroo submainVocaroo = new SubmainVocaroo(jda);

    }

}
