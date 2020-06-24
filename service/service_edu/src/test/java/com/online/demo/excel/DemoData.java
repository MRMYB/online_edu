package com.online.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置excel表头名称
//    @ExcelProperty(index = 0,value = "学生编号")
    @ExcelProperty(index = 0,value = "一级目录")
    private String sno;

    @ExcelProperty(index = 1,value = "二级目录")
    private String sname;
}
