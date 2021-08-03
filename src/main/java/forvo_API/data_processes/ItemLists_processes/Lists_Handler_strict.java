package forvo_API.data_processes.ItemLists_processes;

import forvo_API.data_processes.ItemForXML;
import forvo_API.data_processes.fixedVariables.MapperEnumFixedVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is only for readability.
 * The classes which extends this, is below.
 */
public abstract class Lists_Handler_strict {

    Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs searching = new Searching_inside_ItemsList.GettingAFilteredList_withUserSpecs();
    MapperEnumFixedVar mapper_urv = new MapperEnumFixedVar(); //used in every method

    /*
    User options:
     */

    //(I don't think I'll use this one)
    abstract List<ItemForXML> handling_lists_ByLanguages(List<ItemForXML> list_WithALanguage, String language);
    //fine. (Useful. It is necessary to get a list of close countries)
    abstract List<ItemForXML> handling_lists_ByCountry(List<ItemForXML> list_WithACountry, String countryPriority);
    //(I don't think I'll use this one)
    abstract List<ItemForXML> handling_lists_BySex(List<ItemForXML> list_WithASex, String sex);

    /*
    Automated options:
     */

    abstract List<ItemForXML> handling_lists_ByDate_year(List<ItemForXML> list_WithADate_year);

    abstract List<ItemForXML> handling_lists_ByRate(List<ItemForXML> list_WithARate);

    abstract List<ItemForXML> handling_lists_ByNum_votes(List<ItemForXML> list_WithANumVotes);
    //(I don't think I'll use this one while having "SearchingFor_rate". Not sure)
    abstract List<ItemForXML> handling_lists_ByNum_positive_votes(List<ItemForXML> list_WithANumPositiveVotes);



    /**
     * This class works to sort out a list<ItemForXML>
     *
     * @return every return will be a sorted List<ItemForXML>
     */
    public static class GettingASortedList_withUserNeeds extends Lists_Handler_strict {


        public GettingASortedList_withUserNeeds() { }


        /*
        User options:
         */

        /**
         * Sorting an array by languages priorities.
         * Note: I think I'd prefer to get audios firstly from a English Native than a Spanish one.
         *
         * @param list_WithALanguage this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every language orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByLanguages(List<ItemForXML> list_WithALanguage, String language){
            List<ItemForXML> theList = new ArrayList<>();

            switch (language.toLowerCase()){

                case "spanish": theList.addAll(searching.SearchingFor_language_spanish(list_WithALanguage));

                case "english": theList.addAll(searching.SearchingFor_language_english(list_WithALanguage));

                default: theList.addAll(searching.SearchingFor_language_both(list_WithALanguage));
            }

            return theList;
        }

        /**
         * Sorting an array by country priorities.
         *
         *  Example: Argentina (as parameter sent by a user)
         *    This method will add every "close countries" that "argentina" contains
         *    in the class "UserRequestVariables.class"
         *
         * @param list_WithACountry this list is found in "Searching_insideXMLFile.class"
         * @param countryPriority what country (and its close countries) will
         *                        have the priority in the array.
         *
         * @return it returns a List<ItemForXML> with every close country that
         * a country contains orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByCountry(List<ItemForXML> list_WithACountry, String countryPriority){
            List<ItemForXML> theList = new ArrayList<>();

            if (countryPriority == null || countryPriority.equals("")){
                theList = list_WithACountry;
            }else {

                //temporal list to save every close countries.
                String[] everyCloseCountry = null;

                //adding close countries and the main one ("countryPriority"). to add every item that content them
                everyCloseCountry = new String[mapper_urv.getSpecific_CloseCountries(countryPriority).length + 1];
                for (int i = 0; i < mapper_urv.getSpecific_CloseCountries(countryPriority).length; i++) {
                    if (i == 0) {
                        everyCloseCountry[0] = countryPriority;
                    }
                    everyCloseCountry[i + 1] = mapper_urv.getSpecific_CloseCountries(countryPriority)[i];
                }

                //creating theList with every item.
                for (int i = 0; i < everyCloseCountry.length; i++) {
                    theList.addAll(searching.SearchingFor_country(everyCloseCountry[i], list_WithACountry));
                }
            }

            return theList;
        }

        /**
         * Sorting an array by sex voice priorities.
         * Note: I'd prefer to get audios from a girl instead of from a boy
         *
         * @param list_WithASex this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every sex orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_BySex(List<ItemForXML> list_WithASex, String sex){
            List<ItemForXML> theList = new ArrayList<>();

            if (sex.equalsIgnoreCase("f")){
                theList.addAll(searching.SearchingFor_sex_female(list_WithASex));
            }
            else if (sex.equalsIgnoreCase("m")){
                theList.addAll(searching.SearchingFor_sex_male(list_WithASex));
            }
            else {
                theList.addAll(list_WithASex);
            }

            return theList;
        }



        /*
        Automated options:
         */

        /**
         * Sorting an array by date (year) priorities.
         * Note: I'd prefer the most updated ones
         *
         * @param list_WithADate_year this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every year (4 digits) orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByDate_year(List<ItemForXML> list_WithADate_year){
            List<ItemForXML> theList = new ArrayList<>();

            for (ItemForXML item :
                    list_WithADate_year) {
                theList.add(item);
            }

            return theList;
        }

        /**
         * Sorting an array by rate priorities.
         * Note: I'd prefer the most rated ones. (from highest to lowest)
         *
         * @param list_WithARate this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every rate orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByRate(List<ItemForXML> list_WithARate){
            List<ItemForXML> theList = new ArrayList<>();

            for (ItemForXML item :
                    list_WithARate) {
                theList.add(item);
            }

            return theList;
        }

        /**
         * Sorting an array by number of votes priorities.
         * Note: (from highest to lowest)
         *
         * @param list_WithANumVotes this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every number of votes orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByNum_votes(List<ItemForXML> list_WithANumVotes){
            List<ItemForXML> theList = new ArrayList<>();

            for (ItemForXML item :
                    list_WithANumVotes) {
                theList.add(item);
            }

            return theList;
        }

        /**
         * Sorting an array by number of positive votes priorities.
         * Note: (from highest to lowest)
         *
         * @param list_WithANumPositiveVotes this list is found in "Searching_insideXMLFile.class"
         *
         * @return it returns a List<ItemForXML> with every number of positive votes orderly.
         */
        @Override
        public List<ItemForXML> handling_lists_ByNum_positive_votes(List<ItemForXML> list_WithANumPositiveVotes){
            List<ItemForXML> theList = new ArrayList<>();

            //getting numbers of positive votes
            int[] Nums_of_potiveVotes = new int[list_WithANumPositiveVotes.size()];
            for (int i = 0; i < list_WithANumPositiveVotes.size(); i++) {
                ItemForXML item = list_WithANumPositiveVotes.get(i);
                Nums_of_potiveVotes[i] = Integer.valueOf(item.getNum_positive_votes());
            }

            /*ordering the numbers from highest to lowest*/
            Arrays.sort(Nums_of_potiveVotes); //->this is the list is the other way around (está al revés)
            for (int i = Nums_of_potiveVotes.length; i > 0; i++) { //looking for every item orderly backwards. (hacia atrás)
                theList.addAll(searching.SearchingFor_hits(String.valueOf(Nums_of_potiveVotes[i]), list_WithANumPositiveVotes));
            }

            return theList;
        }

    }

}
