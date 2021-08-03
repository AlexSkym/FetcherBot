package wotd_scraping;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Scraper {

    //SpanishDict - Word of the Day
    private String theURL = "https://www.spanishdict.com/wordoftheday";

    Scraper()throws IOException{

        Document doc = Jsoup.connect(theURL).timeout(50000).get();

/*
        Elements body = doc.select("div._31oklKlS");
        //System.out.println(body.select("div._1qTEOKKt"));

        for (Element e:
             body.select("div._3iXmZ8Jd")) {
            String a = e.text();
            System.out.println(a);

        }
        System.out.println(body.select(".niIPzjCU").get(0).text());
*/
        //elements from the box of the word of the day.
        //Element element_WOTD = doc.select("div._31oklKlS .niIPzjCU").get(0);
        //Elements element_WOTD = doc.select("div.textWrapper--niIPzjCU");

                                                    //body div1 div1 div2 div1 div1 div1 div2 div1 div2
                                                    //body root>container>container2>
        //Element element_WOTD = doc.select("body div div div").get(1); //until the first div2
        //element_WOTD = element_WOTD.select("div div div div").get(1); //until the second div2
        //element_WOTD = element_WOTD.select("div div").get(1); //the last one, the third one

        //container--tCuBiPui
        Element element_WOTD = doc.select("body div#root").get(0); //until the first div2
        //element_WOTD.select("div").get(1);
        //element_WOTD.select("div").get(1);
        element_WOTD.child(0);
        //element_WOTD.getElementsByTag("DIV").get(1);
        //element_WOTD.select("div").get(0);
        //element_WOTD.select("div").get(0);
        //element_WOTD.select("div").get(0);
        //element_WOTD.select("div").get(1);

        System.out.println("hola 34-Scraper.java. Class name: " + element_WOTD.childrenSize());
        System.out.println("hola 34-Scraper.java. Id name: " + element_WOTD.id());
        //Saving the words in the Class "Item_FromXML_ToXML" to send them latter in WOTD class
        Item item = new Item();

        //the word (spanish and english)
        item.setSubtitle_spanishWord(element_WOTD.select("a").text());
        System.out.println("un item más: "+ element_WOTD.select("a").get(0).text());
        item.setSubtitle_englishWord(element_WOTD.select("._3iXmZ8Jd").text());

        //the examples 1 (spanish and english)
        Element theExamples1 = element_WOTD.select("ol li").get(0);
        item.setSpanishExample_1(theExamples1.select("div").get(0).text());
        item.setEnglishExample_1(theExamples1.select("div").get(1).text());
        System.out.println("example1 Eng: "+theExamples1.select("div").get(1).text());

        //the examples 2 (spanish and english)
        Element theExamples2 = element_WOTD.select("ol li").get(1);
        item.setSpanishExample_2(theExamples2.select("div").get(0).text());
        item.setEnglishExample_2(theExamples2.select("div").get(1).text());
        System.out.println("example2 Eng: "+theExamples2.select("div").get(1).text());

    }


}
