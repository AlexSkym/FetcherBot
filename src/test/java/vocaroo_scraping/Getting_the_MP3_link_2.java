package vocaroo_scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Getting_the_MP3_link_2 {

    //This is the original message sent by the user
    private String message = "";
    //This will indicates the URL of each different audios that users of the server sent.
    private String theURL = "";
      //This indicates the URL that will be used to get the MP3 file
    private String MP3_link = "";
      //This will be returned. This's where the file (MP3) was saved in the computer
    private String filePath = "";

    public String Scraping_to_get_the_link_2 (String theMessage) throws IOException{

        this.theURL = theURL;

        //the private Methods working
        try {
            theURL = extractUrls(theMessage).get(0);
            System.out.println("theURL: " + theURL);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        MP3_link = getting_mp3_link(theURL);
        filePath = gettingVocarooPath(MP3_link);

        return filePath;
    }


    /**
     * Returns a list with all links contained in the input
     */
    public static List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }


    /**
     * Connecting to the website
     * @param theURL needs the URL from the Discord Server User
     * @return fileURL serves the URL from www.voca.ro/ConTEnT_001+1 (a url sent by a discord user)
     *      and transform that URL into another for getting the audio (like https://media1.vocaroo.com/mp3/ConTEnT_001+1).
     */
    private String getting_mp3_link(String theURL){

        //from https://voca.ro/13KPCoEARVxM I should receive https://media1.vocaroo.com/mp3/13KPCoEARVxM

        if (theURL.contains("https://vocaroo.com/")) {
            MP3_link = theURL.replace("https://vocaroo.com/","https://media1.vocaroo.com/mp3/");
        }else if (theURL.contains("https://voca.ro/")) {
            MP3_link = theURL.replace("https://voca.ro/", "https://media1.vocaroo.com/mp3/");
        }
        System.out.println("MP3_link: " + MP3_link);
        System.out.println("shoul be: " + "https://media1.vocaroo.com/mp3/13KPCoEARVxM");

        return MP3_link;
    }

    /**
     * Generating and getting the path of our MP3 file
     * @param linkHref
     * @return the path to our MP3 file
     */
    private String gettingVocarooPath(String linkHref) throws IOException {

        DownloadingAudioFromVocaroo_3 downloadingAudio = new DownloadingAudioFromVocaroo_3();

        String filePath = downloadingAudio.DownloadingAudioFromVocaroo_3(linkHref);
        System.out.println("filePath: " + filePath);

        return filePath;
    }

}
