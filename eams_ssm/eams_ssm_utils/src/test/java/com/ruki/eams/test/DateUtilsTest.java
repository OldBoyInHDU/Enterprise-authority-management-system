package com.ruki.eams.test;

import com.ruki.eams.utils.DateUtils;
import org.junit.Test;

import java.util.Date;

public class DateUtilsTest {
    @Test
    public void test01(){
        Date date = new Date();
        String string = DateUtils.date2String(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(string);

    }

}
