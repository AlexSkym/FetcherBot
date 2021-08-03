package forvo_API.data_processes.ItemLists_processes;

import forvo_API.data_processes.ItemForXML;

import java.util.*;

public class ItemListCreator {

    //identifier number of any list (a list from a message with an audio sent)
    private static Map<String, List<ItemForXML> > map_idBotMessage_and_itemsList = new LinkedHashMap<>();

    //time that I'll take to auto-delete a created list.
    int timeTaken = 1000*60*20; //ms to sec, sec to min

    ItemListCreator(){ }

    /**
     * Save a list with items for when the user want to see for
     * the next item of a list sent in a message to a Discord channel
     * @param newList
     */
    void SaveList (List<ItemForXML> newList, String newBotMessageID){
        //saving list:
        map_idBotMessage_and_itemsList.put(newBotMessageID, newList);
        //making a instance to create a new counter.
        ItemListCreator itemListCreator = new ItemListCreator();
        itemListCreator.autoCleaningNewList(newBotMessageID); //every 20 minutes
    }

    /**
     * Rewrite a specific list among the whole lists
     * @param newList the new list you want to add
     * @param oldBotMessageID the old ID of the bot message
     */
    void rewriteList (List<ItemForXML> newList, String oldBotMessageID){

        map_idBotMessage_and_itemsList.replace(oldBotMessageID, newList);
    }

    /**
     * Get the list by the Id of the bot message
     * @param oldBotMessageID
     * @return
     */
    List<ItemForXML> getList_fromBotMessageId (String oldBotMessageID){

        return map_idBotMessage_and_itemsList.get(oldBotMessageID);
    }

    //---------this method is called in this class---------

    /**
     * Clean the map with lists of items every 20 minutes.
     *  (Maximum time a link lasts is 2 hours)
     * @param newBotMessageID the created list withing the map which will be deleted
     */
    void autoCleaningNewList(String newBotMessageID){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map_idBotMessage_and_itemsList.remove(newBotMessageID);
            }
        },timeTaken);
    }


}
