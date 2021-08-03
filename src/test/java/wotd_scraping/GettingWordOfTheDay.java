package wotd_scraping;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.awt.*;
import java.io.IOException;

public class GettingWordOfTheDay {

    public static EmbedBuilder GettingWordOfTheDay(EmbedBuilder embedBuilder, Role role) throws IOException {

        String theURL = "https://www.spanishdict.com/wordoftheday";
        Document doc = Jsoup.connect(theURL).timeout(50000).get();

        Element element_WOTD = doc.select("div[class^=\"cont\"]").get(0); //the first only element of the square I need

        //Printing the elements to console.
        printingElements(element_WOTD);


        //sending the whole info to "item" class
        Item item = new Item();
                //System.out.println(item.toString());
        sendingElementsTo_ItemClass(element_WOTD, item);

        return embedMessage(embedBuilder, item, role);
    }

    private static void printingElements(Element element_WOTD){
        System.out.println("childrenSize: " + element_WOTD.childrenSize());
        System.out.println("classname: " + element_WOTD.className());
        //Saving the words in the Class "Item_FromXML_ToXML" to send them latter in WOTD class

        System.out.println();

        //Calendar
        System.out.println("Month: " + element_WOTD.select("div[class*='month-']").get(0).text());
        System.out.println("Day: " + element_WOTD.select("div[class*='day-']").get(0).text());

        //the word (spanish and english)
        System.out.println("WOTD: "+ element_WOTD.select("h3").get(0).text());
        System.out.println("Translation: "+ element_WOTD.select("div[class^=\"translation\"]").get(0).text());

        System.out.println();

        //the examples 1 (spanish and english)
        Element theExamples1 = element_WOTD.select("ol li").get(0);
        System.out.println("example1 Esp: "+theExamples1.select("div").get(0).text());
        System.out.println("example1 Eng: "+theExamples1.select("div").get(1).text());

        System.out.println();

        //the examples 2 (spanish and english)
        Element theExamples2 = element_WOTD.select("ol li").get(1);
        System.out.println("example2 Esp: "+theExamples1.select("div").get(0).text());
        System.out.println("example2 Eng: "+theExamples2.select("div").get(1).text());

    }

    /** This method maybe should be deleted because of the "toString()" method that is in Item_FromXML_ToXML.class
     *
     * @param element_WOTD it's an element which has the HTML source of https://www.spanishdict.com/wordoftheday
     * @param item it's where the elements previously recollected will be saved.
     */
    private static void sendingElementsTo_ItemClass(Element element_WOTD, Item item){

        //CALENDAR
        item.setSubtitle_month(element_WOTD.select("div[class*='month-']").get(0).text());
        item.setSubtitle_day(element_WOTD.select("div[class*='day-']").get(0).text());

        //WORDS

        //the word (spanish and english)
        item.setSubtitle_spanishWord(element_WOTD.select("h3").get(0).text());
        item.setSubtitle_englishWord(element_WOTD.select("div[class^=\"translation\"]").get(0).text());

        //EXAMPLES

        //the examples 1 (spanish and english)
        Element theExamples1 = element_WOTD.select("ol li").get(0);
        item.setSpanishExample_1(theExamples1.select("div").get(0).text());
        item.setEnglishExample_1(theExamples1.select("div").get(1).text());
        //the examples 2 (spanish and english)
        Element theExamples2 = element_WOTD.select("ol li").get(1);
        item.setSpanishExample_2(theExamples2.select("div").get(0).text());
        item.setEnglishExample_2(theExamples2.select("div").get(1).text());
    }

