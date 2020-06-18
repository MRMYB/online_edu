package com.online.eduservice.service;

import com.online.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
public interface EduChapterService extends IService<EduChapter> {

    //课程大纲列表，根据课程id 进行查询 章节与小节
    List<ChapterVo> getChapterVideoByCourseId(String courseId);


    //删除章节  章节删除会一同删除掉其中所有的小节
    boolean deleteChapter(String chapterId);


    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);

}
