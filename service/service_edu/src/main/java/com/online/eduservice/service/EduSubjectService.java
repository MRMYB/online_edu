package com.online.eduservice.service;

import com.online.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author myb
 * @since 2020-06-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    //定义上传保存excel 方法
    public  void saveSubject(MultipartFile file,EduSubjectService subjectService);


    //获取课程分类(树形)  获取 一级分类 一级分类之下又有二级分类
    public List<OneSubject> getAllOneTwoSubject();
}
