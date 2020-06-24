package com.online.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class MergeExcel {

    //设置excel表头名称

    @ExcelProperty(index = 0,value = "投标要素")
    private String bidElement;

    @ExcelProperty(index = 1,value = "具体情况")
    private String detail;

    @ExcelProperty(index = 2,value = "对应分值")
    private String score;

    @ExcelProperty(index = 3,value = "说明")
    private String explain;

    @ExcelProperty(index = 4,value = "项目名称")
    private String projectName;
}
