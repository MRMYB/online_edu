package com.online.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.eduservice.entity.EduSubject;
import com.online.eduservice.entity.excel.SubjectData;
import com.online.eduservice.entity.subject.OneSubject;
import com.online.eduservice.entity.subject.TwoSubject;
import com.online.eduservice.listener.SubjectExcelListener;
import com.online.eduservice.mapper.EduSubjectMapper;
import com.online.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    //添加课程分类  文件上传
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        //获取文件输入流
        try {
            InputStream in = file.getInputStream();
            //读取excel内容，数据库保存
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //课程分类列表 树形结构
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有一级分类数据  条件 parent_id =0   利用条件构造器QueryWrapper<>  eq等于 ne不等于
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        //查询条件
        wrapperOne.eq("parent_id","0");
        //一级分类查询结果
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);


        //2.查询出二级分类数据
        //条件 parent_id !=0 利用条件构造器QueryWrapper<>
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
//        wrapperTwo.ne("parent_id", "0");
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);


        //创建list集合 用于存储最终封装的一二级分类数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3.遍历查询出的所有一级分类数据 进行封装
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //得到查询出一级分类列表oneSubjectList 中的每一个eduSubject 并存储到OneSubject中
            EduSubject eduSubject = oneSubjectList.get(i);

            OneSubject oneSubject = new OneSubject();
            //第一种 一个一个set (当字段较少时可以使用 ,不推荐)
            //oneSubject.setId(eduSubject.getId());
            //oneSubject.setTitle(eduSubject.getTitle());
            //第二种 使用内置工具类BeanUtils.copyProperties(拷贝目标，拷贝到的对象目标)
            BeanUtils.copyProperties(eduSubject,oneSubject);

            //封装到finalSubject中
            finalSubjectList.add(oneSubject);

            //在一级分类中遍历循环查询所有二级分类
            //创建twoFinalSubject list集合存储二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取得到每一个二级分类
                EduSubject tSubject = twoSubjectList.get(m);
                //判断二级分类的parentId是否与一级分类相同
                //相同就封装到一级分类中
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    //拷贝
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }

            //把一级下面所有的二级分类放到一级分类中
            oneSubject.setChildren(twoFinalSubjectList);

        }
        //最终返回所有一二级分类数据 树形结构
        return finalSubjectList;
    }
}
