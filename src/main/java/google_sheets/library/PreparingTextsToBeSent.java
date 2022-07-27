package google_sheets.library;

import discord_bot.submains.SubmainTextFetching;
import google_sheets.SheetsQuickstart;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class PreparingTextsToBeSent {

    /**
     * Constructor
     */
    public PreparingTextsToBeSent(){}

    /**
     * Getting the ending text
     * @param name_of_sheet it should contain something like: English Easy !E24
     * @return getHelp() or the final text from a Google Sheet cell
     */
    public String getEndingText(String name_of_sheet) {

        //just when "help" is active "theCounter" will not be shown
        if (!SubmainTextFetching.textForCounting.equals("") || !SubmainTextFetching.textForCounting.equals(null)) {
            SubmainTextFetching.textForCounting = "Counter: "+ SubmainTextFetching.textForCounting;
        }

        //Starting SheetsQuickstart.class to receive the content from the specified cell of Google Sheet
        SheetsQuickstart sheetsQuickstart = new SheetsQuickstart();
        sheetsQuickstart.setMyOwnRange(name_of_sheet);
        try {
            SheetsQuickstart.Starting(name_of_sheet);
        } catch (IOException e) {
            System.out.println("un error 1");
            SubmainTextFetching.textForCounting = "";
            e.printStackTrace();
            return getHelp();
        } catch (GeneralSecurityException e) {
            System.out.println("un error 2");
            SubmainTextFetching.textForCounting = "";
            e.printStackTrace();
            return getHelp();
        }
        System.out.println("name_of_sheet: "+name_of_sheet);
        System.out.println("name_of_sheet1: "+ name_of_sheet.split("!E")[0]);
        String name_of_sheet_split = name_of_sheet.split("!E")[0];


        //Figuring out if the cell from Google Sheet is not empty
        List<List<Object>> values = sheetsQuickstart.getValues();
        String theEndingText = "";
        //If it canNOT find a text...
        if (values == null || values.isEmpty()) {
            System.out.println("No data found.");
            theEndingText = "";
            //theText = "No data found";
            theEndingText = "Pido disculpas, no encontré ningún dato.";

            //Restarting the counter of FetcherBot to "1"
            switch (name_of_sheet_split){
                case "Spanish - Easy" : LastParagraphsPOJO.setNum_of_paragraph_esp_easy(1);
                    break;
                case "Spanish - Intermediate" : LastParagraphsPOJO.setNum_of_paragraph_esp_intermediate(1);
                    break;
                case "Spanish - Advanced" : LastParagraphsPOJO.setNum_of_paragraph_esp_advanced(1);
                    break;
                case "English - Easy" : LastParagraphsPOJO.setNum_of_paragraph_eng_easy(1);
                    break;
                case "English - Intermediate" : LastParagraphsPOJO.setNum_of_paragraph_eng_intermediate(1);
                    break;
                case "English - Advanced" : LastParagraphsPOJO.setNum_of_paragraph_eng_advanced(1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + name_of_sheet);
            }
        }
        //If it can find the text...
        else {
            theEndingText = "";
            for (List row : values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                //System.out.printf("%s, %s\n", row.get(1), row.get(4));
                System.out.printf("%s\n", row.get(0));	//Esta expresa el número de la columna del exel, pero...
                //hay que recordar que estoy tratando con un Array,
                //entonces es directamente 0 la primer y única columna que nombré
                //y seleccioné .
                theEndingText += row;

                //kick off the '[' and ']'
                theEndingText = theEndingText.substring(1,theEndingText.length()-1);
                System.out.println("theText: " +theEndingText);
            }

            //Restarting the counter of FetcherBot to "1"
            switch (name_of_sheet_split){
                case "Spanish - Easy" : LastParagraphsPOJO.setNum_of_paragraph_esp_easy(LastParagraphsPOJO.getNum_of_paragraph_esp_easy()+1);
                    break;
                case "Spanish - Intermediate" : LastParagraphsPOJO.setNum_of_paragraph_esp_intermediate(LastParagraphsPOJO.getNum_of_paragraph_esp_intermediate()+1);
                    break;
                case "Spanish - Advanced" : LastParagraphsPOJO.setNum_of_paragraph_esp_advanced(LastParagraphsPOJO.getNum_of_paragraph_esp_advanced()+1);
                    break;
                case "English - Easy" : LastParagraphsPOJO.setNum_of_paragraph_eng_easy(LastParagraphsPOJO.getNum_of_paragraph_eng_easy()+1);
                    break;
                case "English - Intermediate" : LastParagraphsPOJO.setNum_of_paragraph_eng_intermediate(LastParagraphsPOJO.getNum_of_paragraph_eng_intermediate()+1);
                    break;
                case "English - Advanced" : LastParagraphsPOJO.setNum_of_paragraph_eng_advanced(LastParagraphsPOJO.getNum_of_paragraph_eng_advanced()+1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + name_of_sheet);
            }

        }

        System.out.println(theEndingText);

        return theEndingText;
    }


    /**
     * Get the global help for lost Users
     * @return "the helping guide text"
     */
    public String getHelp() {
        // TODO Auto-generated method stub

		/*
    	final String help = "```"
    			+ "Command: fetchText"
    			+ System.lineSeparator()
    			+ "\n"
    			+ "Aliases: .,fetchtext .,fetchtxt; .,ftxt; .,ftext"
    			+ "\n"
    			+ "Description: Gets a text based on the language and difficulty specified."
    			+ "\n"
    			//+ "Cooldown: 10 seconds"
    			//+ "\n"
    			+ "\n"
    			+ "Argument #1: The language of the text"
    			+ "\n"
    			+ "english, eng, en, inglés, ing, in\n"
    			+ "spanish, spa, sp, español, esp, es\n"
    			+ "\n"
    			+ "Argument #2: The difficulty of the text\n"
    			+ "easy, e, fácil, f\n"
    			+ "medium, m, intermediate, i, medio, intermedio\n"
    			+ "advanced, a, hard, h, dificult, d, avanzado, difícil\n"
    			//+ "\n"
    			//+ "Argument #3: The size of the text\n"
    			//+ "\n"
    			//+ "short, small, corto, pequeño, s, c, p\n"
    			//+ "normal, medium, normal, mediano, n, m\n"
    			//+ "long, large, extense, vast, largo, extenso, vasto, l, e, v\n"
				//
				// + "\n" + "\n" +
				// "in order to know the amount of paragraphs with normal (medium) size or amount of stories there are
				//       in each level of each language, type: ,ftxt amount paragraph/stories en/es e/i/a."
				// + "\n"
				//
    			+ "\n"
				+ "\n"
				+ "Example: <prefix><alias> <language> <level>\n"
				+ "         .,fetchtext English Easy)"
    			+ "\n"
				+ "\n"
    			+ "Get help typing the Alias + ' help',"
    			//+ " or Alias + ' more' if you have the required permits to edit or add stories"
    			+ "```";
    	*/

        final String help = "```md\n" +
                "Command: fetchText\n" +
                "\n" +
                "#Aliases: _Call the bot_\n" +
                ".,fetchtext .,fetchtxt; .,ftext; .,ftxt\n" +
                "\n" +
                "#Argument #1: _The language of the text_\n" +
                "english, eng, en, inglés, ing, in\n" +
                "spanish, spa, sp, español, esp, es\n" +
                "\n" +
                "#Argument #2: _The difficulty of the text_\n" +
                "easy, e, fácil, f\n" +
                "intermediate, intermedio, i, medium, medio, m\n" +
                "advanced, avanzado, a, dificult, difícil, d, hard, h\n" +
                "\n" +
                "\n" +
                "Example:   <alias> <language> <level>\n" +
                "#            .,ftxt English Easy\n" +
                "\n" +
                "\n" +
                "Get help typing the <Alias + ' help'>\n" +
                "Example            .,fetchtext help\n" +
                "```";


        return help;
    }

}
