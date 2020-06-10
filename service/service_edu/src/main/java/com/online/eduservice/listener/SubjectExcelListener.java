package com.online.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.eduservice.entity.EduSubject;
import com.online.eduservice.entity.excel.SubjectData;
import com.online.eduservice.service.EduSubjectService;
import com.online.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener 不能交给spring管理 需要自己new 不能注入其他对象
    public EduSubjectService subjectService;

    public SubjectExcelListener() {}//无参构造函数

    public SubjectExcelListener(EduSubjectService subjectService) {//有参构造函数
        this.subjectService = subjectService;
    }


    //读取excel内容 一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //先判断excel的数据
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //读取excel 一行一行读取，每一行有两个值 分别对应一级、二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null) {  //如果一级分类没有重复，则进行添加保存
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        //获取一级分类的id值  一级分类的id 就是 二级分类的parent_id
        String pid = existOneSubject.getId();

        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject ==null){//如果二级分类没有重复，则进行添加保存
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);

        }


    }


    // 根据分类名称 判断一级分类目录不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        //使用条件构造器
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);  //数据库title 字段名称 一个相同title 对应多条记录
        wrapper.eq("parent_id", "0");  //数据库中的parent_id 即 一级分类编号为0
        EduSubject oneSubject = subjectService.getOne(wrapper);  //根据条件获取单条记录
        return oneSubject;
    }

    // 根据分类名称 判断二级分类目录不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        //使用条件构造器
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title", name);  //数据库title 字段名称 一个相同title 对应多条记录
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);  //根据条件获取单条记录
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
