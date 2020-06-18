package com.online.eduservice.entity.vo;

import lombok.Data;

//用于课程发布显示信息
@Data
public class CoursePublishVo {

    private  String id;  //课程id
    private  String title;  //课程标题
    private  String cover;  //课程封面
    private  Integer lessonNum; //课程总课时
    private  String subjectLevelOne; //一级分类
    private  String subjectLevelTwo; //二级分类
    private  String teacherName;  //讲师名称
    private  String price; //费用   只用于显示
}
