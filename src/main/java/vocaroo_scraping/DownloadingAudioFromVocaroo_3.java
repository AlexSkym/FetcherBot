package vocaroo_scraping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DownloadingAudioFromVocaroo_3 {

    //this is the link used from the other class.
    String linkHref = "";

    //this will be returned at the end.
    String filePath = "";

    //these variables beside "filePath" will be used in the method "download"
    String fileName = "";
    String destinationDirectory = "";

    String DownloadingAudioFromVocaroo_3(String linkHref) throws IOException {

        System.out.println(linkHref.replace(".ro", "roo.com"));
        linkHref.replace(".ro", "roo.com");


        //Everything for the method
        this.linkHref = linkHref;
        fileName = "vocarooAudio.mp3";
        destinationDirectory = "";

        download(this.linkHref,destinationDirectory,fileName);

//        filePath = "C:/Users/Na_ma/" + fileName;
        filePath = "/Users/Na_ma/" + fileName;

        return filePath;
    }

    private static void download(String fileURL, String destinationDirectory,String name) throws IOException {

        // File name that is being downloaded
        String downloadedFileName = name;
        // Open connection to the file
        URL url = new URL(fileURL);

        InputStream is = url.openStream();
        // Stream to the destination file
        FileOutputStream fos = new FileOutputStream(destinationDirectory + "/" + downloadedFileName);

        // Read bytes from URL to the local file
        byte[] buffer = new byte[4096];
        int bytesRead = 0;

        System.out.println("Downloading " + downloadedFileName);
        while ((bytesRead = is.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }

        // Close destination stream
        fos.close();
        // Close URL stream
        is.close();
    }

}
