package edu.epam.webproject.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private DateUtil(){}
    public static Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    public static int getDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }
    public static Date makeDateComparable(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
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
