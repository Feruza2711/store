package uz.pdp.store.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static Date getCurrentDate(){
        return new Date();
    }


    public static Date getDateAfterFiveHour(){
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_YEAR, 100);

        return calendar.getTime();
    }


}
