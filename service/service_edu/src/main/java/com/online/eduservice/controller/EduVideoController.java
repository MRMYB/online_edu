package com.online.eduservice.controller;


import com.online.commonutils.R;
import com.online.eduservice.entity.EduChapter;
import com.online.eduservice.entity.EduVideo;
import com.online.eduservice.entity.chapter.VideoVo;
import com.online.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
@Api(value = "章节小节接口", tags = "小节视频操作")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    //添加小节信息
    @ApiOperation(value = "添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //根据小节id  查询小节
    @ApiOperation(value = "根据小节节id查询")
    @GetMapping("/getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);

        return R.ok().data("video", eduVideo);
    }

    //TODO 修改小节
    //根据小节id修改小节信息
    @ApiOperation(value = "修改小节")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        boolean flag = videoService.updateById(eduVideo);
        if (flag) {
            return R.ok();
        } else {
            return R.error().message("修改小节失败");
        }
    }


    //删除小节信息
    //TODO 未实现视频的操作
    @ApiOperation(value = "删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        videoService.removeById(id);
        return R.ok();
    }



}

