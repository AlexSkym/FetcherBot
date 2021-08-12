package forvo_API;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.Locale;
import java.util.TimeZone;

import static java.lang.Integer.parseInt;

public class GettingLocalTime {

    public static void main(String[] args) {


        TemporalAccessor temporalAccessor;
        temporalAccessor = new TemporalAccessor() {
            @Override
            public boolean isSupported(TemporalField temporalField) {
                return false;
            }

            @Override
            public long getLong(TemporalField temporalField) {
                return 0;
            }
        };

        System.out.println("temporalAccessor: " + temporalAccessor);

        TimeZone timeZone = TimeZone.getTimeZone(ZoneId.systemDefault());
        String[] a = TimeZone.getAvailableIDs();

        for (String n:a
             ) {
            System.out.println(n);
        }
        timeZone = TimeZone.getTimeZone("America/New_York");

        System.out.println("Timezone " + timeZone);

        LocalTime localTime = LocalTime.now();
        //LocalTime localTime = LocalTime.now();

        System.out.println("localTime: " + localTime);

        ZoneId zoneId = timeZone.toZoneId();
        localTime = LocalTime.now(zoneId);
        System.out.println("\nlocalTime with ZoneId: " + localTime);

        ZoneId zoneId1 = ZoneId.of("America/New_York");
        localTime = LocalTime.now(zoneId);
        System.out.println("\nlocalTime with ZoneId1: " + localTime);


        //comparingTwoDates();
        pasandoEnLimpio_1();
    }

    private static void comparingTwoDates(){
        String timeString1 = "2:00 PM";
        String timeString2 = "9:00 AM";

        DateTimeFormatter timeFormatParser
                = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
        LocalTime time1 = LocalTime.parse(timeString1, timeFormatParser);
        LocalTime time2 = LocalTime.parse(timeString2, timeFormatParser);


        //Getting the idea xd
        int hour = LocalTime.now().getHour();
        String minutes = LocalTime.now().getMinute() + "";
        String AM_PM = "AM";


        //cal of PM AM
        if (hour > 12) AM_PM = "PM";
        else AM_PM = "AM";
        //cal of getMinutes
        if (parseInt(minutes) < 10){
            minutes = "0"+minutes;
        }

        String timeNowString = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " " + AM_PM;
        System.out.println("timeNowString: " + timeNowString);
        LocalTime timeNow = LocalTime.parse(timeNowString, timeFormatParser);

        System.out.println("DateTimeFormatter (timeFormatParser - ENGLISH): " + timeFormatParser);

        if (time1.isAfter(time2)) {
            System.out.println("After");
        }

        System.out.println("timeNow: " + timeNow);

        String inAMinute = "5:22 AM";
        int inAMinute1 = 30;

        do {
            if (LocalTime.now().getMinute() == inAMinute1
                    && LocalTime.now().getHour() == 5){
                System.out.println("El tiempo a concluido");
                break;
            }

        } while (true);

    }

    private static void pasandoEnLimpio_1(){




        ZoneId zoneId = ZoneId.of("America/New_York");

        int theHour = 5, theMinute = 0;

        do {
            if (LocalTime.now(zoneId).getHour() == theHour &&
                    LocalTime.now(zoneId).getMinute() == theMinute){

                System.out.println("El tiempo a concluido");

                //making the message not working above.
                while (LocalTime.now(zoneId).getMinute() == theMinute) {
                    if (LocalTime.now(zoneId).getMinute() != theMinute){
                        break;
                    }
                }

            }else if (LocalTime.now(zoneId).getHour() == theHour &&
                    LocalTime.now(zoneId).getMinute() == theMinute+1){

                System.out.println("El tiempo se ha pasado");
                break;
            }

        } while (true);
    }

    private static void pasandoEnLimpio_2(){

        ZoneId zoneId = ZoneId.of("America/New_York");

        int theHour = 5, theMinute = 0;

        do {
            if (LocalTime.now(zoneId).getHour() == theHour &&
                    LocalTime.now(zoneId).getMinute() == theMinute){

                System.out.println("El tiempo a concluido");

                //making the message not working above.
                while (LocalTime.now(zoneId).getMinute() == theMinute) {
                    if (LocalTime.now(zoneId).getMinute() != theMinute){
                        break;
                    }
                }

            }else if (LocalTime.now(zoneId).getHour() == theHour &&
                    LocalTime.now(zoneId).getMinute() == theMinute+1){

                System.out.println("El tiempo se ha pasado");
                break;
            }

        } while (true);
    }

}
