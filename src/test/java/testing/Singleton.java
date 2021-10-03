package testing;

import java.util.LinkedHashMap;
import java.util.Map;

public class Singleton {

    static Map<String, Singleton> map_getInstance = new LinkedHashMap<>();

    int num = 0;

    static Singleton singleton;

    private Singleton() {

    }

    static Singleton createInstance(String id){
        System.out.println("executing: " + id);
        singleton = new Singleton();
        map_getInstance.put(id, singleton);

        return singleton;
    }

    static Singleton getCertainSingleton(String id){

        if (map_getInstance.containsKey(id)){
            System.out.println("I think it works");
            return map_getInstance.get(id);
        }else {
            System.out.println("it's null");
            return null;
        }

    }

    public int getNum() {
        return num;
    }

    public void setNum(int f){
        num += f;
    }

}
