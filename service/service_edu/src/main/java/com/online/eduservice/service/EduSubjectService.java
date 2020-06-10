package com.online.eduservice.service;

import com.online.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

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
}
