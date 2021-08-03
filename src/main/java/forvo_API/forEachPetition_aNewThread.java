package forvo_API;

import forvo_API.data_processes.ItemForXML;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class forEachPetition_aNewThread extends Thread{

    //Map with a key with a message ID
    Map<String, List<ItemForXML>> map_messageID_and_itemList;
    //Map with a key with an iterator
    Map<String, List<ItemForXML>> map_iterator_and_itemList;
    int iterator = 0;

    List<ItemForXML> temporalList = new ArrayList<>();
    forEachPetition_aNewThread(List<ItemForXML> list){
        this.temporalList = list;

        //To be executed in another class:
//        forEachPetition_aNewThread newThread = new forEachPetition_aNewThread(list);
//        newThread.start();
    }

    static int count = 0;
    forEachPetition_aNewThread(){
        start();
        count++;
    }

    public static void main(String[] args) {

        forEachPetition_aNewThread newThread = new forEachPetition_aNewThread();
        forEachPetition_aNewThread newThread1 = new forEachPetition_aNewThread();
        forEachPetition_aNewThread newThread2 = new forEachPetition_aNewThread();
        forEachPetition_aNewThread newThread3 = new forEachPetition_aNewThread();

        System.out.println("getName: "+newThread.getName());
        System.out.println("getName1: "+newThread1.getName());
        System.out.println("getState1: "+newThread3.getState());
        System.out.println("getName2: "+newThread2.getName());
        newThread3.setName("aguacate3");
        System.out.println("getName3: "+newThread3.getName());
        System.out.println("getState3: "+newThread3.getState());

    }

    @Override
    public void run() {
        super.run();

        setName("c: " +count);
        count++;
    }
}
