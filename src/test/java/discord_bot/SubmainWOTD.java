package discord_bot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import wotd_scraping.GettingWordOfTheDay;

import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;

public class SubmainWOTD {


    private final String prefix;
    private final JDA jda;
    private final long channelId = 848415285768749076L; //796511380701708318
    private final long WOTD_RoleId = 826545054105075762L;
    private String content;

    EmbedBuilder embedBuilder;
    GettingWordOfTheDay gettingWordOfTheDay;

    SubmainWOTD(String prefix, JDA jda){

        this.prefix = prefix;
        this.jda = jda;
        System.out.println("prefix: "+prefix);

        //Calling method
        doThing();
    }


    public void doThing() {
        TextChannel channel = jda.getTextChannelById(this.channelId);
        if (channel != null) {
            Role role = jda.getRoleById(WOTD_RoleId);
            embedBuilder = new EmbedBuilder();
            gettingWordOfTheDay = new GettingWordOfTheDay();


            ZoneId zoneId = ZoneId.of("America/New_York");

            int theHour = 11, theMinute = 3;

            do {
                if (LocalTime.now(zoneId).getHour() == theHour &&
                        LocalTime.now(zoneId).getMinute() == theMinute){
                    System.out.println("El momento a llegado");
                    try {
                        channel.sendMessage(gettingWordOfTheDay.GettingWordOfTheDay(embedBuilder, role).build()).queue();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //making the message above not working.
                    while (LocalTime.now(zoneId).getMinute() == theMinute) {
                        if (LocalTime.now(zoneId).getMinute() != theMinute)  break;
                    }
                }
            } while (true);

        }
    }


}
