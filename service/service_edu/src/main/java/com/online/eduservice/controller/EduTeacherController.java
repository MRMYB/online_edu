package com.online.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.commonutils.R;
import com.online.eduservice.entity.EduTeacher;
import com.online.eduservice.entity.ov.TeacherQuery;
import com.online.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author myb
 * @since 2020-05-29
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询所有老师列表
    //restful风格
//    @ApiOperation(value = "所有讲师列表")
//    @GetMapping("/findAll")
//    public List<EduTeacher> findAll() {
//        List<EduTeacher> list = eduTeacherService.list(null);
//        return list;
//    }

    //1 查询讲师表所有数据
    //rest风格  数据统一返回格式
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }


    //逻辑删除教师 @PathVariable 通过路径传值
//    @ApiOperation(value = "逻辑删除讲师")
//    @DeleteMapping("{id}")
//    public Boolean delTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable Long id) {
//        Boolean flag = eduTeacherService.removeById(id);
//        return flag;
//    }
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R delTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable Long id) {
        Boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //教师带条件分页查询
    @ApiOperation(value = "教师带条件分页查询")
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(long current, long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建Page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //获取查询条件 进行拼接
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断字段是否为空
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);//名字模糊查询
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);//eq()等于 精确查询
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);//ge() 大于等于  时间查询大于等于开始时间 数据库字段
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);//le() 小于等于 结束时间
        }

        //调用方法实现条件分页查询
        eduTeacherService.page(pageTeacher, wrapper);
        //返回结果
        long total = pageTeacher.getTotal();//获取总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//数据list集合

        return R.ok().data("total", total).data("records", records);
    }

    //添加教师
    @ApiOperation(value = "添加教师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody(required = false) EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据ID查找教师
    @ApiOperation(value = "根据ID查询教师")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable Long id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher", eduTeacher);
    }

    //更新修改操作 先查询 再更改
    @ApiOperation(value = "更新教师信息")
    @PostMapping("/updateTea")
    public R updateTea(@RequestBody(required = false) EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //PUT 根据ID修改教师信息
//    @ApiOperation(value = "根据ID修改教师信息")
//    @PutMapping("/updateById/{id}")
//    public R updateById(@PathVariable Long id){
//        EduTeacher eduTeacher =new EduTeacher();
//        eduTeacher.setId(id);
//        boolean flag = eduTeacherService.updateById(eduTeacher);
//        if (flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
//    }


}

