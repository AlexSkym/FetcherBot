package forvo_API.data_processes.fixedVariables;

public class AutomatedMessages {

    //constructor
    public AutomatedMessages(){ }

    /**
     * when you want to get help about how the bot for pronunciations works out.
     * @return return the help in a String.
     */
    public final String getHelp(){
        String stringHelp;

        stringHelp= "" +
                "```.,fetchPronunciation --help" + "      " +
                "[.,fp -h]" + "\n" +
                //"```.,help pronunciations" + "\n" +
                "" + "\n" +
                "simple command: " + "\n" +
                "*  \".,pronounce Hola\"" + "\n" +
                "*  \".,pronuncia Hello\"" + "\n" +
                "" + "\n" +
                "Optional commands:" + "\n" +
                "*  language/idioma:" + "\n" +
                "*       Español [esp / es] / Spanish [spa / sp]" + "\n" +
                "*       Inglés [ing / in] / English [eng / en]" + "\n" +
                "" + "\n" +
                "*  sex/sexo: \n" +
                "*       \"f\" (female/femenino)" + "\n" +
                "*       \"m\" (male/masculino)" + "\n" +
                "" + "\n" +
                "*  country/país: " + "\n"; //I'll complete this by using the next additions

        //countries:
        MapperEnumFixedVar mapper_urv = new MapperEnumFixedVar();


        stringHelp += "*       English countries:\n";
        //Getting the complexes of English countries
        for (int i = 0; i < mapper_urv.getAll_EnglishCountries_PrettyName().size()-1; i++) {
            stringHelp += "*        -" + " [" + mapper_urv.getAll_EnglishCountries_Abbreviations().get(i) + "] " + mapper_urv.getAll_EnglishCountries_PrettyName().get(i) + "\n";
        }
        stringHelp += "*\n"; //adding an line break

        //Getting the complexes of Spanish countries
        stringHelp += "*       Spanish countries:\n";
        for (int i = 0; i < mapper_urv.getAll_SpanishCountries_PrettyName().size()-1; i++) {
            stringHelp += "*        -" + " [" + mapper_urv.getAll_SpanishCountries_Abbreviations().get(i) + "] " + mapper_urv.getAll_SpanishCountries_PrettyName().get(i) + "\n";
        }
        stringHelp += "```";

        return stringHelp;
    }

    /**
     * When an audio have been sent successfully
     * @return String: "for you sir/miss"
     */
    public final String getSuccessMessage(){
        return "__For you sir/miss__.";
    }





    /*
    Errors:
     */

    /**
     * When the user string sentence is misspelled
     * @return String with an Error
     */
    public final String getMessageOfError_sentenceTypo(){
        return "Error:\n" +
                "*The sentence is misspelled.\n" +
                "*La oración está mal escrita.";
    }

    /**
     * When the word couldn't be found with the url
     * @return String with an Error
     */
    public final String getMessageOfError_wordNotFound(){
        return "Error:\n" +
                "*Word without audios.\n" +
                "*Palabra sin audios.";
    }

    /**
     * When the word couldn't be find within the specifications
     * @return String with an Error
     */
    public final String getMessageOfError_specsNotFound(){
        return  "Error:\n" +
                "*The audio could not be found within the given specs.\n"+
                "*El audio no se pudo encontrar dentro de las especificaciones dadas.";
    }

    /**
     * When the word couldn't be find within the specifications
     *  but without those specs, it found results.
     * @return String with an Error
     */
    public final String getMessageOfError_specsNotFound_but(){
        return  "Error:" +
                "\n"+
                "*The audio could not be found within the given specs" +
                    ", but I found some similar results." +
                "\n"+
                "*El audio no se pudo encontrar dentro de las especificaciones dadas" +
                    ", pero encontré resultados similares.";
    }

    /**
     * When an audio is deprecated
     * @return string with the error message
     */
    public final String getMessageOfError_audioDeprecated(){
        return "Error:\n" +
                "*Audio deprecated.\n" +
                "*Audio obsoleto.";
    }

    /**
     * When an audio is deprecated
     * @return string with the error message
     */
    public final String getMessageOfError_unexpectedFileError(){
        return "Error:\n" +
                "*Unexpected file error.\n" +
                "*Inesperado error del archivo.";
    }


}
