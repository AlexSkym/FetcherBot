package storytime.data_processes.onTxtFile;

import net.dv8tion.jda.api.entities.Message;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * attachment    (source1: https://stackoverflow.com/questions/65555022/discord-jda-save-a-file-attachment-that-was-included-in-a-message)
 * attachment    (source2: https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/entities/Message.Attachment.html#downloadToFile(java.lang.String))
 */
public class WritingTxtFile {

    public WritingTxtFile(Message msg) {

        List<Message.Attachment> msgAttachments = msg.getAttachments();

        //saving file
        CompletableFuture<File> future = null;
        File file = new File("message.txt");
        for (Message.Attachment attachment :
                msgAttachments) {

            System.out.println("fileExtension: " + attachment.getFileExtension());
            if (attachment.getFileExtension().equalsIgnoreCase("txt")) {
                future = attachment.downloadToFile(file);

                System.out.println("Saving file");
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
