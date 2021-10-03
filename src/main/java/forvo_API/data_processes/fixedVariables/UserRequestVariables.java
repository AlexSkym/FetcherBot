package forvo_API.data_processes.fixedVariables;

public class UserRequestVariables {

    /**
     * Aliases allowed
     */
    enum AliasesAllowed {
        ALIASES(".,fetchPronunciation", ".,fetchPronunciacion",
                ".,fetchPro", ".,fetchPr", ".,fetchP", ".,fp",
                "pronunciation", ".,pronunciacion",".,pronounce", ".,pronuncia",
                ".,pron", ".,pro", ".,pr", ".,p");

        String[] complex;

        AliasesAllowed(String... complex){
            this.complex = complex;
        }
    }

    /**
     * Languages allowed
     */
    enum LanguagesAllowed {
        SPANISH("spanish", "spa", "sp", "español", "esp", "es"),
        ENGLISH("english", "eng", "en", "inglés", "ingles", "ing", "in");

        String[] complex;

        LanguagesAllowed(String... complex) {
            this.complex = complex;
        }
    }

    /**
     * Voice Sex
     */
    enum VoiceSex {
        MALE("male", "m", "masculino"),
        FEMALE("female", "f", "femenino");

        String[] complex;

        VoiceSex(String... complex) {
            this.complex = complex;
        }
    }


    /**
     * English Countries
     */
    enum EnglishForvoCountries {
        //6 countries
        AUSTRALIA("au", "aus", "Australia", "NewZealand", "UnitedKingdom"),
        CANADA("ca", "can", "Canada", "UnitedStates", "UnitedKingdom"),
        IRELAND("ie", "irl", "Ireland", "UnitedKingdom"),
        NEWZEALAND("nz", "nzl", "New Zealand", "Australia", "UnitedKingdom"),
        UNITEDKINGDOM("uk", "gbr", "United Kingdom", "Australia", "Ireland", "Canada", "UnitedStates"), //I'm not sure about these abbreviations
        UNITEDSTATES("us", "usa", "United States", "Canada");


        protected final String countryAbbreviation;
        protected final String countryAbbreviation_alpha3Code;
        protected final String prettyName;
        protected final String[] closeCountries;

        EnglishForvoCountries(String countryAbbreviation, String countryAbbreviation_alpha3Code, String prettyName, String... closeCountries) {
            this.countryAbbreviation = countryAbbreviation;
            this.countryAbbreviation_alpha3Code = countryAbbreviation_alpha3Code;
            this.prettyName = prettyName;
            this.closeCountries = closeCountries;
        }

    }

    /**
     * Spanish Countries
     */
    enum SpanishForvoCountries {
        //19 countries
        CHILE("cl", "chl", "Chile", "Argentina", "Uruguay", "Bolivia", "Peru"),
        ARGENTINA("ar", "arg", "Argentina", "Uruguay", "Chile", "Paraguay", "Bolivia", "Peru"),
        URUGUAY("uy", "ury", "Uruguay", "Argentina"),
        PARAGUAY("py", "pry", "Paraguay", "Argentina", "Uruguay", "Bolivia", "Peru"),
        BOLIVIA("bo", "bol", "Bolivia", "Peru", "Paraguay", "Chile", "Argentina"),
        PERU("pe", "per", "Peru", "Bolivia", "Chile", "Paraguay", "Ecuador"),
        ECUADOR("ec", "ecu", "Ecuador", "Peru", "Colombia"),
        COLOMBIA("co", "col", "Colombia", "Venezuela", "Cuba", "DominicanRepublic", "Ecuador", "Panama"),
        VENEZUELA("ve", "ven", "Venezuela", "Colombia", "Cuba", "DominicanRepublic"),
        PANAMA("pa", "pan", "Panama", "CostaRica", "Nicaragua", "Honduras", "Colombia"),
        COSTARICA("cr", "cri", "Costa Rica", "Nicaragua", "Panama"),
        NICARAGUA("ni", "nic", "Nicaragua", "ElSalvador", "Honduras", "CostaRica"),
        HONDURAS("hn", "hnd", "Honduras", "Nicaragua", "ElSalvador", "Guatemala"),
        ELSALVADOR("sv", "slv", "El Salvador", "Nicaragua", "Honduras", "Guatemala"),
        GUATEMALA("gt", "gtm", "Guatemala", "Mexico", "ElSalvador", "Honduras"),
        MEXICO("mx", "mex", "Mexico", "Guatemala"),
        CUBA("cu", "cub", "Cuba", "DominicanRepublic", "Colombia", "Venezuela"),
        DOMINICANREPUBLIC("dr", "dom", "Dominican Republic", "Cuba", "Colombia", "Venezuela"),  //I'm not sure about these abbreviations
        SPAIN("es", "esp", "Spain", "Argentina", "Uruguay");


        protected final String countryAbbreviation;
        protected final String countryAbbreviation_alpha3Code;
        protected final String prettyName;
        protected final String[] closeCountries;

        SpanishForvoCountries(String countryAbbreviation, String countryAbbreviation_alpha3Code, String prettyName, String... closeCountries) {
            this.countryAbbreviation = countryAbbreviation;
            this.countryAbbreviation_alpha3Code = countryAbbreviation_alpha3Code;
            this.prettyName = prettyName;
            this.closeCountries = closeCountries;
        }

    }




    /**
     * Constructor of the class
     */
    public UserRequestVariables(){ }


}
