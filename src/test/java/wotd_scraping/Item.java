package wotd_scraping;

public class Item {

    private final String title_date = "Date / Fecha";

    private String subtitle_month;

    private String subtitle_day;

    private final String title_wordOfTheDay = "Word of the Day / Palabra del D\u00EDa";

    private String subtitle_spanishWord;
    private String subtitle_englishWord;

    private final String titleExample = "EXAMPLES";

    private String spanishExample_1;
    private String englishExample_1;

    private String spanishExample_2;
    private String englishExample_2;

    //Constructor
    Item(){}

    //toString method
    @Override
    public String toString() {
        return "Item_FromXML_ToXML{" +
                "title_date='" + title_date + '\'' +
                ", subtitle_month='" + subtitle_month + '\'' +
                ", subtitle_day='" + subtitle_day + '\'' +
                ", title_wordOfTheDay='" + title_wordOfTheDay + '\'' +
                ", subtitle_spanishWord='" + subtitle_spanishWord + '\'' +
                ", subtitle_englishWord='" + subtitle_englishWord + '\'' +
                ", Example='" + titleExample + '\'' +
                ", spanishExample_1='" + spanishExample_1 + '\'' +
                ", englishExample_1='" + englishExample_1 + '\'' +
                ", spanishExample_2='" + spanishExample_2 + '\'' +
                ", englishExample_2='" + englishExample_2 + '\'' +
                '}';
    }

    //GETTERS and SETTERS:

    //DATES

    public String getTitle_date() {
        return title_date;
    }

    public String getSubtitle_month() {
        return subtitle_month;
    }

    public String getSubtitle_day() {
        return subtitle_day;
    }

    public void setSubtitle_month(String subtitle_month) {
        this.subtitle_month = subtitle_month;
    }

    public void setSubtitle_day(String subtitle_day) {
        this.subtitle_day = subtitle_day;
    }

    //WORD OF THE DAY

    public String getTitle_wordOfTheDay() {
        return title_wordOfTheDay;
    }

    public String getSubtitle_spanishWord() {
        return subtitle_spanishWord;
    }

    public void setSubtitle_spanishWord(String subtitle_spanishWord) {
        this.subtitle_spanishWord = subtitle_spanishWord;
    }

    public String getSubtitle_englishWord() {
        return subtitle_englishWord;
    }

    public void setSubtitle_englishWord(String subtitle_englishWord) {
        this.subtitle_englishWord = subtitle_englishWord;
    }

    //EXAMPLES

    public String getTitleExample() {
        return titleExample;
    }

    public String getSpanishExample_1() {
        return spanishExample_1;
    }

    public void setSpanishExample_1(String spanishExample_1) {
        this.spanishExample_1 = spanishExample_1;
    }

    public String getEnglishExample_1() {
        return englishExample_1;
    }

    public void setEnglishExample_1(String englishExample_1) {
        this.englishExample_1 = englishExample_1;
    }

    public String getSpanishExample_2() {
        return spanishExample_2;
    }

    public void setSpanishExample_2(String spanishExample_2) {
        this.spanishExample_2 = spanishExample_2;
    }

    public String getEnglishExample_2() {
        return englishExample_2;
    }

    public void setEnglishExample_2(String englishExample_2) {
        this.englishExample_2 = englishExample_2;
    }

}
