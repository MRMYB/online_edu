package com.online.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//课程一级分类
@Data
public class OneSubject {

    private String id;  //一级分类id

    private String title;  //一级分类名称

    //一级分类 与二级分类的关系  一对多关系 children
    private List<TwoSubject> children = new ArrayList<>();

}
