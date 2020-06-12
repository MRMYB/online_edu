package com.online.eduservice.controller;


import com.online.commonutils.R;
import com.online.eduservice.entity.EduChapter;
import com.online.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
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
@Api(value = "章节管理接口", tags = "章节管理接口")
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表，根据课程id 进行查询 章节与小节
    @GetMapping("/getChapterVideo/{courseId}")
    public  R getChapterVideo(@PathVariable String courseId){

        return  R.ok();
    }


    //添加章节
    @ApiOperation(value = "添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id  查询章节
    @ApiOperation(value = "根据章节id查询")
    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);

        return R.ok().data("chapter", eduChapter);
    }

    //根据章节id修改章节信息
    @ApiOperation(value = "修改章节")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        boolean flag = chapterService.updateById(eduChapter);
        if (flag) {
            return R.ok();
        } else {
            return R.error().message("修改章节失败");
        }
    }

    //删除章节 根据id
    //删除章节  章节删除会一同删除掉其中所有的小节
    @ApiOperation(value = "删除章节")
    @DeleteMapping("/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        chapterService.deleteChapter(chapterId);
        return R.ok();
    }

}

