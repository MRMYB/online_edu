package com.online.eduservice.service;

import com.online.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.eduservice.entity.vo.CourseInfoVo;
import com.online.eduservice.entity.vo.CoursePublicVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);


    CoursePublicVo getPublicCourseIfo(String id);
}
