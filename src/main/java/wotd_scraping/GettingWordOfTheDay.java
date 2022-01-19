package wotd_scraping;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.IOException;

public class GettingWordOfTheDay {


    public static EmbedBuilder GettingWordOfTheDay(EmbedBuilder embedBuilder, Role role, String theURL, int whatWebSite) throws IOException {

        Document doc = Jsoup.connect(theURL).timeout(6000).get();

        Element theElementINeed = null;

        if (whatWebSite == 1) {
            theElementINeed = theElementIneed(doc, "today");
        }else if (whatWebSite == 2){
            theElementINeed = theElementIneed(doc, "hoy");
        }

        //sending the whole info to "item" class
        Item item = new Item();
        sendingElementsTo_ItemClass(theElementINeed, item);

        //spanishDict.com
        if (whatWebSite == 1) {
            return embedMessageSpa1(embedBuilder, item, role, theURL); //calling the method, returning an embed
        }
        //ingles.com
        else {
            return embedMessageEng2(embedBuilder, item, role, theURL); //calling the method, returning an embed
        }

    }

    /**
     *
     * @param doc This is the document that contains the HTML scraped before.
     * @return TheElementINeed (an Jsoup Element that contains a div that contains the date, words and examples)
     */
    private static Element theElementIneed(Document doc, String hoy_or_today){


        /* Here I'm looking for the div that contains the text "Today", to get latter the div
         *  that contains the word of the day.
         */
        Elements elements = doc.select("body div");
        Element element_today = null;
        String stringTheClassName = "";

        for (Element element :
                elements) {

            if(element.text().toLowerCase().equalsIgnoreCase(hoy_or_today)){
                element_today = element;
            }
        }
        System.out.println("class name that contains the word 'Today/Hoy': " + stringTheClassName);

        /*
         * Looking for the third parent of that element I got before.
         */
        Element theElementINeed = element_today;
        for (int i = 0; i < 3; i++) {
            theElementINeed = theElementINeed.parent();
        }
        System.out.println("the class name of theElementINeed: " + theElementINeed.className());
        //System.out.println("the class name of theElementINeed: " + theElementINeed.attr("class"));

        return theElementINeed;
    }


    /** This method maybe should be deleted because of the "toString()" method that is in Item.class
     *
     * @param theElementINeed it's an element which has the HTML source of https://www.spanishdict.com/wordoftheday
     * @param item it's where the elements previously recollected will be saved.
     */
    private static void sendingElementsTo_ItemClass(Element theElementINeed, Item item){

        ScrapingSites scrapingSites = new ScrapingSites(theElementINeed);

        //CALENDAR
        item.setSubtitle_month(scrapingSites.month);
        item.setSubtitle_day(scrapingSites.day);

        //WORDS

        //the word (spanish and english)
        item.setSubtitle_spanishWord(scrapingSites.word_SP);
        item.setSubtitle_englishWord(scrapingSites.word_EN);

        //EXAMPLES

        //the examples 1 (spanish and english)
        item.setSpanishExample_1(scrapingSites.ex1_Word_SP);
        item.setEnglishExample_1(scrapingSites.ex1_Word_EN);
        //the examples 2 (spanish and english)
//        item.setSpanishExample_2(scrapingSites.ex2_Word_SP);
//        item.setEnglishExample_2(scrapingSites.ex2_Word_EN);

    }

    /** Generate an embedMessage getting the items of Item.class
     *  @param embedBuilder : I should got this variable from SubmainWOTD.class
     * @param item : This is an instance from Item.class
     * @param role it's used to name the WOTD_role
     */
    private static EmbedBuilder embedMessageSpa1(EmbedBuilder embedBuilder,
                                             Item item, Role role,
                                             String theURL){

        embedBuilder.setAuthor("Spanish-English Community Server", theURL);

        embedBuilder.setTitle("" + "Palabra del D\u00EDa / Word of the Day" + " - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")",""+theURL);

        embedBuilder.setThumbnail("https://i.pinimg.com/originals/b5/1d/b5/b51db59d977b0fa13e4ac1fe357e7190.gif");

        embedBuilder.setColor(new Color(32, 173, 47));

        embedBuilder.setDescription("" +
                "> " + role.getAsMention() + "\n" +
                "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> [ES] **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                //  "> " + "\n" +
                "> <:YellowBallicon:821400709839978506> [EN] **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n\n" +
                //"**" + "Example" + " 1**: " + "\n" +
                "<:purpledash:846641287129858048> **" + item.getSpanishExample_1() + "**\n" +
                "<:yellowdash:846640358116032572> " + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                //"**" + "Example" + " 2**: " + "\n" +
//                "<:purpledash:846641287129858048> **" + item.getSpanishExample_2() + "**\n" +
//                "<:yellowdash:846640358116032572> " + item.getEnglishExample_2() + "\n" +
//                "." + "\n" +
                "");

        embedBuilder.setFooter("Practice until you have it.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");

        return embedBuilder;
    }


    /** Generate an embedMessage getting the items of Item.class
     *  @param embedBuilder : I should got this variable from SubmainWOTD.class
     * @param item : This is an instance from Item.class
     * @param role it's used to name the WOTD_role
     */
    private static EmbedBuilder embedMessageEng2(EmbedBuilder embedBuilder,
                                                 Item item, Role role,
                                                 String theURL){

        embedBuilder.setAuthor("Spanish-English Community Server", theURL);

        embedBuilder.setTitle("" + "Palabra del D\u00EDa / Word of the Day" + " - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")", ""+theURL);

        embedBuilder.setThumbnail("https://i.pinimg.com/originals/b5/1d/b5/b51db59d977b0fa13e4ac1fe357e7190.gif");

        embedBuilder.setColor(new Color(32, 173, 47));

        embedBuilder.setDescription("" +
                "> " + role.getAsMention() + "\n" +
                "> " + "\n" +
                "> <:YellowBallicon:821400709839978506> [EN] **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                //  "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> [ES] **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n\n" +
                //"**" + "Example" + " 1**: " + "\n" +
                "<:yellowdash:846640358116032572> **" + item.getEnglishExample_1() + "**\n" +
                "<:purpledash:846641287129858048> " + item.getSpanishExample_1() + "\n" +
                "" + "\n" +
                //"**" + "Example" + " 2**: " + "\n" +
//                "<:yellowdash:846640358116032572> **" + item.getEnglishExample_2() + "**\n" +
//                "<:purpledash:846641287129858048> " + item.getSpanishExample_2() + "\n" +
//                "." + "\n" +
                "");

        embedBuilder.setFooter("Practice until you have it.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");

        return embedBuilder;
    }
}
