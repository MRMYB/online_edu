package com.online.demo.excel;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTest {
    public static void main(String[] args) {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        Date time = new Date();
//        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");

        Calendar cal = Calendar.getInstance();
        int weeks=cal.get(Calendar.DAY_OF_WEEK) - 1;
        String s = df.format(time);


        System.out.println("当前时间是:" + s + ' ' + weekDays[cal.get(Calendar.DAY_OF_WEEK) - 1]);
        System.out.println("当前时间是:" + s + ' ' + weekDays[weeks]);
        TimeTest t = new TimeTest();
        t.out_week_hour_minute();
    }

    public void out_week_hour_minute() {
        long eight_hour_ms = 8 * 60 * 60 * 1000;//8小时*分*秒*毫秒,时区影响，北京时区:东八区。
        long per_week_ms = 7 * 24 * 60 * 60 * 1000;//7天*时*分*秒*毫秒,一周。
        long four_day_ms = 4 * 24 * 60 * 60 * 1000;//4天*时*分*秒*毫秒,时间戳起点是周四。
        long per_day_ms = 24 * 60 * 60 * 1000;//24时*分*秒*毫秒
        long per_hour_ms = 60 * 60 * 1000;//60分*秒*毫秒
        long per_minute_ms = 60 * 1000;//60秒*毫秒

        //计算方式:在时间戳的基础上增加上四天,然后除以一星期的时间长度取余,
        // 然后对上一步得到的余数除以一天的时间长度取商的值即是周几。
        // 注:该计算方式可能会受计算机所在时区的影响,因为取的时间戳来源于计算机。
        // 对时间戳起点周四增加四天，调整到第四天。
        // 对时间戳起点增加8小时，调整到东八区。
        long now_ms = System.currentTimeMillis() + four_day_ms + eight_hour_ms;
        long week_remainder_ms = now_ms % per_week_ms;//当前时间距离本周周一0点0分0秒0毫秒的时间跨度。
        long day_remainder_ms = week_remainder_ms % per_day_ms;//当前时间距离今天0点0分0秒0毫秒的时间跨度。
        long hour_remainer_ms = day_remainder_ms % per_hour_ms;//当前时间距离当前小时0分0秒0毫秒的时间跨度。

        //long week = week_remainder_ms / per_day_ms;

        long hour = day_remainder_ms / per_hour_ms;
        //long hour = day_remainder_ms/per_hour_ms<10?'0'+day_remainder_ms/per_hour_ms:day_remainder_ms/per_hour_ms;
        long minutes = (hour_remainer_ms / per_minute_ms);

        long minute = minutes<10?0+minutes:minutes;

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Date time = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cal = Calendar.getInstance();
        int weeks=cal.get(Calendar.DAY_OF_WEEK) - 1;
        String s = df.format(time);

//        System.out.println("星期" + week + " " + hour +":"+ minute);
        System.out.println("当前时间是:" + s+weekDays[weeks] + " " + hour +":"+ minute);
    }

}
