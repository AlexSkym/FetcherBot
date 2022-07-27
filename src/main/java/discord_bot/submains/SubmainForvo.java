package discord_bot.submains;

import forvo_API.UserPetition_Handler_simpleMode;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import zUtil.myProperties.MyProperties;

import java.io.File;

public class SubmainForvo {

    public SubmainForvo(){

    }

    public void start_onMessageReceived(MessageReceivedEvent event) {

        //the message of a user in a object
        Message msg = event.getMessage();


        if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    msg.getContentDisplay());

            MessageChannel messageChannel = event.getPrivateChannel();
            messageChannel.sendMessage("From https://discord.gg/5MVUqGhuAF");

        } else {
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    msg.getContentDisplay());

            //if it is not a bot
            if (!event.getAuthor().isBot() && msg.getContentDisplay().substring(0,2).contains(".,")) {

                MessageChannel channel = event.getChannel();

                //get exactly the message a user sent
                String userMessage = msg.getContentRaw();

                //replacing the accents with normal letters (english letters)
                userMessage = StringUtils.stripAccents(userMessage);

                //sending the message to the class handler
                UserPetition_Handler_simpleMode handler =
                        new UserPetition_Handler_simpleMode(userMessage);

                //getting the forvo audio file
                MyProperties myProperties = new MyProperties();
                File audioFile = new File(myProperties.
                        getMyProperties().
                        getProperty("forvoPathName"));

                if (handler.isNextAction()){
                    channel.sendMessage(handler.getFinalMessage()).addFile(audioFile).queue();
                }else {
                    //channel.sendMessage(handler.getFinalMessage()).queue();
                }

            }
        }

    }

}
