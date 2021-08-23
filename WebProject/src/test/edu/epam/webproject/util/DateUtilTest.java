package edu.epam.webproject.util;

import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.assertEquals;

public class DateUtilTest {
    @Test
    public void addDaysTestTrue(){
        Date date = new Date();
        date = DateUtil.makeDateComparable(date);
        Date actual = DateUtil.addDays(date, 2);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, Calendar.AUGUST, 25, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date expected = calendar.getTime();
        assertEquals(actual, expected);
    }
    @Test
    public void dayOfMonthTestTrue(){
        Date date = new Date();
        int actual = DateUtil.getDayOfMonth(date);
        int expected = 23;
        assertEquals(actual, expected);
    }
    @Test
    public void getMonthTestTrue(){
        Date date = new Date();
        int actual = DateUtil.getMonth(date);
        int expected = 7;
        assertEquals(actual, expected);
    }
}
