package forvo_API.data_processes;

import zUtil.myProperties.MyProperties;

import java.io.*;
import java.net.URL;

public class DownloadingForvoAudioMP3 {


    String path = "";

    /**
     * get the audio with a word being pronounced
     *
     * @param url url with the specified word
     * @throws IOException
     */
    public DownloadingForvoAudioMP3(String url) throws IOException {

        MyProperties myProperties = new MyProperties();
        path = myProperties.getMyProperties().getProperty("forvoPathName");

        download(url);
    }


    private void download(String fileUrl) throws IOException {

        // Open connection to the file
        URL url = new URL(fileUrl);

        InputStream is = url.openStream();
        // Stream to the destination file
        System.out.println("path: "+path);
        FileOutputStream fos = new FileOutputStream(path);

        // Read bytes from URL to the local file
        byte[] buffer = new byte[4096];
        int bytesRead = 0;

        System.out.println("Downloading " + path);
        while ((bytesRead = is.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }

        // Close destination stream
        fos.close();
        // Close URL stream
        is.close();
    }

    
}
