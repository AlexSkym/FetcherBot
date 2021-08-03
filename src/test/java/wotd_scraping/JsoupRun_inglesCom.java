package wotd_scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class JsoupRun_inglesCom {

    static String theURL = "https://www.ingles.com/palabradeldia";

    JsoupRun_inglesCom() throws IOException {
        //nothing for now.

    }

    public static void main(String[] args) throws IOException{

        Document doc = Jsoup.connect(theURL).timeout(6000).get();


        /* Here I'm looking for the div that contains the text "Today", to get latter the div
         *  that contains the word of the day.
         *
         */
        Elements elements = doc.select("body div");
        Element element_today = null;
        String stringTheClassName = "";

        for (Element element :
                elements) {

            if(element.text().toLowerCase().contains("hoy")){
                element_today = element;
            }
        }
        System.out.println("class name that contains the word 'Hoy': " + stringTheClassName);



        /*
         * Looking for the third parent of that element I got before.
         */
        Element theElementINeed = element_today;
        for (int i = 0; i < 3; i++) {
            theElementINeed = theElementINeed.parent();
        }
        System.out.println("the class name of theElementINeed: " + theElementINeed.className());
        //System.out.println("the class name of theElementINeed: " + theElementINeed.attr("class"));


        System.out.println("\nDATE");
        /*
         * Getting Date
         */
        Element theElementINeed_date = theElementINeed.child(0);
        System.out.println("date class name: " + theElementINeed_date.className());

        //day
        Element element_sibling =
            receiving_day(theElementINeed_date);
        //month
        receiving_Month(element_sibling);




        System.out.println("\nWORDS");
        /*
         * Getting words
         */
        Element theElementINeed_wotd = theElementINeed.child(1);
        System.out.println("wotd class name: " + theElementINeed_wotd.className());

        receivingSpanishWord(theElementINeed_wotd);
        receivingEnglishWord(theElementINeed_wotd);




        System.out.println("\nEXAMPLES");
        /*
         * Examples Elements
         */
        Element element_examples = theElementINeed_wotd.child(3);

        receivingSpaEx1(element_examples);
        receivingEngEx1(element_examples);

        receivingSpaEx2(element_examples);
        receivingEngEx2(element_examples);


    }


    private static Element receiving_day(Element theElementINeed_date){

        /*
         * Getting the day
         */

        Element element_day = null;
        //get the element and the name of the class that contains a number
        for (Element element :
                theElementINeed_date.select("div")) {
            // if the string contains a number... save the class name
            char[] chars = element.text().toLowerCase().toCharArray();
            for(char c : chars){
                if(Character.isDigit(c)){
                    element_day = element;
                }
            }
        }
        System.out.println("class name of the day: " + element_day.className());
        System.out.println("day: "+ element_day.text());

        return element_day;
    }

    /**
     * To receive the Month of the date
     * @param element_day it needs his sibling
     * @return the month
     */
    private static Element receiving_Month(Element element_day){

        /*
         * Getting the month starting from the css #day class
         */

        Element element_month = null;
        //get the element and the name of the class that contains a number
        element_month = element_day.firstElementSibling();
        System.out.println("class name of the month: " + element_month.className());
        System.out.println("month: "+ element_month.text());

        return element_month;
    }

    private static void receivingSpanishWord(Element theElementINeed_wotd){

        /*
         * Spanish Word
         */

        Element spanishWord = null;
        spanishWord = theElementINeed_wotd.child(0);
        System.out.println("englishWord: " + spanishWord.text());

    }

    private static void receivingEnglishWord(Element theElementINeed_wotd){

        /*
         * English Word
         */

        Element englishWord = null;
        englishWord = theElementINeed_wotd.child(1);
        System.out.println("spanishWord: " + englishWord.text());

    }

    private static void receivingSpaEx1(Element element_examples){
        /*
         * Spanish Example 1
         */

        Element spanishExample1 = null;
        spanishExample1 = element_examples.select("li div").first();
        System.out.println("spanishExample1: " + spanishExample1.text());

    }

    private static void receivingEngEx1(Element element_examples){

        /*
         * English Example 1
         */

        Element englishExample1 = null;
        englishExample1 = element_examples.select("li div").get(1);
        System.out.println("englishExample1: " + englishExample1.text());

    }

    private static void receivingEngEx2(Element element_examples){
        /*
         * Spanish Example 2
         */

        Element spanishExample2 = null;
        spanishExample2 = element_examples.select("li div").get(2);
        System.out.println("spanishExample2: " + spanishExample2.text());

    }

    private static void receivingSpaEx2(Element element_examples){
        /*
         * English Example 2
         */

        Element englishExample2 = null;
        englishExample2 = element_examples.select("li div").get(3);
        System.out.println("englishExample2: " + englishExample2.text());

    }


}
