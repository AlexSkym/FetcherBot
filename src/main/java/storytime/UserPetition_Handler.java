package storytime;

import discord_bot.GettingJDA;
import net.dv8tion.jda.api.JDA;
import storytime.data_processes.StringRequest_Handler_firstPage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserPetition_Handler {

    Map<String, List<String>> map_MsgID_and_ParagraphList = new LinkedHashMap<>();
    String msgID;
    List<String> list = new ArrayList<>();
    //getting JDA;
    JDA jda = GettingJDA.getInstance().getJDA();

    //constructor
    public UserPetition_Handler() {

    }
    //constructor2
    /*public UserPetition_Handler(String messageID) {
        this.msgID = messageID;

        //if the ID is from our Bot, then... use a
        if (messageID.equalsIgnoreCase(jda.getSelfUser().getId())){
            StringRequest_Handler_firstPage handler_firstPage = new StringRequest_Handler_firstPage();
        }

    }


    public String newPetition(){

    }

    public String modifyPetition(){

    }*/

}
