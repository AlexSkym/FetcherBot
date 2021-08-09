package storytime;

import java.util.Scanner;

public class test {


    public static void main(String[] args) {
        MessageThreads messageThreads;
        Thread thread;

        Scanner scanner = new Scanner(System.in);

        int[] nums = new int[5];

        System.out.println("enter a number: 1 or 2.");

        //first one
        nums[0] = scanner.nextInt();
        messageThreads = new MessageThreads(String.valueOf(nums[0]));
        thread = new Thread(messageThreads);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //second one
        nums[1] = scanner.nextInt();
        messageThreads = new MessageThreads(String.valueOf(nums[1]));
        thread = new Thread(messageThreads);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //third one
        nums[2] = scanner.nextInt();
        messageThreads = new MessageThreads(String.valueOf(nums[2]));
        thread = new Thread(messageThreads);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //forth one
        nums[3] = scanner.nextInt();
        messageThreads = new MessageThreads(String.valueOf(nums[3]));
        thread = new Thread(messageThreads);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //fifth one
        nums[4] = scanner.nextInt();
        messageThreads = new MessageThreads(String.valueOf(nums[4]));
        thread = new Thread(messageThreads);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        /*
        for (int num :
                nums) {

            System.out.printf("Actual num: %d", num);

            if (num == 1) {
                //get the first number


            }

            if (num == 3){
                //get the lastone


            }

        }
        */

    }
}
