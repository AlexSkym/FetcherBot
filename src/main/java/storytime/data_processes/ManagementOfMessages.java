package storytime.data_processes;

import discord_bot.GettingJDA;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.List;

public class ManagementOfMessages {

    JDA jda = GettingJDA.getInstance().getJDA();

    List<String> bot_id_messages = new ArrayList<>();

    /**
     * this constructor has to be called from "SubmainStorytime.class"
     * @param id_message string_ id of the message of the event.
     */
    public ManagementOfMessages(String id_message) {

        //step 1
        boolean fromABot = verifying_id_message(id_message);

        //step 2
        boolean a;
        if (fromABot) a = false;
        else{

        }


    }



    /*-------------
    Private methods
    --------------*/



    //step 1
    private boolean verifying_id_message(String id_message){

        //if it is a message from a user (like typing ".,r")
        if (! (id_message.equals(jda.getSelfUser().getId())) ){
            return false;
        }
        //if it is from a my bot
        else{
            bot_id_messages.add(id_message);
            return true;
        }

    }


    //step 2




}
