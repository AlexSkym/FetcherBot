package google_sheets.library;

import zUtil.myProperties.MyProperties;

public class LastParagraphsPOJO {

    private static int num_of_paragraph_eng_easy = 1;
    private static int num_of_paragraph_eng_intermediate = 1;
    private static int num_of_paragraph_eng_advanced = 1;

    private static int num_of_paragraph_esp_easy = 1;
    private static int num_of_paragraph_esp_intermediate = 1;
    private static int num_of_paragraph_esp_advanced = 1;



    LastParagraphsPOJO(){}



//    *********************
//    Getters and Setters
//    *********************

    public static int getNum_of_paragraph_eng_easy() {
        return num_of_paragraph_eng_easy;
    }
    public static void setNum_of_paragraph_eng_easy(int num_of_paragraph_eng_easy) {
        System.out.println("SETTING EN Easy!! N#: "+LastParagraphsPOJO.num_of_paragraph_eng_easy);
        LastParagraphsPOJO.num_of_paragraph_eng_easy = num_of_paragraph_eng_easy;
    }

    public static int getNum_of_paragraph_eng_intermediate() {
        return num_of_paragraph_eng_intermediate;
    }
    public static void setNum_of_paragraph_eng_intermediate(int num_of_paragraph_eng_intermediate) {
        LastParagraphsPOJO.num_of_paragraph_eng_intermediate = num_of_paragraph_eng_intermediate;
    }

    public static int getNum_of_paragraph_eng_advanced() {
        return num_of_paragraph_eng_advanced;
    }
    public static void setNum_of_paragraph_eng_advanced(int num_of_paragraph_eng_advanced) {
        LastParagraphsPOJO.num_of_paragraph_eng_advanced = num_of_paragraph_eng_advanced;
    }

    public static int getNum_of_paragraph_esp_easy() {
        return num_of_paragraph_esp_easy;
    }
    public static void setNum_of_paragraph_esp_easy(int num_of_paragraph_esp_easy) {
        LastParagraphsPOJO.num_of_paragraph_esp_easy = num_of_paragraph_esp_easy;
    }

    public static int getNum_of_paragraph_esp_intermediate() {
        return num_of_paragraph_esp_intermediate;
    }
    public static void setNum_of_paragraph_esp_intermediate(int num_of_paragraph_esp_intermediate) {
        LastParagraphsPOJO.num_of_paragraph_esp_intermediate = num_of_paragraph_esp_intermediate;
    }

    public static int getNum_of_paragraph_esp_advanced() {
        return num_of_paragraph_esp_advanced;
    }
    public static void setNum_of_paragraph_esp_advanced(int num_of_paragraph_esp_advanced) {
        LastParagraphsPOJO.num_of_paragraph_esp_advanced = num_of_paragraph_esp_advanced;
    }
}
