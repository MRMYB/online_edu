package com.online.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(index = 0,value = "学生编号")
    private Integer sno;

    @ExcelProperty(index = 1,value = "学生姓名")
    private String sname;
}