    /** Generate an embedMessage getting the items of Item_FromXML_ToXML.class
     *
     * @param embedBuilder: I should got this variable from SubmainWOTD.class
     * @param item: This is an instance from Item_FromXML_ToXML.class
     * @param role it's used to name the WOTD_role
     */
    private static EmbedBuilder embedMessage (EmbedBuilder embedBuilder, Item item, Role role){

        //embedBuilder.setAuthor("https://www.spanishdict.com/wordoftheday", "https://www.spanishdict.com/wordoftheday", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.DNlNvnHYVHUd06YhKEj_3AAAAA%26pid%3DApi&f=1");
        embedBuilder.setAuthor("Spanish-English Community Server", "https://www.spanishdict.com/wordoftheday");
        //https://www.spanishdict.com/wordoftheday

        //embedBuilder.setTitle("> " + item.getTitle_wordOfTheDay() + " - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")","https://thumbs.gfycat.com/LimitedEvilCaribou-size_restricted.gif");
        //embedBuilder.setTitle("" + "Word Of The Day" + " - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")","https://www.spanishdict.com/wordoftheday");
        embedBuilder.setTitle("" + "Palabra del D\u00EDa / Word of the Day" + " - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")","https://www.spanishdict.com/wordoftheday");

        embedBuilder.setThumbnail("https://i.pinimg.com/originals/b5/1d/b5/b51db59d977b0fa13e4ac1fe357e7190.gif");

        //embedBuilder.setColor(new Color(236, 95, 39));
        embedBuilder.setColor(new Color(32, 173, 47));

        /*
        embedBuilder.setDescription("" +

                "> __**" + item.getTitle_date() + "**__" + "\n" +
                "> _ _\tMonth: " + item.getSubtitle_month() + "\n" +
                "> _ _\tDay: " + item.getSubtitle_day() + "\n" +
                "" + "\n" +
                "> __**" + item.getTitle_wordOfTheDay() + "**__" + "\n" +
                "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                //"" + "\n" +
                "> <:YellowBallicon:821400709839978506> **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n" +
                //"> __**" + item.getTitleExample() + "**__" + "\n" +
                //"" + "\n" +
                "1. " + "**" + item.getSpanishExample_1() + "**\n" +
                "** ** \t" + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                "1. " + "**" + item.getSpanishExample_2() + "**\n" +
                "\t " + item.getEnglishExample_2() + "*\n" +
                "." + "\n" +
                "");
*/
        /*
        embedBuilder.setDescription("" +

                "__**" + item.getTitle_date() + "**__: " + item.getSubtitle_month() + " " + item.getSubtitle_day() + "\n" +
                "" + "\n" +
                "> __**" + item.getTitle_wordOfTheDay() + "**__" + "\n" +
                "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                //"" + "\n" +
                "> <:YellowBallicon:821400709839978506> **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n" +
                //"> __**" + item.getTitleExample() + "**__" + "\n" +
                //"" + "\n" +
                "1. " + "**" + item.getSpanishExample_1() + "**\n" +
                "" + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                "2. " + "**" + item.getSpanishExample_2() + "**\n" +
                "" + item.getEnglishExample_2() + "*\n" +
                "." + "\n" +
                "");
*/
        /*
        embedBuilder.setDescription("" +

                "__**" + item.getTitle_date() + "**: " + item.getSubtitle_month() + " " + item.getSubtitle_day() + "__\n" +
                "" + "\n" +
                "> __**" + item.getTitle_wordOfTheDay() + "**__" + "\n" +
                "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                //"" + "\n" +
                "> <:YellowBallicon:821400709839978506> **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n" +
                //"> __**" + item.getTitleExample() + "**__" + "\n" +
                //"" + "\n" +
                "**" + "Example" + " 1**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_1() + "\n" +
                "<:yellowdash:846640358116032572> " + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                "**" + "Example" + " 2**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_2() + "\n" +
                "<:yellowdash:846640358116032572>" + item.getEnglishExample_2() + "*\n" +
                "." + "\n" +
                "");
*/
        /*
        embedBuilder.setDescription("" +
                "> __**" + item.getTitle_wordOfTheDay() + "**__ - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")\n" +
                "> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                "> <:YellowBallicon:821400709839978506> **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n" +
                "**" + "Example" + " 1**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_1() + "\n" +
                "<:yellowdash:846640358116032572> " + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                "**" + "Example" + " 2**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_2() + "\n" +
                "<:yellowdash:846640358116032572>" + item.getEnglishExample_2() + "*\n" +
                "." + "\n" +
                "");
*/
        /*
        embedBuilder.setDescription("" +
                //"> __**" + item.getTitle_date() + "**__ - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")\n" +
                //"> " + "\n" +
                "> <:PurpleBallicon:821400709677056050> **" + item.getSubtitle_spanishWord().substring(0, 1).toUpperCase() + item.getSubtitle_spanishWord().substring(1) + "**\n" +
                "> <:YellowBallicon:821400709839978506> **" + item.getSubtitle_englishWord().substring(0, 1).toUpperCase() + item.getSubtitle_englishWord().substring(1) + "**\n" +
                "" + "\n\n" +
                "**" + "Example" + " 1**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_1() + "\n" +
                "<:yellowdash:846640358116032572> " + item.getEnglishExample_1() + "\n" +
                "" + "\n" +
                "**" + "Example" + " 2**: " + "\n" +
                "<:purpledash:846641287129858048> "  + item.getSpanishExample_2() + "\n" +
                "<:yellowdash:846640358116032572>" + item.getEnglishExample_2() + "*\n" +
                "." + "\n" +
                "");
*/
        embedBuilder.setDescription("" +
                //"> __**" + item.getTitle_date() + "**__ - (" + item.getSubtitle_month() + " " + item.getSubtitle_day() + ")\n" +
                //"> Buen d\u00EDa! otro d\u00EDa m\u00E1s para practicar! \n" +
                //"> Good day! another day to practice! \n" +
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
                "<:purpledash:846641287129858048> **" + item.getSpanishExample_2() + "**\n" +
                "<:yellowdash:846640358116032572> " + item.getEnglishExample_2() + "\n" +
                "." + "\n" +
                "");

        embedBuilder.setFooter("Practice until you have it.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("Practice until you get it right.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("Let's practice our target words.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("Practice until you can't get it wrong.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("Don't practice until you get it right, practice until you can't get it wrong.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("Practice everytime you get a chance.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("The more you sweat in practice, the less you bleed in battle.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");
        //embedBuilder.setFooter("The more you practice, the better you get.","https://i.pinimg.com/originals/43/d3/a3/43d3a3a86a32e7058384324132685dca.gif");

        return embedBuilder;
    }
}
