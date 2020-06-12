package com.online.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.eduservice.entity.EduChapter;
import com.online.eduservice.entity.EduVideo;
import com.online.eduservice.entity.chapter.ChapterVo;
import com.online.eduservice.entity.chapter.VideoVo;
import com.online.eduservice.mapper.EduChapterMapper;
import com.online.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.eduservice.service.EduVideoService;
import com.online.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    //注入章节小节service
    @Autowired
    private EduVideoService videoService;


    //课程大纲列表，根据课程id查询课程大纲信息
    //一门课程 有多个章节
    //一个章节 有多个小节
    @Override
    public List<ChapterVo> getChapterVideo(String courseId) {
        //1 根据课程id 查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter =new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChaptersList = baseMapper.selectList(wrapperChapter);

        //2.根据课程
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);


        //创建list集合 进行封装
        List<ChapterVo> finalList =new ArrayList<>();

        //3.遍历章节list 进行封装
        for (int i = 0; i < eduChaptersList.size(); i++) {
            EduChapter eduChapter = eduChaptersList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //把拷贝的章节list 添加到finalList集合中
            finalList.add(chapterVo);

            //创建封装小节的list集合
            List<VideoVo> videoList = new ArrayList<>();
            //遍历小节内容
            for (int m = 0; m < eduVideoList.size(); m++) {
                EduVideo eduVideo =eduVideoList.get(m);
                //判断：小节所属章节id 是否和 章节里面的id是否相同
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    //封装小节内容
                    VideoVo videoVo =new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoList.add(videoVo);

                }
            }
            //把封装之后的小节list集合 放到章节对象中
            chapterVo.setChildren(videoList);
        }

        return finalList;
    }

    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterId章节id 查询小节表 如果查询到数据，则不删除，否则删除
        QueryWrapper<EduVideo> wrapper =new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if(count>0){
            throw new GuliException(20001,"不能删除");
        }else {
            int result = baseMapper.deleteById(chapterId);
//            if(result>0){
//                return true;
//            }else{
//                return false;
//            }
            return  result>0; //0 失败 1 成功
        }



    }
}
