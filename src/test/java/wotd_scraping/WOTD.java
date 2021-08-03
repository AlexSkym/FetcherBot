package wotd_scraping;

import java.io.IOException;

public class WOTD {     //class for dates treatment

    static private String title;
    static private String subtitle_SPA;
    static private String subtitle_ENG;
    static private String examples;
    static private String ex1_SPA;
    static private String ex1_ENG;
    static private String ex2_SPA;
    static private String ex2_ENG;

    static String everything;

    WOTD(){

    }

    public static void main(String[] args) throws IOException {

        Scraper scraper = new Scraper();

        Item item = new Item();

        title = "**"+ item.getTitle_wordOfTheDay() +"**";
        subtitle_SPA = "["+ item.getSubtitle_spanishWord() +"]";
        subtitle_ENG = "["+ item.getSubtitle_englishWord() +"]";
        examples = item.getTitleExample().toUpperCase();
        ex1_SPA = "**"+ item.getSpanishExample_1() + "**";
        ex1_ENG = item.getEnglishExample_1();
        ex2_SPA = "**"+ item.getSpanishExample_2() +"**";
        ex2_ENG = item.getEnglishExample_2();

        everything = "Everything: " +
                title+"\n" +
                "\n" +
                subtitle_SPA+"\n" +
                subtitle_ENG+"\n" +
                "\n" +
                "\n" +
                examples+"\n" +
                "\n" +
                "\t"+ex1_SPA+"\n" +
                "\t"+ex1_ENG+"\n" +
                "\n" +
                "\t"+ex2_SPA+"\n" +
                "\t"+ex2_ENG+"\n";

        System.out.println(everything);

    }


}
