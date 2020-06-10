package com.online.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.online.eduservice.entity.EduSubject;
import com.online.eduservice.entity.excel.SubjectData;
import com.online.eduservice.listener.SubjectExcelListener;
import com.online.eduservice.mapper.EduSubjectMapper;
import com.online.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author myb
 * @since 2020-06-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        //获取文件输入流
        try{
            InputStream in = file.getInputStream();
            //读取excel内容，数据库保存
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
