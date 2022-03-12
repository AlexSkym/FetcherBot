package discord_bot.submains;

import discord_bot.GettingJDA;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import wotd_scraping.GettingWordOfTheDay;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;

public class SubmainWOTD extends Thread{


    private JDA jda;
//    private final long channelId_1 = 875576052532015115L, channelId_2 = 933225706924691507L; //testing... ("server" ("wotd" channel))
//    private final long WOTD_RoleId = 823692348361932842L; //testing... (news role)

    private final long channelId_1 = 848415285768749076L; //796511380701708318 ID spamWOTD //848415285768749076 ID realWOTD
    private final long channelId_2 = 860236709916835900L; //796511380701708318 ID spamWOTD //860236709916835900 ID realWOTD
    private final long WOTD_RoleId = 826545054105075762L; //826545054105075762
    String theURL_1 = "https://www.spanishdict.com/wordoftheday";
    String theURL_2 = "https://www.ingles.com/palabradeldia";

    ZoneId zoneId = ZoneId.of("America/New_York");
    int theHour = 10, theMinute = 0;
//    int theHour = 23, theMinute = 28; //testing...
    int sleepingTime = 1000 * 60 * 60 * 4;  //1000 milliseconds = 1 second . Here it is sleeping 4 hours.



    EmbedBuilder embedBuilder;
    GettingWordOfTheDay gettingWordOfTheDay;

    public SubmainWOTD(){
        GettingJDA gettingJDA = GettingJDA.getInstance();
        this.jda = gettingJDA.getJDA();

        if (jda == null) System.out.println("WOTD, jda==null");
        else System.out.println("WOTD, jda==!null");
        System.out.println("\nactualHourTime: " + LocalTime.now(zoneId).getHour());
        System.out.println("actualMintime: " + LocalTime.now(zoneId).getMinute());

        doThing_spanishDict();
    }




    /** Using thread to run "doThing_spanishDict()" method,
     * which seems to be infinite and doesn't let other classes be running at the same time.
     *
     */
    /*
    @Override
    public void run(){
        doThing_spanishDict();
    }*/


    public void doThing_spanishDict() {

        System.out.println("SPANISHDICT!!");

        TextChannel channel_1 = jda.getTextChannelById(this.channelId_1);
        TextChannel channel_2 = jda.getTextChannelById(this.channelId_2);
        if (channel_1 != null && channel_2 != null) {
            Role role = jda.getRoleById(WOTD_RoleId);
            embedBuilder = new EmbedBuilder();
            gettingWordOfTheDay = new GettingWordOfTheDay();


            do {
                if (LocalTime.now(zoneId).getHour() == theHour &&
                        LocalTime.now(zoneId).getMinute() == theMinute){
                    System.out.println("El momento a llegado");
                    try {
                        //from spanishdict.com
                        channel_1.sendMessage(role.getAsMention()).queue();
                        channel_1.sendMessageEmbeds( gettingWordOfTheDay.GettingWordOfTheDay(
                                embedBuilder, role, theURL_1, 1).build()).queue();
                        //from ingles.com
                        channel_2.sendMessage(role.getAsMention()).queue();
                        channel_2.sendMessageEmbeds( gettingWordOfTheDay.GettingWordOfTheDay(
                                embedBuilder, role, theURL_2, 2).build()).queue();

                        //making the message above not working. Sleeping for some minutes
                        try {
                            Thread.sleep(sleepingTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } while (true);

        }
    }



}
