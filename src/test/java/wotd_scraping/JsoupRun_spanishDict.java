package wotd_scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class JsoupRun_spanishDict {

    static String theURL = "https://www.spanishdict.com/wordoftheday";

    JsoupRun_spanishDict() throws IOException {
        //nothing for now.

    }

    public static void main(String[] args) throws IOException{

        Document doc = Jsoup.connect(theURL).timeout(6000).get();


        /* Here I'm looking for the div that contains the text "Today", to get latter the div
         *  that contains the word of the day.
         */
        Elements elements = doc.select("body div");
        Element element_today = null;
        String stringTheClassName = "";

        for (Element element :
                elements) {

            if(element.text().toLowerCase().contains("today")){
                element_today = element;
            }
        }
        System.out.println("class name that contains the word 'Today': " + stringTheClassName);

        /*
         * Looking for the third parent of that element I got before.
         */
        Element theElementINeed = element_today;
        for (int i = 0; i < 3; i++) {
            theElementINeed = theElementINeed.parent();
        }
        System.out.println("the class name of theElementINeed: " + theElementINeed.className());
        //System.out.println("the class name of theElementINeed: " + theElementINeed.attr("class"));






    }

}
