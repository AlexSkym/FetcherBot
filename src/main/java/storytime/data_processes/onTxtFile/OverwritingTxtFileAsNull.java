package storytime.data_processes.onTxtFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OverwritingTxtFileAsNull {

    OverwritingTxtFileAsNull() throws IOException {

        //saving file

        File file = new File("message.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter("message.txt"));
        //writer.write("");

        System.out.println("Saving file as null");

    }

}
