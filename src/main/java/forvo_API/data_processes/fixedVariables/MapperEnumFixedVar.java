package forvo_API.data_processes.fixedVariables;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapperEnumFixedVar {



    /* Methods to get everything in an orderly way; every abbreviation, full name, etc.
    ------------------------------------------------------------------------------------*/

    //Sex
    /**
     * Complexes of the Male Sex
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAliasesComplexes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.AliasesAllowed vs = UserRequestVariables.AliasesAllowed.ALIASES;

        for (String c :
                vs.complex) {
            complexes.add(c);
        }

        return complexes;
    }



    //Languages
    /**
     * Complexes of the Spanish Language
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getSpanishComplexes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.LanguagesAllowed la = UserRequestVariables.LanguagesAllowed.SPANISH;

        for (String c :
                la.complex) {
            complexes.add(c);
        }

        return complexes;
    }

    /**
     * Complexes of the English Language
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getEnglishComplexes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.LanguagesAllowed la = UserRequestVariables.LanguagesAllowed.ENGLISH;

        for (String c :
                la.complex) {
            complexes.add(c);
        }

        return complexes;
    }




    //Sex
    /**
     * Complexes of the Male Sex
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getMaleSexComplexes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.VoiceSex vs = UserRequestVariables.VoiceSex.MALE;

        for (String c :
                vs.complex) {
            complexes.add(c);
        }

        return complexes;
    }

    /**
     * Complexes of the Female Sex
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getFemaleSexComplexes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.VoiceSex vs = UserRequestVariables.VoiceSex.FEMALE;

        for (String c :
                vs.complex) {
            complexes.add(c);
        }

        return complexes;
    }




    //the whole English Countries
    /** English Formal values (in their primitive upper case mode; all together and close together)
     *      ex.: UNITEDSTATES, NEWZEALAND, etc.
     */
    public ArrayList<String> getAll_EnglishCountries_FormalValue(){
        ArrayList<String> allValues = new ArrayList<>();

        UserRequestVariables.EnglishForvoCountries[] ec = UserRequestVariables.EnglishForvoCountries.values();

        for (UserRequestVariables.EnglishForvoCountries value :
                ec) {
            allValues.add(String.valueOf(value).trim());
        }

        return allValues;
    }

    /**
     * PrettyName from the English Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_EnglishCountries_PrettyName(){
        ArrayList<String> complexes = new ArrayList<>();

        UserRequestVariables.EnglishForvoCountries[] ec = UserRequestVariables.EnglishForvoCountries.values();

        for (UserRequestVariables.EnglishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.prettyName));
        }

        return complexes;
    }

    /**
     * Abbreviations from the English Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_EnglishCountries_Abbreviations(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.EnglishForvoCountries[] ec = UserRequestVariables.EnglishForvoCountries.values();

        for (UserRequestVariables.EnglishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.countryAbbreviation));
        }

        return complexes;
    }

    /**
     * Abbreviation (Alpha-3 Code) from the English Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_EnglishCountries_Abbreviation_alpha3Codes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.EnglishForvoCountries[] ec = UserRequestVariables.EnglishForvoCountries.values();

        for (UserRequestVariables.EnglishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.countryAbbreviation_alpha3Code));
        }

        return complexes;
    }

    /**
     * Close Countries from every English Country
     *
     * @return return an ArrayList with an String-type Array (ArrayList<String[]> complexes)
     */
    public Map<String, String[]> getAll_EnglishCountries_CloseCountries(){
        Map <String, String[]> countries_and_itsCloseCountries = new LinkedHashMap<>();

        UserRequestVariables.EnglishForvoCountries[] ec = UserRequestVariables.EnglishForvoCountries.values();

        //c = Enum with each Eng country
        for (UserRequestVariables.EnglishForvoCountries c :
                ec) {

            //saving each close Eng country into a String[] array.
            String[] closeCountries = new String[c.closeCountries.length];
            for (int i = 0; i < c.closeCountries.length; i++) {
                String one_ofTheCloseCountries = c.closeCountries[i];
                closeCountries[i] = one_ofTheCloseCountries;
            }
            //adding
            countries_and_itsCloseCountries.put(String.valueOf(c).trim(), closeCountries);

        }

        return countries_and_itsCloseCountries;
    }




    //the whole Spanish countries
    /** Spanish Formal values (in their primitive upper case mode; all together and close together)
     *      ex.: DOMINICANREPUBLIC, ARGENTINA, ELSALVADOR, etc.
     */
    public ArrayList<String> getAll_SpanishCountries_FormalValue(){
        ArrayList<String> allValues = new ArrayList<>();

        UserRequestVariables.SpanishForvoCountries[] ec = UserRequestVariables.SpanishForvoCountries.values();

        for (UserRequestVariables.SpanishForvoCountries value :
                ec) {
            allValues.add(String.valueOf(value).trim());
        }

        return allValues;
    }

    /**
     * PrettyName from the Spanish Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_SpanishCountries_PrettyName(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.SpanishForvoCountries[] ec = UserRequestVariables.SpanishForvoCountries.values();

        for (UserRequestVariables.SpanishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.prettyName));
        }

        return complexes;
    }

    /**
     * Abbreviations from the Spanish Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_SpanishCountries_Abbreviations(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.SpanishForvoCountries[] ec = UserRequestVariables.SpanishForvoCountries.values();

        for (UserRequestVariables.SpanishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.countryAbbreviation));
        }

        return complexes;
    }

    /**
     * Abbreviation (Alpha-3 Code) from the Spanish Countries
     *
     * @return return an ArrayList (ArrayList<String> complexes)
     */
    public ArrayList<String> getAll_SpanishCountries_Abbreviation_alpha3Codes(){

        ArrayList<String> complexes = new ArrayList<String>();

        UserRequestVariables.SpanishForvoCountries[] ec = UserRequestVariables.SpanishForvoCountries.values();

        for (UserRequestVariables.SpanishForvoCountries c :
                ec) {
            complexes.add(String.valueOf(c.countryAbbreviation_alpha3Code));
        }

        return complexes;
    }

    /**
     * Close Countries from every Spanish Country
     *
     * @return return an ArrayList with an String-type Array (ArrayList<String[]> complexes)
     */
    public Map<String, String[]> getAll_SpanishCountries_CloseCountries(){
        Map <String, String[]> countries_and_itsCloseCountries = new LinkedHashMap<>();

        UserRequestVariables.SpanishForvoCountries[] ec = UserRequestVariables.SpanishForvoCountries.values();

        //c = Enum with each Eng country
        for (UserRequestVariables.SpanishForvoCountries c :
                ec) {

            //saving each close Eng country into a String[] array.
            String[] closeCountries = new String[c.closeCountries.length];
            for (int i = 0; i < c.closeCountries.length; i++) {
                String one_ofTheCloseCountries = c.closeCountries[i];
                closeCountries[i] = one_ofTheCloseCountries;
            }
            //adding
            countries_and_itsCloseCountries.put(String.valueOf(c).trim(), closeCountries);

        }

        return countries_and_itsCloseCountries;
    }



    //specific country.


    /**
     *
     * @param theSpecificCountry the specific country that we want to receive (ex.: unitedKingdom)
     * @return
     */
    public String getSpecific_PrettyName_Of_AnyCountry(String theSpecificCountry) {
        String noValue = "";

        for (String value :
                getAll_EnglishCountries_FormalValue()) {
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return value;
            }
        }
        for (String value :
                getAll_SpanishCountries_FormalValue()) {
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return value;
            }
        }

        return noValue;
    }

    /**
     *
     * @param theSpecificCountry the specific country that we want to receive (ex.: unitedKingdom)
     * @return
     */
    public String getSpecific_Abbreviation_Of_AnyCountry(String theSpecificCountry) {
        String noValue = "";

        for (int i = 0; i < getAll_EnglishCountries_Abbreviations().size(); i++) {
            String value = getAll_EnglishCountries_FormalValue().get(i);
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return getAll_EnglishCountries_Abbreviations().get(i);
            }
        }
        for (int i = 0; i < getAll_SpanishCountries_Abbreviations().size(); i++) {
            String value = getAll_SpanishCountries_FormalValue().get(i);
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return getAll_SpanishCountries_Abbreviations().get(i);
            }
        }

        return noValue;
    }

    /**
     *
     * @param theSpecificCountry the specific country that we want to receive (ex.: unitedstates)
     * @return
     */
    public String getSpecific_Abbreviation_alpha3Code_Of_AnyCountry(String theSpecificCountry) {
        String noValue = "";

        for (int i = 0; i < getAll_EnglishCountries_Abbreviation_alpha3Codes().size(); i++) {
            String value = getAll_EnglishCountries_FormalValue().get(i);
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return getAll_EnglishCountries_Abbreviation_alpha3Codes().get(i);
            }
        }
        for (int i = 0; i < getAll_SpanishCountries_Abbreviation_alpha3Codes().size(); i++) {
            String value = getAll_SpanishCountries_FormalValue().get(i);
            if (value.equalsIgnoreCase(theSpecificCountry)){
                return getAll_SpanishCountries_Abbreviation_alpha3Codes().get(i);
            }
        }

        return noValue;
    }

    /**
     *
     * @param theSpecificCountry the specific country that we want to receive (ex.: unitedstates)
     * @return returns an Array (String[]).
     */
    public String[] getSpecific_CloseCountries(String theSpecificCountry) {
        String[] noValue  = {};

        if (getAll_SpanishCountries_CloseCountries().containsKey(theSpecificCountry.toUpperCase().trim())){
            return getAll_SpanishCountries_CloseCountries().get(theSpecificCountry.toUpperCase().trim());
        }
        else if (getAll_EnglishCountries_CloseCountries().containsKey(theSpecificCountry.toUpperCase().trim())){
            return getAll_EnglishCountries_CloseCountries().get(theSpecificCountry.toUpperCase().trim());
        }

        return noValue;
    }



}
