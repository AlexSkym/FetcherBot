/*
 * This Java google_sheets file was generated by the Gradle 'init' task.
 */
package google_sheets;

import discord_bot.SubmainTextFetching;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Library {

	public boolean someLibraryMethod() {
		return true;
	}

	/*
	 * Giving the context to "SubmainTextFetching.java" in order to show it
	 * Here we will do some choose a little bit what text will be sent
	 */
	public String getContext(String command) {

		String name_of_sheet = "";

		//return SheetsQuickstart.theText;
		if (language_treatment(command).equals("sp")) {
			name_of_sheet = "Spanish";
		} else if (language_treatment(command).equals("en")) {
			name_of_sheet = "English";
		} else {
			return getHelp();
		}
		;

		if (level_treatment(command).equals("e")) {
			name_of_sheet += " - Easy";
		} else if (level_treatment(command).equals("i")) {
			name_of_sheet += " - Intermediate";
		} else if (level_treatment(command).equals("a")) {
			name_of_sheet += " - Advanced";
		} else {
			return getHelp();
		}

		/* Note: I don't need to put a if-else here because I'm using the return word above to finish this method.
		 * otherwise we could continue with the next part of the code of this method...
		 */
		return getEndText(name_of_sheet);
	}

	private String getEndText(String name_of_sheet) {

		if (name_of_sheet.contains("English")){

			if (name_of_sheet.contains("Easy")){
				SubmainTextFetching.num_of_paragraph_eng_easy += 1;   //preparing the next paragraph;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_eng_easy;        //example ended: "English - Easy!E2"
				System.out.println("easy: "+name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_eng_easy-1) + "; Eng - Easy ";
			}else if(name_of_sheet.contains("Intermediate")){
				SubmainTextFetching.num_of_paragraph_eng_intermediate +=1;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_eng_intermediate;        //example ended: "English - Easy!E2"
				System.out.println("int: "+name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_eng_intermediate-1) + "; Eng - Intermediate ";
			}else if (name_of_sheet.contains("Advanced")) {
				SubmainTextFetching.num_of_paragraph_eng_advanced += 1;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_eng_advanced;        //example ended: "English - Easy!E2"
				System.out.println("adv: "+name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_eng_advanced-1) + "; Eng - Advanced";
			}
		}
		if (name_of_sheet.contains("Spanish")) {
			if (name_of_sheet.contains("Easy")) {
				SubmainTextFetching.num_of_paragraph_esp_easy += 1;   //preparing the next paragraph;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_esp_easy;        //example ended: "English - Easy!E2"
				System.out.println(name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_esp_easy-1) + "; Esp - Easy ";
			} else if (name_of_sheet.contains("Intermediate")) {
				SubmainTextFetching.num_of_paragraph_esp_intermediate += 1;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_esp_intermediate;        //example ended: "English - Easy!E2"
				System.out.println(name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_esp_intermediate-1) + "; Esp - Intermediate ";
			} else if (name_of_sheet.contains("Advanced")) {
				SubmainTextFetching.num_of_paragraph_esp_advanced += 1;
				name_of_sheet += "!E" + SubmainTextFetching.num_of_paragraph_esp_advanced;        //example ended: "English - Easy!E2"
				System.out.println(name_of_sheet);
				SubmainTextFetching.theCounter = (SubmainTextFetching.num_of_paragraph_esp_advanced-1) + "; Esp - Advanced ";
			}
		}

		//just when "help" is active "theCounter" will not be shown
		if (!SubmainTextFetching.theCounter.equals("") || !SubmainTextFetching.theCounter.equals(null)) SubmainTextFetching.theCounter = "Counter: "+ SubmainTextFetching.theCounter;


		SheetsQuickstart sheetsQuickstart = new SheetsQuickstart();
		sheetsQuickstart.setMyOwnRange(name_of_sheet);

		try {
			SheetsQuickstart.Starting();
		} catch (IOException e) {
			System.out.println("un error 1");
			SubmainTextFetching.theCounter = "";
			e.printStackTrace();
			return getHelp();
		} catch (GeneralSecurityException e) {
			System.out.println("un error 2");
			SubmainTextFetching.theCounter = "";
			e.printStackTrace();
			return getHelp();
		}
		System.out.println(sheetsQuickstart.getTheText());

		return sheetsQuickstart.getTheText();
	}

    
    // (help)
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
    			+ "english, eng, en, ingl�s, ing, in\n"
    			+ "spanish, spa, sp, espa�ol, esp, es\n"
    			+ "\n"
    			+ "Argument #2: The difficulty of the text\n"
    			+ "easy, e, f�cil, f\n"
    			+ "medium, m, intermediate, i, medio, intermedio\n"
    			+ "advanced, a, hard, h, dificult, d, avanzado, dif�cil\n"
    			//+ "\n"
    			//+ "Argument #3: The size of the text\n"
    			//+ "\n"
    			//+ "short, small, corto, peque�o, s, c, p\n"
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
				"english, eng, en, ingl�s, ing, in\n" +
				"spanish, spa, sp, espa�ol, esp, es\n" +
				"\n" +
				"#Argument #2: _The difficulty of the text_\n" +
				"easy, e, f�cil, f\n" +
				"intermediate, intermedio, i, medium, medio, m\n" +
				"advanced, avanzado, a, dificult, dif�cil, d, hard, h\n" +
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
    
    /* language (sp,en)
     * Get the texts depending on / according to the language
     * 
     * 	requirements: 				
     *			+ "english, eng, en, ingl�s, ing, in\n"		en,in
     *			+ "spanish, spa, sp, espa�ol, esp, es\n"	sp,es
     *
     */
    public String language_treatment (String command) {		//Nota= creo que debo agregar cada parte 
    														//(es,esp,espa�ol) para evitar que se invoque el comando 
    														//escibiendo cosas como "esssmeralda" en vez de "espa�ol"
    	   //Spanish language 
    	if ( (command.contains(" es ")) || ( command.contains(" esp ")) || ( command.contains(" espa�ol "))
    			 || ( command.contains(" sp ")) || ( command.contains(" spa ")) || ( command.contains(" spanish "))) {
			return "sp";
		}  //English language 
    	else if ( (command.contains(" en ")) || ( command.contains(" eng ")) || ( command.contains(" english "))
    			 || ( command.contains(" in ")) || ( command.contains(" ing ")) || ( command.contains(" ingl�s ")) || ( command.contains(" ingles "))) {
			return "en";
		}  //If nothing work, get help
    	else return "error";
    }
    
    /* level (e,i,a)
     * Get the texts depending on / according to the level
     *  
     *  Requirements:
     *  		+ "easy, e, f�cil, f\n"											e,f
     *			+ "medium, medio, m, intermediate, intermedio, i\n"				m,i
     *			+ "advanced, avanzado, a, dificult, dif�cil, d, hard, h\n"		a,d,h
     * 
     */
    public String level_treatment (String command) {	//Algo por reparar, sin dudas:

    	int n;
		n = command.indexOf(" ")+1;
		String commandcutted = command.substring(n);
		n = commandcutted.indexOf(" ")+1;
		commandcutted = commandcutted.substring(n);
		System.out.println("commandcutted: #" +commandcutted);
		//Easy level
		if ( (commandcutted.equalsIgnoreCase("easy")) || ( commandcutted.equalsIgnoreCase("e"))
				|| ( commandcutted.equalsIgnoreCase("f�cil")) || ( commandcutted.equalsIgnoreCase("f")) || ( commandcutted.equalsIgnoreCase("facil"))) {
			return "e";
		}  //Intermediate level
		else if ( (commandcutted.equalsIgnoreCase("medio")) || ( commandcutted.equalsIgnoreCase("medium")) || ( commandcutted.equalsIgnoreCase("m"))
				|| ( commandcutted.equalsIgnoreCase("intermediate")) || ( commandcutted.equalsIgnoreCase("intermedio")) || ( commandcutted.equalsIgnoreCase("i"))) {
			return "i";
		}  //difficult level
		else if ( (commandcutted.equalsIgnoreCase("difficult")) || (commandcutted.equalsIgnoreCase("dificil")) || (commandcutted.equalsIgnoreCase("d"))
				|| (commandcutted.equalsIgnoreCase("advanced")) || (commandcutted.equalsIgnoreCase("avanzado")) || (commandcutted.equalsIgnoreCase("a"))
				|| (commandcutted.equalsIgnoreCase("hard")) || (commandcutted.equalsIgnoreCase("h"))) {
			return "a";
		}  //If nothing work, get help
		else return "error";
    }
    

	/* sizes (Not made) 
     * Here I go to classify / sort out the texts in different sizes
     * 
     * Requirements
     * 			"short, small, corto, peque�o, s, c, p\n"
     *			"normal, medium, normal, mediano, n, m\n"
     *			"long, large, extense, vast, largo, extenso, vasto, l, e, v\n"
     */
    public String sizes (String story) {
    	
    	
    	
    	return "";
    }
    
    /*
     * Add more texts (not made)
     * 		In order to get the correct paragraph/sentence, I'll count the points and lines
     * 
     * Until now this doesn't serve
     */
    public String j () {
    	
    	return "";
    }
	
}
