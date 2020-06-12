package com.online.eduservice.mapper;

import com.online.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.eduservice.entity.vo.CoursePublicVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublicVo getPublicCourseIfo(String courseId);
}
