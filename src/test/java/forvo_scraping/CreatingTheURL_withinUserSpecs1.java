package forvo_scraping;

import forvo_API.data_processes.fixedVariables.MapperEnumFixedVar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class CreatingTheURL_withinUserSpecs1 {

    //example of returning: https://apifree.forvo.com/key/1d41a16fe2fc7be20028f9b634997dda/format/xml/action/word-pronunciations/word/barcelona/group-in-languages/true

    private String theUrl="";   //the url which will be returned
    MapperEnumFixedVar mapper_urv = new MapperEnumFixedVar();  //instance of the class which contains every prefix/complex


    /*
    Methods which look like Constructors:
     */

    /**returns a url taking in count some specifications
     *
     * @param word
     * @return theURL to be scraped
     */
    protected String urlWithinUserSpecs(String word) {

        //Linking the beginning of the url
        theUrl = addBeginningOfTheUrl();

        //Linking the our personal Key from "file.properties" file
        theUrl += addPersonalKey();

        //Linking the type of format file I want to receive
        theUrl += addTypeOfFormatFile();

        //Linking the type of service I want to receive from forvo.com
        theUrl += addTypeOfService();

        //Linking the Word that a user asked for.
        theUrl += addWord(word);
/*
        //Linking Spanish language only if a user asked for it
        theUrl += addSpanishLanguage(language);

        //Linking English language only if a user asked for it
        theUrl += addEnglishLanguage(language);
*/

        return theUrl;
    }

    /**returns a url taking in count some specifications
     *
     * @param word
     * @param language
     * @return theURL to be scraped
     */
    protected String urlWithinUserSpecs(String word, String language) {

        //Linking the beginning of the url
        theUrl = addBeginningOfTheUrl();

        //Linking the our personal Key from "file.properties" file
        theUrl += addPersonalKey();

        //Linking the type of format file I want to receive
        theUrl += addTypeOfFormatFile();

        //Linking the type of service I want to receive from forvo.com
        theUrl += addTypeOfService();

        //Linking the Word that a user asked for.
        theUrl += addWord(word);

        //Linking Spanish language only if a user asked for it
        theUrl += addSpanishLanguage(language);

        //Linking English language only if a user asked for it
        theUrl += addEnglishLanguage(language);


        return theUrl;
    }

    /**returns a url taking in count some specifications
     *
     * @param word
     * @param language
     * @param sex
     * @return theURL to be scraped
     */
    protected String urlWithinUserSpecs(String word, String language, String sex) {

        //Linking the beginning of the url
        theUrl = addBeginningOfTheUrl();

        //Linking the our personal Key from "file.properties" file
        theUrl += addPersonalKey();

        //Linking the type of format file I want to receive
        theUrl += addTypeOfFormatFile();

        //Linking the type of service I want to receive from forvo.com
        theUrl += addTypeOfService();

        //Linking the Word that a user asked for.
        theUrl += addWord(word);

        //Linking Spanish language only if a user asked for it
        theUrl += addSpanishLanguage(language);

        //Linking English language only if a user asked for it
        theUrl += addEnglishLanguage(language);

        //Linking the Male Voice that a user asked for.
        theUrl += addMaleVoice(sex);

        //Linking the Male Voice that a user asked for.
        theUrl += addFemaleVoice(sex);


        return theUrl;
    }

    /**returns a url taking in count every specification
     *
     * @param word
     * @param language
     * @param sex
     * @param country
     * @return theURL to be scraped
     */
    protected String urlWithinUserSpecs(String word, String language, String sex, String country) {

        //Linking the beginning of the url
        theUrl = addBeginningOfTheUrl();

        //Linking the our personal Key from "file.properties" file
        theUrl += addPersonalKey();

        //Linking the type of format file I want to receive
        theUrl += addTypeOfFormatFile();

        //Linking the type of service I want to receive from forvo.com
        theUrl += addTypeOfService();

        //Linking the Word that a user asked for.
        theUrl += addWord(word);

        //Linking Spanish language only if a user asked for it
        theUrl += addSpanishLanguage(language);

        //Linking English language only if a user asked for it
        theUrl += addEnglishLanguage(language);

        //Linking the Male Voice that a user asked for.
        theUrl += addMaleVoice(sex);

        //Linking the Male Voice that a user asked for.
        theUrl += addFemaleVoice(sex);

        //Linking the Spanish country voice that a user asked for.
        theUrl += addSpanishCountry(country);

        //Linking the English country voice that a user asked for.
        theUrl += addEnglishCountry(country);


        return theUrl;
    }


    /*
     **************************************************
     Methods to implement within the above constructors
     **************************************************
     */


    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the beginning of the url
     */
    private String addBeginningOfTheUrl() {
        return "https://apifree.forvo.com/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return our personal Key from "file.properties" file
     */
    private String addPersonalKey() {
        String filePath_forProperties = "file.properties";
        Properties properties = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath_forProperties);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "key/"+properties.getProperty("forvoKey") + "/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the type of format file I want to receive
     */
    private String addTypeOfFormatFile() {
        return "format/xml/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @return the type of service I want to receive from forvo.com
     */
    private String addTypeOfService() {
        return "action/word-pronunciations/";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param word
     * @return the Word that a user asked for
     */
    private String addWord(String word) {
        return "word/"+word+"/";}

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param language
     * @return the Spanish language only if a user asked for it
     */
    private String addSpanishLanguage(String language) {
        for (String languagesAllowed :
                mapper_urv.getSpanishComplexes()) {
            if (language.equalsIgnoreCase(languagesAllowed)) {
                return  "language/es/";
            }
        }
        return "";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param language
     * @return the English language only if a user asked for it
     */
    private String addEnglishLanguage(String language) {
        for (String languagesAllowed :
                mapper_urv.getEnglishComplexes()) {
            if (language.equalsIgnoreCase(languagesAllowed)) {
                return "language/en/";
            }
        }
        return "";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param sex
     * @return the Male Voice that a user asked for
     */
    private String addMaleVoice(String sex) {

        for (String complexesAllowed :
                mapper_urv.getMaleSexComplexes()) {
            if (sex.equalsIgnoreCase(complexesAllowed)) {
                return "sex/m/";
            }
        }
        return "";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param sex
     * @return the Female Voice that a user asked for
     */
    private String addFemaleVoice(String sex) {

        for (String complexesAllowed :
                mapper_urv.getFemaleSexComplexes()) {
            if (sex.equalsIgnoreCase(complexesAllowed)) {
                return "sex/f/";
            }
        }
        return "";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param country
     * @return the Spanish Country voice that a user asked for
     */
    private String addSpanishCountry(String country) {

        for (int i = 0; i < mapper_urv.getAll_SpanishCountries_Abbreviations().size()-1; i++) {
            String countryComplex = "";


            //if the notation from a user request contains an abbreviation of a Spanish country.
            countryComplex = mapper_urv.getAll_SpanishCountries_Abbreviations().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_SpanishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
            //if the notation from a user request contains an abbreviation (Alpha-3 Code) of a Spanish country.
            countryComplex = mapper_urv.getAll_SpanishCountries_Abbreviation_alpha3Codes().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_SpanishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
            //if the notation from a user request contains a prettyName of a Spanish country.
            countryComplex = mapper_urv.getAll_SpanishCountries_PrettyName().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_SpanishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
        }

        return "";
    }

    /**
     * To help to shape the perfect forvo url for every user specs
     * @param country
     * @return the English Country voice that a user asked for
     */
    private String addEnglishCountry(String country) {

        for (int i = 0; i < mapper_urv.getAll_EnglishCountries_Abbreviations().size()-1; i++) {
            String countryComplex = "";


            //if the notation from a user request contains an abbreviation of a Spanish country.
            countryComplex = mapper_urv.getAll_EnglishCountries_Abbreviations().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_EnglishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
            //if the notation from a user request contains an abbreviation (Alpha-3 Code) of a Spanish country.
            countryComplex = mapper_urv.getAll_EnglishCountries_Abbreviation_alpha3Codes().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_EnglishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
            //if the notation from a user request contains a prettyName of a Spanish country.
            countryComplex = mapper_urv.getAll_EnglishCountries_PrettyName().get(i);
            if (country.equalsIgnoreCase(countryComplex)){
                return "country/"+ mapper_urv.getAll_EnglishCountries_Abbreviation_alpha3Codes().get(i)+"/";
            }
        }

        return "";
    }

}
