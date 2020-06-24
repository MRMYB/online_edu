package com.online.demo.excel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeTest02 {

    public static void main(String[] args) {
        System.out.println(TimeTest02.getNewTime());

    }

    public  static  String getNewTime(){
        //判断 时间上下午
        GregorianCalendar ca = new GregorianCalendar();
        String a = "上午";
        String b ="下午";
        String ap=ca.get(GregorianCalendar.AM_PM)==0?a:b;

        Calendar now = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);// 初始化year对象;
        int month = now.get(Calendar.MONTH) + 1;// 略历中一年中的第一个月是 JANUARY，它为 0；所以要加1；
        int day = now.get(Calendar.DAY_OF_MONTH);// 指示一月中的天;
        int hour = now.get(Calendar.HOUR_OF_DAY);// 指示一天中的小时;
        int minute = now.get(Calendar.MINUTE);// 指示一小时中的分钟;
        int second = now.get(Calendar.SECOND);// 指示一分钟中的秒;
        int millisecond = now.get(Calendar.MILLISECOND);// 指示一秒中的毫秒;
        int week = now.get(Calendar.DAY_OF_WEEK);// 指示一个星期中的某天。该字段可取的值为 SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY// 和 SATURDA


        String str = "日一二三四五六";// 英语国家星期从星期日开始计算。所以我们需要倒退一天来达到目的。
//        System.out.println("现在日期是：" + year + "年" + month + "月" + day + "日");// 输出日期
//        System.out.println("当前时间为：" + hour + "点" + minute + "分" + second + "秒" + millisecond + "毫秒");// 输出时间
//        System.out.println("目前时间毫秒数：" + now.getTimeInMillis());// 输出毫秒数
//        System.out.println("今天是一个星期中的 星期" + str.substring(week - 1, week));// 输出星期信息

        Date d = new Date();// 创建当前系统时间的对象
        System.out.println(d);// 输出系统时间对象

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义指定模式时间对象
        String dateNowStr = sdf.format(d);// 转换时间格式
        System.out.println("格式化后的日期：" + dateNowStr);// 输出转换格式后的时间

        return  "现在日期是：" + year + "年" + month + "月" + day + "日"+" "+"星期"+ str.substring(week - 1, week)+" "+
                ap+ hour + "点" + minute + "分" + second + "秒";

    }
}
