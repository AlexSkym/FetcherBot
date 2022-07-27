package google_sheets.library;

import discord_bot.submains.SubmainTextFetching;

public class TreatingCommands {

PreparingTextsToBeSent textsToBeSent = new PreparingTextsToBeSent();

    public TreatingCommands(){}


    /**
     * Method made to create the full caller name of the paragraph in Google Sheet
     *
     * @param command it should contain something like: ".,ftxt en e".
     *                      Also, that parameter is received by this.getContent();
     * @return name_of_sheet changed to: "English Easy !E24"
     *                      (..."!E" is the Row number inside the Google Sheet).
     */
    public String getTreatedText_fromCommand(String command) {
        String name_of_sheet = "";

        //For a full text or empty if wrong
        if (language_treatment(command).equals("English")) { //English
            name_of_sheet = "English";
            if (level_treatment(command).equals("Easy")) { //Easy
                name_of_sheet += " - Easy!E" + LastParagraphsPOJO.getNum_of_paragraph_eng_easy();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_eng_easy() + "; Eng - Easy ";
            } else if (level_treatment(command).equals("Intermediate")) {
                name_of_sheet += " - Intermediate!E" + LastParagraphsPOJO.getNum_of_paragraph_eng_intermediate();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_eng_intermediate() + "; Eng - Intermediate ";
            } else if (level_treatment(command).equals("Advanced")) {
                name_of_sheet += " - Advanced!E" + LastParagraphsPOJO.getNum_of_paragraph_eng_advanced();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_eng_advanced() + "; Eng - Advanced";
            }
        }
        else
        if (language_treatment(command).equals("Spanish")) { //Spanish
            name_of_sheet = "Spanish";
            if (level_treatment(command).equals("Easy")) { //Easy
                name_of_sheet += " - Easy!E" + LastParagraphsPOJO.getNum_of_paragraph_esp_easy();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_esp_easy() + "; Esp - Easy ";
            } else if (level_treatment(command).equals("Intermediate")) { //Intermediate
                name_of_sheet += " - Intermediate!E" + LastParagraphsPOJO.getNum_of_paragraph_esp_intermediate();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_esp_intermediate() + "; Esp - Intermediate ";
            } else if (level_treatment(command).equals("Advanced")) { //Advanced
                name_of_sheet += " - Advanced!E" + LastParagraphsPOJO.getNum_of_paragraph_esp_advanced();        //example ended: "English - Easy!E2"
                SubmainTextFetching.textForCounting = LastParagraphsPOJO.getNum_of_paragraph_esp_advanced() + "; Esp - Advanced ";
            }
        }

        //just when "help" is active "theCounter" will not be shown
        if (!SubmainTextFetching.textForCounting.equals("") || !SubmainTextFetching.textForCounting.equals(null)) {
            SubmainTextFetching.textForCounting = "Counter: " + SubmainTextFetching.textForCounting;
        }

        return name_of_sheet;
    }






//    -------------------------
//    Utilities for this class
//    -------------------------

    /* language (sp,en)
     * Get the texts depending on / according to the language
     *
     * 	requirements:
     *			+ "english, eng, en, inglés, ing, in\n"		en,in
     *			+ "spanish, spa, sp, español, esp, es\n"	sp,es
     *
     */
    protected String language_treatment (String command) {
        //Nota= creo que debo agregar cada parte
        //(es,esp,español) para evitar que se invoque el comando
        //escibiendo cosas como "esssmeralda" en vez de "español"
        //Spanish language
        if ( (command.contains(" es ")) || ( command.contains(" esp ")) || ( command.contains(" español "))
                || ( command.contains(" sp ")) || ( command.contains(" spa ")) || ( command.contains(" spanish "))) {
            return "Spanish";
        }  //English language
        else if ( (command.contains(" en ")) || ( command.contains(" eng ")) || ( command.contains(" english "))
                || ( command.contains(" in ")) || ( command.contains(" ing ")) || ( command.contains(" inglés ")) || ( command.contains(" ingles "))) {
            return "English";
        }  //If nothing work, get help
        else return "error";
    }

    /* level (e,i,a)
     * Get the texts depending on / according to the level
     *
     *  Requirements:
     *  		+ "easy, e, fácil, f\n"											e,f
     *			+ "medium, medio, m, intermediate, intermedio, i\n"				m,i
     *			+ "advanced, avanzado, a, dificult, difícil, d, hard, h\n"		a,d,h
     *
     */
    protected String level_treatment (String command) {	//Algo por reparar, sin dudas:

        int n;
        n = command.indexOf(" ")+1;
        String commandcutted = command.substring(n);
        n = commandcutted.indexOf(" ")+1;
        commandcutted = commandcutted.substring(n);
        System.out.println("commandcutted: #" +commandcutted);
        //Easy level
        if ( (commandcutted.equalsIgnoreCase("easy")) || ( commandcutted.equalsIgnoreCase("e"))
                || ( commandcutted.equalsIgnoreCase("fácil")) || ( commandcutted.equalsIgnoreCase("f")) || ( commandcutted.equalsIgnoreCase("facil"))) {
            return "Easy";
        }  //Intermediate level
        else if ( (commandcutted.equalsIgnoreCase("medio")) || ( commandcutted.equalsIgnoreCase("medium")) || ( commandcutted.equalsIgnoreCase("m"))
                || ( commandcutted.equalsIgnoreCase("intermediate")) || ( commandcutted.equalsIgnoreCase("intermedio")) || ( commandcutted.equalsIgnoreCase("i"))) {
            return "Intermediate";
        }  //difficult level
        else if ( (commandcutted.equalsIgnoreCase("difficult")) || (commandcutted.equalsIgnoreCase("dificil")) || (commandcutted.equalsIgnoreCase("d"))
                || (commandcutted.equalsIgnoreCase("advanced")) || (commandcutted.equalsIgnoreCase("avanzado")) || (commandcutted.equalsIgnoreCase("a"))
                || (commandcutted.equalsIgnoreCase("hard")) || (commandcutted.equalsIgnoreCase("h"))) {
            return "Advanced";
        }  //If nothing work, get help
        else return "error";
    }

    /* sizes (Not made)
     * Here I go to classify / sort out the texts in different sizes
     *
     * Requirements
     * 			"short, small, corto, pequeño, s, c, p\n"
     *			"normal, medium, normal, mediano, n, m\n"
     *			"long, large, extense, vast, largo, extenso, vasto, l, e, v\n"
     */
    protected String size_treatment (String story) {



        return "";
    }



}
