package com.online.vod.controller;

import com.online.commonutils.R;
import com.online.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @ApiOperation(value = "上传视频到阿里云")
    @PostMapping("/uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){
        //返回上传的视频id
        String videoId = vodService.uploadAlyiVideo(file);
        return  R.ok().data("videoId",videoId);
    }
}
