package com.online.eduservice.service;

import com.online.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.eduservice.entity.vo.CourseInfoVo;
import com.online.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id 查询课程信息
    CourseInfoVo getCourseInfo(String courseId);

    //更新修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);


    CoursePublishVo getPublishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);
}
