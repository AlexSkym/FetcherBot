package forvo_API.data_processes.fixedVariables;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Validation of a String sent by a user
 */
public class AcceptableValues_ofAUserString {

    //mapper to be used in the methods below
    MapperEnumFixedVar mapperEnum = new MapperEnumFixedVar();
    //bot aliases
    ArrayList<String> botAliases = new ArrayList<>();

    //acceptable or not: To pass to the next method
    boolean acceptableAlias = false;
    boolean acceptableLanguage = false;
    boolean acceptableSex = false;
    boolean acceptableCountry = false;

    boolean acceptableFinalUserString = false; //last verification
    Map<String, String> map_typeOfValue_and_value = new LinkedHashMap<>();

    //String with supposed accuracy
    String theRightAlias = "";
    String theRightLanguage = "";
    String theRightSex = "";
    String theRightCountry = "";
    String theUserWordOrSentence = "";

    String userString = "";

    /**
     * constructor
     */
    public AcceptableValues_ofAUserString(){

        /* NOTE: I'll need to find a way to create Spanish values for countries.
        Because every fixed value is in English
         */
    }


    /*
    The only public method:
     */

    /**
     * Verifier to be sure that the user wrote a right string to make our program work
     * @param userString the string a user sent to invoke the bot and its functionalities
     */
    public Map verifying_aUserString(String userString){  //remember that this contains a random word name too
        //initialization of values
        theRightAlias = ""; theRightSex = ""; theRightLanguage = ""; theRightCountry = ""; theUserWordOrSentence = "";
        this.userString = userString.trim().toLowerCase();
        acceptableFinalUserString = false;
        map_typeOfValue_and_value.clear();


        //start judging the alias value
        aliasesJudger(this.userString);
        if (acceptableAlias) {
            //judging every value of the sentence
            languageJudger(this.userString);
            sexJudger(this.userString);
            countryJudger(this.userString);
            /*judging the sentence to ensure everything is orderly correct and
                and every value have been wrote correctly

                Note: I have to remember that the user string will have random words at the end*/
            finalValidation(this.userString);
        }

        //if the user string is not completely right in every sense, it'll send a map with null value.
        if (acceptableFinalUserString){
            map_typeOfValue_and_value.put("language", theRightLanguage);
            map_typeOfValue_and_value.put("sex", theRightSex);
            map_typeOfValue_and_value.put("country", theRightCountry);
            //you get theRightWord after validating everything else
            map_typeOfValue_and_value.put("word", theUserWordOrSentence);
        }

        return map_typeOfValue_and_value;
    }



    /*
    Private methods:
     */


    /**
     * judging the string a user sent, looking for a alias value
     * @param userString the string a user sent
     */
    private void aliasesJudger(String userString){

        MapperEnumFixedVar mapper = new MapperEnumFixedVar();
        botAliases = mapper.getAliasesComplexes();

        for (String oneAlias :
                botAliases) {
            if (userString.contains(oneAlias+" ")) {
                theRightAlias = oneAlias.trim();
                acceptableAlias = true;
                break;
            }else {
                theRightAlias = "";
                acceptableAlias = false;
            }
        }

    }

    /**
     * judging the string a user sent, looking for a language value
     * @param userString the string a user sent
     */
    private void languageJudger(String userString){
        List<String> countryComplexes = mapperEnum.getEnglishComplexes();
        countryComplexes.addAll(mapperEnum.getSpanishComplexes());

        //looking in English and Spanish complexes / prefixes
        for (String oneComplex :
                countryComplexes) {
            if (userString.contains(" "+oneComplex.trim()+" ")) {
                theRightLanguage += " "+oneComplex.trim();
                acceptableLanguage = true;
                break;
            }else {
                theRightLanguage = "";
                acceptableLanguage = false;
            }
        }
    }

    /**
     * judging the string a user sent, looking for a sex value
     * @param userString the string a user sent
     */
    private void sexJudger(String userString){
        List<String> sexComplexes = mapperEnum.getFemaleSexComplexes();
        sexComplexes.addAll(mapperEnum.getMaleSexComplexes());

        //looking in English and Spanish complexes / prefixes
        for (String oneComplex :
                sexComplexes) {
            if (userString.contains(" "+oneComplex.trim()+" ")) {
                theRightSex += " "+oneComplex.trim();
                acceptableSex = true;
                break;
            }else {
                theRightSex = "";
                acceptableSex = false;
            }
        }
    }

