package com.online.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(value = "一级目录",index = 0)
    private  String oneSubjectName;

    @ExcelProperty(value = "二级目录",index = 1)
    private  String twoSubjectName;
}
