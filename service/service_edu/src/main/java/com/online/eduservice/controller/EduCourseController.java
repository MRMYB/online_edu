package com.online.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.commonutils.R;
import com.online.eduservice.entity.EduCourse;
import com.online.eduservice.entity.vo.CourseInfoVo;
import com.online.eduservice.entity.vo.CoursePublishVo;
import com.online.eduservice.entity.vo.CourseQuery;
import com.online.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
@Api(value="课程接口",tags = "课程管理模块")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //课程列表 基本实现
    @GetMapping
    public R getCourseList() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    //TODO 完善条件查询分页 --参考教师条件查询分页实现
    //条件查询待分页的方法
    @ApiOperation(value = "条件分页查询")
    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery){

        Page<EduCourse> pageCourse = new Page<>(current,limit);

        QueryWrapper<EduCourse> wrapper =new QueryWrapper<>();

        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //按时间排序
        wrapper.orderByDesc("gmt_create");

        //调用条件查询分页方法
        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }


    //添加课程基本信息
    @ApiOperation(value = "课程信息添加")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);

    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @ApiOperation(value = "修改课程信息")
    @PostMapping("/updateCourseInfo")
    public  R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }


    //根据课程id 查询课程确认信息
    @ApiOperation(value = "课程发布信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public  R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.getPublishCourseInfo(id);
        return  R.ok().data("publishCourse",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @ApiOperation(value = "课程信息最终发布")
    @PostMapping("/publishCourse/{id}")
    public  R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal"); //设置课程发布状态
        courseService.updateById(eduCourse);

        return  R.ok();
    }

    //删除课程
    //逻辑删除
    @DeleteMapping("/{courseId}")
    public  R deleteCourse(@PathVariable String courseId ){
        courseService.removeCourse(courseId);
        return  R.ok();
    }




}

