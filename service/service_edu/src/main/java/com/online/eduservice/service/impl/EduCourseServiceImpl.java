package com.online.eduservice.service.impl;

import com.online.eduservice.entity.EduCourse;
import com.online.eduservice.entity.EduCourseDescription;
import com.online.eduservice.entity.vo.CourseInfoVo;

import com.online.eduservice.entity.vo.CoursePublishVo;
import com.online.eduservice.mapper.EduCourseMapper;
import com.online.eduservice.service.EduChapterService;
import com.online.eduservice.service.EduCourseDescriptionService;
import com.online.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.eduservice.service.EduVideoService;
import com.online.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //添加课程  EduCourse实体类中没有 description课程简介描字段,需注入进来实现数据库操作
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    //添加课程信息 与课程描述信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse =new EduCourse();
        //拷贝
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);//影响数据库数 0插入失败 1插入成功
        if(insert ==0){  //判断是否插入成功
            throw  new GuliException(20001,"添加课程信息失败");
        }

        //获取添加之后的课程信息
        String cid = eduCourse.getId();
        //2.添加课程简介描述信息  课程与课程描述的关系 --1 对 1

        EduCourseDescription courseDescription =new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);//课程表与课程描述表的关联
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    //根据课程id 查询课程信息
    //课程信息包含  课程表和课程描述表 先 获取课程表 再根据获取的课程id 得到课程描述表信息
    // 最后返回VO中的CourseInfoVo
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.根据课程id 查询课程信息
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo =new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        //2.获取课程描述表
        EduCourseDescription courseDescription =courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if(update == 0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        //2.修改课程描述
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseInfoVo.getId());
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(courseDescription);

    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }


    //删除课程信息 同时删除课程下面的所有信息
    //从底部删除
    @Override
    public void removeCourse(String courseId) {
        //1.删除小节
        videoService.removeVideoByCourseId(courseId);

        //2.删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3.根据课程id 删除课程简介描述
        courseDescriptionService.removeById(courseId);

        //4.根据课程id 删除课程信息
        int result = baseMapper.deleteById(courseId);
        if(result==0){
            throw new  GuliException(20001,"删除课程失败");
        }

    }
}
