package testing;

public class mai {

    public static void main(String[] args) {
        Singleton singleton = Singleton.createInstance("meh_1");
        singleton.setNum(8);

        Singleton singleton1 = Singleton.createInstance("meh_2");
        singleton1.setNum(54);

        Singleton singleton2 = Singleton.createInstance("meh_3");
        singleton2.setNum(32);

        Singleton singleton3 = Singleton.createInstance("meh_4");
        singleton3.setNum(9);

/*
        System.out.println("number: "+singleton1.getNum());
        System.out.println("number1: "+singleton1.getNum());
        System.out.println("number2: "+singleton1.getNum());
        System.out.println("number3: "+singleton1.getNum());*/

        Singleton singletonTest = Singleton.getCertainSingleton("meh_3");


        System.out.println("number: "+singletonTest.getNum());
        System.out.println("number1: "+singletonTest.getNum());
        System.out.println("number2: "+singletonTest.getNum());


    }
}
