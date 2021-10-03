package testing2;

//JDA full guide: https://ci.dv8tion.net/job/JDA/javadoc/index.html

import discord_bot.GettingJDA;
import discord_bot.submains.SubmainWOTD;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

// String spanish = "\u00E1\u00E9\u00ED\u00F3\u00FA"; = áéíóú
// unicode table = https://codestall.files.wordpress.com/2017/08/unicode-table.jpg?w=564
//  Documentation JDA:
//source: https://javadoc.io/static/net.dv8tion/JDA/4.0.0_66/net/dv8tion/jda/api/entities/MessageReaction.html
//source: https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/entities
public class Main0Listener {


    public static JDA jda;
    public static String prefix = ".,";

    public static void main(String[] args) throws LoginException {

        //Actual token: ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0     //to invite it: https://discord.com/api/oauth2/authorize?client_id=817821150855823412&permissions=2148005952&scope=bot
        jda = JDABuilder
//                .createDefault("ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0") //fetcherBot
                .createDefault("ODU0NzUzODUyNTg1NDEwNTgw.YMohjw.zcsyfgroGI8gY1cSqST5iidsRus") //pruebaBot
                .addEventListeners(Main_EventManager2.getInstance())
                .setActivity(Activity.playing("Type " + prefix + " help"))
                .build();

        System.out.println("-----------------------Starting-----------------------");

        //new Main_EventManager2(prefix, jda);
        Main_EventManager2.setJda(jda);

        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }

        System.out.println("Finished Building JDA!");


    }

}
