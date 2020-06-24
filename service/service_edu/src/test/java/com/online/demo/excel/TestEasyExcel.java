package com.online.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel 写的操作
        //excel文件路径
//        String fileName = "d:\\Desktop\\Project\\read.xlsx";
////        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
        //实现excel 读的操作
        String fileName = "d:\\Desktop\\read.xlsx";
//        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
        EasyExcel.read(fileName,MergeExcel.class,new ExcelListener()).sheet().doRead();
    }

    //创建添加学生列表
//    private static List<DemoData> getData() {
//        List<DemoData> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            DemoData data = new DemoData();
//            data.setSname("小王" + i);
//            data.setSno(i);
//            list.add(data);
//        }
//
//        return list;
//    }
}
