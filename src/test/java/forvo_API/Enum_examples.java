package forvo_API;

public class Enum_examples {

    private enum transport {
        PLANE (500), BUS(30), CAR(70), TRAIN(200);

        //constructor
        int speed;

        transport (int speed){
            this.speed = speed;
        }

        void comer (){
            System.out.println("I'm eating");
        } //it didn't work. One day I'd like to know how to make it work
    }

    public static void main(String[] args) {
        validating();
        System.out.println();
        allTheValues();
        System.out.println();
        speedOfEachTransport();
        System.out.println();
        theOrdinalOfEachTransport();

    }


    /**
     * Validating with a conditional
     */
    private static void validating(){
        transport tp = transport.PLANE;
        if (tp == transport.PLANE){
            System.out.println("it is true, it's a plane.");
        }
    }

    /**
     * Getting all the values
     */
    private static void allTheValues(){
        transport[] tps = transport.values();
        for (transport value :
                tps) {
            System.out.println("value: " +value);
        }
    }

    /**
     * Getting all the speed values for each transport
     */
    private static void speedOfEachTransport(){
        transport[] tps = transport.values();
        for (transport tp :
                tps) {
            System.out.println(tp+" -> "+tp.speed);
        }
    }

    /**
     * Getting the ordinal of each transport
     */
    private static void theOrdinalOfEachTransport() {
        transport[] tps = transport.values();
        for (transport tp :
                tps) {
            System.out.println(tp+" in ["+tp.ordinal()+"]");
        }
    }


}
