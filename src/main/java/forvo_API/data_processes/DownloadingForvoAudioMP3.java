package forvo_API.data_processes;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

public class DownloadingForvoAudioMP3 {


    Properties properties = new Properties();
    String path = "";

    /**
     * get the audio with a word being pronounced
     *
     * @param url url with the specified word
     * @throws IOException
     */
    public DownloadingForvoAudioMP3(String url) throws IOException {

        zUtil.MyProperties myProperties = new zUtil.MyProperties();
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