    /**
     * judging the string a user sent, looking for a country value
     * @param userString the string a user sent
     */
    private void countryJudger(String userString){
        //adding formal values

        List<String> countryComplexes = mapperEnum.getAll_EnglishCountries_FormalValue();
        countryComplexes.addAll(mapperEnum.getAll_SpanishCountries_FormalValue());
        //adding abbreviation values
        countryComplexes.addAll(mapperEnum.getAll_EnglishCountries_Abbreviations());
        countryComplexes.addAll(mapperEnum.getAll_SpanishCountries_Abbreviations());
        //adding abbreviation alpha 3 codes
        countryComplexes.addAll(mapperEnum.getAll_EnglishCountries_Abbreviation_alpha3Codes());
        countryComplexes.addAll(mapperEnum.getAll_SpanishCountries_Abbreviation_alpha3Codes());
        //adding pretty name
        countryComplexes.addAll(mapperEnum.getAll_EnglishCountries_PrettyName());
        countryComplexes.addAll(mapperEnum.getAll_SpanishCountries_PrettyName());

        //looking in English and Spanish complexes / prefixes
        for (String oneComplex :
                countryComplexes) {
            if (userString.toLowerCase().contains(" "+oneComplex.trim().toLowerCase()+" ")) {
                theRightCountry += " "+oneComplex.trim();
                acceptableCountry = true;
                break;
            }else {
                theRightCountry = "";
                acceptableCountry = false;
            }
        }
    }




    /**
     * judging all the string a user sent, looking for every value as a complete sentence
     * @param userString the string a user sent
     */
    private void finalValidation(String userString){

        //options with a complete string which could match with the userString
        List<String> options = new ArrayList<>();


        //alias + 3 arguments
        if (acceptableAlias && acceptableLanguage && acceptableSex && acceptableCountry) {
            options.add(theRightAlias + theRightLanguage + theRightSex + theRightCountry);
            options.add(theRightAlias + theRightLanguage + theRightCountry + theRightSex);

            options.add(theRightAlias + theRightSex + theRightCountry + theRightLanguage);
            options.add(theRightAlias + theRightSex + theRightLanguage + theRightCountry);

            options.add(theRightAlias + theRightCountry + theRightSex + theRightLanguage);
            options.add(theRightAlias + theRightCountry + theRightLanguage + theRightSex);
        }


        //alias + 2 arguments
        else if (acceptableAlias && acceptableLanguage && acceptableSex) {
            options.add(theRightAlias + theRightLanguage + theRightSex);
            options.add(theRightAlias + theRightSex + theRightLanguage);
        }
        //alias + 2 arguments
        else if (acceptableAlias && acceptableCountry && acceptableSex) {
            options.add(theRightAlias + theRightCountry + theRightSex);
            options.add(theRightAlias + theRightSex + theRightCountry);
        }
        //alias + 2 arguments
        else if (acceptableAlias && acceptableLanguage && acceptableCountry) {
            options.add(theRightAlias + theRightLanguage + theRightCountry);
            options.add(theRightAlias + theRightCountry + theRightLanguage);
        }



        //alias + 1 argument
        else if (acceptableAlias && acceptableLanguage)
            options.add(theRightAlias+theRightLanguage);
        //alias + 1 argument
        else if (acceptableAlias && acceptableSex)
            options.add(theRightAlias+theRightSex);
        //alias + 1 argument
        else if (acceptableAlias && acceptableCountry)
            options.add(theRightAlias+theRightCountry);



        //alias + 0 arguments
        else if (acceptableAlias)
            options.add(theRightAlias);





        //Verifying every option (options[]) with userString
        for (String option :
                options) {

            /*counting option characters to ensure that the sentence
                is at the beginning of the sentence */
            int optionLength = option.length();
            String userString_cut = userString.substring(0,optionLength);
            String userString_finalCut = userString.substring(optionLength).toLowerCase().trim();

            //System.out.println("acceptableCountry "+acceptableCountry);
            //System.out.println("userString_cut "+userString_cut);
            //System.out.println("userString_finalCut "+userString_finalCut);


            //verifying if the sentence is right with that option
            if (userString_cut.trim().equalsIgnoreCase(option)) {
                System.out.println("verifying: true");
                acceptableFinalUserString = true;
                gettingWordOrSentence(userString_finalCut);
                break;
            }
            //verifying if the sentence is wrong with that option
            else {
                System.out.println("verifying: false");
                acceptableFinalUserString = false;
            }

        }


    }

    /**
     * after getting the last part of the sentence (which haven't been verified),
     * I need to save that part to find that word or sentence in apifree.forvo.com
     *
     * @param userString_finalCut the last part of the non verified of the user string
     */
    private void gettingWordOrSentence(String userString_finalCut){

        theUserWordOrSentence = userString_finalCut.trim().toLowerCase();
    }


}
