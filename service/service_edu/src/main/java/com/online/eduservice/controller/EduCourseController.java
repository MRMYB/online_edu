package com.online.eduservice.controller;


import com.online.commonutils.R;
import com.online.eduservice.entity.vo.CourseInfoVo;
import com.online.eduservice.entity.vo.CoursePublicVo;
import com.online.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //添加课程基本信息
    @ApiOperation(value = "课程信息添加")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);

    }

    //根据课程id查询课程基本信息
    @ApiOperation(value = "查询课程信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public  R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return  R.ok().data("courseInfoVo",courseInfoVo);
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
    @GetMapping("/getPublicCourseIfo/{id}")
    public  R getPublicCourseIfo(@PathVariable String id){
        CoursePublicVo coursePublicVo = courseService.getPublicCourseIfo(id);
        return  R.ok().data("coursePublicVo",coursePublicVo);
    }

}

