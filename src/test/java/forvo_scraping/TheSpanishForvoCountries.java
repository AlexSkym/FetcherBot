package forvo_scraping;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

class TheSpanishForvoCountries {

    enum MySpanishForvoCountries {

        CHILE("cl", "Chile", "Argentina", "Uruguay", "Bolivia", "Peru"),
        ARGENTINA("ar", "Argentina", "Uruguay", "Chile", "Paraguay", "Bolivia", "Peru"),
        URUGUAY("uy", "Uruguay", "Argentina"),
        PARAGUAY("py", "Paraguay", "Argentina", "Uruguay", "Bolivia", "Peru"),
        BOLIVIA("bo", "Bolivia", "Peru", "Paraguay", "Chile", "Argentina"),
        PERU("pe", "Peru", "Bolivia", "Chile", "Paraguay", "Ecuador"),
        ECUADOR("ec", "Ecuador", "Peru", "Colombia"),
        COLOMBIA("co", "Colombia", "Venezuela", "Cuba", "DominicanRepublic", "Ecuador", "Panama"),
        VENEZUELA("ve", "Venezuela", "Colombia", "Cuba", "DominicanRepublic"),
        PANAMA("pa", "Panama", "CostaRica", "Nicaragua", "Honduras", "Colombia"),
        COSTARICA("cr", "Costa Rica", "Nicaragua", "Panama"),
        NICARAGUA("ni", "Nicaragua", "ElSalvador", "Honduras", "CostaRica"),
        HONDURAS("hn", "Honduras", "Nicaragua", "ElSalvador", "Guatemala"),
        ELSALVADOR("sv", "El Salvador", "Nicaragua", "Honduras", "Guatemala"),
        GUATEMALA("gt", "Guatemala", "Mexico", "ElSalvador", "Honduras"),
        MEXICO("mx", "Mexico", "Guatemala"),
        CUBA("cu", "Cuba", "DominicanRepublic", "Colombia", "Venezuela"),
        DOMINICANREPUBLIC("dr", "Dominican Republic", "Cuba", "Colombia", "Venezuela"),
        SPAIN("es", "Spain", "Argentina", "Uruguay");

        private static final EnumMap<MySpanishForvoCountries, List<MySpanishForvoCountries>> countryProximityMap = new EnumMap<>(MySpanishForvoCountries.class);
        private static final HashMap<String, MySpanishForvoCountries> abbreviationToEnum = new HashMap<>();
        private final String countryAbbreviation;
        private final String prettyName;
        private final String[] closeCountries;

        static {
            MySpanishForvoCountries[] countries = MySpanishForvoCountries.values();
            for (MySpanishForvoCountries country : countries) {
                abbreviationToEnum.put(country.getCountryAbbreviation(), country);

                List<MySpanishForvoCountries> closeCountriesAsEnumList = new ArrayList<>(countries.length);

                countryProximityMap.put(country, closeCountriesAsEnumList);
            }
        }

        /**
         * Give to every country's value a "name", like:
         *      SPAIN( countryAbbreviation: "es", prettyName: "Spain", closeCountries: "Argentina", "Uruguay");
         *   and also works to improve the readability when indicating the variable "closeCountries"
         *      is a list.
         *
         *
         * @param countryAbbreviation
         * @param prettyName
         * @param closeCountries
         */
        MySpanishForvoCountries(String countryAbbreviation, String prettyName, String... closeCountries) {
            this.countryAbbreviation = countryAbbreviation;
            this.prettyName = prettyName;
            this.closeCountries = closeCountries;
        }


        //------------------------------------------------------------------------------

        /**
         * Gets the country's 2 letter abbreviation
         *
         * @return The country's 2 letter abbreviation
         */
        public String getCountryAbbreviation() {
            return countryAbbreviation;
        }

        /**
         * Gets the pretty name of the country
         *
         * @return The pretty name of the country
         */
        public String getPrettyName() {
            return prettyName;
        }

        /**
         * Gets an array of countries as strings closest in accent to the country
         *
         * @return An array of countries as strings closest in accent to the country
         */
        /*private String[] getCloseCountries(String country) {
            return MySpanishForvoCountries.country.closeCountries;
        }*/

        /**
         * Converts an abbreviation to a country. This is not case-sensitive
         *
         * @param abbreviation The country's 2 letter abbreviation
         * @return The country. Null if the abbreviation doesn't match a country.
         */
        public static MySpanishForvoCountries abbreviationToCountry(String abbreviation) {

            return MySpanishForvoCountries.abbreviationToEnum.get(abbreviation.toLowerCase());
        }

        /**
         * Finds the closest country in accent to the given country. This does not include itself.
         * <p>
         * //@param country The country
         *
         * @param countryFilter The set of countries that can be returned.
         * @return The closest country found. Null if it no country could be found.
         */
        public MySpanishForvoCountries findClosestCountry(Set<MySpanishForvoCountries> countryFilter) {
            for (MySpanishForvoCountries countryByProximity : MySpanishForvoCountries.countryProximityMap.get(this)) {
                if (countryFilter.contains(countryByProximity)) {
                    return countryByProximity;
                }
            }

            return null;
        }

    }

    //Execute program
    public static void main(String[] args) {

        System.out.println("the list of countries");
        //System.out.println(MySpanishForvoCountries.);


    }

    //------------------------------------------------------------------------------


}

