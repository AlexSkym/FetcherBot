package storytime;

import discord_bot.Main_EventManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

class Main extends ListenerAdapter{


    public static JDA jda;
    public static String prefix = ".,";


    Main(){ }


    public static void main(String[] args) throws LoginException {

        //PruebaBot token: ODU0NzUzODUyNTg1NDEwNTgw.YMohjw.zcsyfgroGI8gY1cSqST5iidsRus     //to invite it: https://discord.com/api/oauth2/authorize?client_id=817821150855823412&permissions=2148005952&scope=bot
        jda = JDABuilder
                //.createDefault("ODE3ODIxMTUwODU1ODIzNDEy.YEPFTA.8ro0KnoOTUQ7y3IJqNfNl2Oank0") //FetcherBot
                .createDefault("ODU0NzUzODUyNTg1NDEwNTgw.YMohjw.zcsyfgroGI8gY1cSqST5iidsRus") //pruebaBot
                .addEventListeners(new Main_EventManager())
                .setActivity(Activity.playing("Type " + prefix + " help"))
                .build();
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }


    }

}
