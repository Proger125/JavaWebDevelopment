package edu.epam.webproject.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class that provides methods to interact with {@link Date} objects
 */
public class DateUtil {
    /**
     * Private constructor without parameters
     */
    private DateUtil(){}

    /**
     * Adds a certain amount of days to the date
     * @param date - certain date
     * @param days - amount of days to be addes
     * @return {@link Date} which will come after adding days
     */
    public static Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * Returns day number in month of certain {@link Date}
     * @param date - certain {@link Date}
     * @return - day number in month
     */
    public static int getDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * Returns month number in year of certain {@link Date}
     * @param date - certain {@link Date}
     * @return - month number in month
     */
    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Changes time of certain {@link Date} to 00:00:00
     * @param date - certain {@link Date}
     * @return - {@link Date} with time = 00:00:00
     */
    public static Date makeDateComparable(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Returns {@link List<Date>} of 60 days after current date
     * @return {@link List<Date>}
     */
    public static List<Date> setUpDateList(){
        List<Date> list = new ArrayList<>();
        Date currentDate = new Date();
        Date lastDate = DateUtil.addDays(currentDate, 60);
        for (Date date = currentDate; date.before(lastDate) || date.equals(lastDate); date = DateUtil.addDays(date, 1)){
            list.add(makeDateComparable(date));
        }
        return list;
    }
}
