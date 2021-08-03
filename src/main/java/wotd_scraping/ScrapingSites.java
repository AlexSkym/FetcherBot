package wotd_scraping;

import org.jsoup.nodes.Element;

public class ScrapingSites {

    static String day = "";
    static String month = "";
    static String word_SP = "";
    static String word_EN = "";
    static String ex1_Word_SP = "";
    static String ex1_Word_EN = "";
    static String ex2_Word_SP = "";
    static String ex2_Word_EN = "";

    ScrapingSites(Element theElementINeed){

        Item item = new Item();



        //DATE
        Element theElementINeed_date = totalDate(theElementINeed);

        Element elementDay = receivingDay(theElementINeed_date);
        day = elementDay.text();
        month = receivingMonth(elementDay).text();

        //WORDS
        Element theElementINeed_words = totalWords(theElementINeed);
        totalExamples(theElementINeed);

        word_SP = receivingSpanishWord(theElementINeed_words).text();
        word_EN = receivingEnglishWord(theElementINeed_words).text();

        Element theElementINeed_examples = totalExamples(theElementINeed);

        ex1_Word_SP = receivingSpaEx1(theElementINeed_examples).text();
        ex1_Word_EN = receivingEngEx1(theElementINeed_examples).text();

        ex2_Word_SP = receivingSpaEx2(theElementINeed_examples).text();
        ex2_Word_EN = receivingEngEx2(theElementINeed_examples).text();

    }



    /*
    Every word to be send to Item class
     */



    private static Element totalDate(Element theElementINeed){

        System.out.println("\nDATE");


        Element theElementINeed_date = theElementINeed.child(0);
        System.out.println("date class name: " + theElementINeed_date.className());


        return theElementINeed_date;
    }

    private static Element totalWords (Element theElementINeed){

        System.out.println("\nWORDS");


        Element theElementINeed_wotd = theElementINeed.child(1);
        System.out.println("wotd class name: " + theElementINeed_wotd.className());

        return theElementINeed_wotd;
    }

    private static Element totalExamples (Element theElementINeed){

        System.out.println("\nEXAMPLES");


        Element theElementINeed_wotd = theElementINeed.child(1);

        Element theElementINeed_examples = theElementINeed_wotd.child(3);

        return theElementINeed_examples;
    }







    /*
    Every date scraped
     */


    private static Element receivingDay(Element theElementINeed_date){

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
    private static Element receivingMonth(Element element_day){

        Element element_month = null;
        //get the element and the name of the class that contains a number
        element_month = element_day.firstElementSibling(); //element_day is the sibling of element_month
        System.out.println("class name of the month: " + element_month.className());
        System.out.println("month: "+ element_month.text());

        return element_month;
    }

    private static Element receivingSpanishWord(Element theElementINeed_wotd){

        Element spanishWord = null;
        spanishWord = theElementINeed_wotd.child(0);
        System.out.println("spanishWord: " + spanishWord.text());

        return spanishWord;
    }

    private static Element receivingEnglishWord(Element theElementINeed_wotd){

        Element englishWord = null;
        englishWord = theElementINeed_wotd.child(1);
        System.out.println("englishWord: " + englishWord.text());

        return englishWord;
    }

    private static Element receivingSpaEx1(Element element_examples){

        Element spanishExample1 = null;
        spanishExample1 = element_examples.select("li div").first();
        System.out.println("spanishExample1: " + spanishExample1.text());

        return spanishExample1;
    }

    private static Element receivingEngEx1(Element element_examples){

        Element englishExample1 = null;
        englishExample1 = element_examples.select("li div").get(1);
        System.out.println("englishExample1: " + englishExample1.text());

        return englishExample1;
    }

    private static Element receivingSpaEx2(Element element_examples){

        Element spanishExample2 = null;
        spanishExample2 = element_examples.select("li div").get(2);
        System.out.println("spanishExample2: " + spanishExample2.text());

        return spanishExample2;
    }

    private static Element receivingEngEx2(Element element_examples){

        Element englishExample2 = null;
        englishExample2 = element_examples.select("li div").get(3);
        System.out.println("englishExample2: " + englishExample2.text());

        return englishExample2;
    }



}
