package forvo_API.data_processes.ItemLists_processes;

import forvo_API.data_processes.ItemForXML;
import forvo_API.data_processes.xml_processes.ReaderForXML;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is only for readability.
 * The classes which extends this, is below.
 */
public abstract class Searching_inside_ItemsList{

    //get items List


    abstract List<ItemForXML> SearchingFor_language_spanish(List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_language_english(List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_language_both(List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_sex_female(List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_sex_male(List<ItemForXML> listParameter);
    //(I don't think I'll use this one)
    //abstract List<ItemForXML> SearchingFor_sex_both(List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_country(String country, List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_Date_year(String year, List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_rate(String rate, List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_num_vote(String num_vote, List<ItemForXML> listParameter);
    //(I don't think I'll use this one while having "SearchingFor_rate". Not sure)
    abstract List<ItemForXML> SearchingFor_num_positive_votes(String num_positive_votes, List<ItemForXML> listParameter);

    abstract List<ItemForXML> SearchingFor_hits(String hits, List<ItemForXML> listParameter);

    /**
     * This class works to search inside a list<ItemForXML>
     *     That list is found within ReaderForXML.class -> readXMLFile() method
     *
     * @return every return will be a specified List<ItemForXML>
     */
    public static class GettingAFilteredList_withUserSpecs extends Searching_inside_ItemsList{

        ReaderForXML readerForXML = new ReaderForXML();

        /**
         * constructor
         */
        public GettingAFilteredList_withUserSpecs() {}


        /**
         * Searching by language (Spanish) inside the list
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_language_spanish(List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getLangname().trim().equalsIgnoreCase("spanish")){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by language (Spanish or English) inside the list
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_language_english(List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getLangname().trim().equalsIgnoreCase("english")){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by language (Spanish and English) inside the list
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_language_both(List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                //if an item contains "english" or "spanish" as a language name
                if ((item.getLangname().trim().equalsIgnoreCase("english"))
                        || (item.getLangname().trim().equalsIgnoreCase("spanish"))){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by sex voice (female) inside the list
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_sex_female(List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getSex().trim().equalsIgnoreCase("f")){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by sex voice (male) inside the list
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_sex_male(List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getSex().trim().equalsIgnoreCase("m")){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by country inside the list
         * @param country A year with 4 digits like 1999
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_country(String country, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {

                if (item.getCountry().toLowerCase().trim().equalsIgnoreCase(country.toLowerCase())){
                    list.add(item);
                }

            }

            return list;
        }

        /**
         * Searching by year (date) inside the list
         * @param year A year with 4 digits like 1999
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_Date_year(String year, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getAddtime().trim().contains(year)){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by rate inside the list
         * @param rate . "rate" is: positive votes - negative votes... as a result
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_rate(String rate, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getRate().trim().equalsIgnoreCase(rate)){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by number of votes inside the list
         * @param num_vote all the votes, even if they were positive or negative
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_num_vote(String num_vote, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getNum_votes().trim().equalsIgnoreCase(num_vote)){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by number of positive votes inside the list
         * @param num_positive_votes
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_num_positive_votes(String num_positive_votes, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getNum_positive_votes().trim().equalsIgnoreCase(num_positive_votes)){
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * Searching by hits on the button inside the list
         * @param hits times that people have hit the button to reproduce the audio
         * @param listParameter That list is found within ReaderForXML.class -> readXMLFile() method
         * @return
         */
        public List<ItemForXML> SearchingFor_hits(String hits, List<ItemForXML> listParameter){
            List<ItemForXML> list = new ArrayList<>();

            for (ItemForXML item :
                    listParameter) {
                if (item.getHits().trim().equalsIgnoreCase(hits)){
                    list.add(item);
                }
            }

            return list;
        }


    }


}

