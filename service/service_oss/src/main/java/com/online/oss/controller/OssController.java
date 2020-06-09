package com.online.oss.controller;

import com.online.commonutils.R;
import com.online.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "Oss文件上传")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping
    public R  uploadFileAvatar(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        // "url": "https://edu-online-2020.oss-cn-shenzhen.aliyuncs.com/flower01.jpg"
        // "url": "https://edu-online-2020.oss-cn-shenzhen.aliyuncs.com/2020/06/097c24006c92264d978c35293b0ddb109eflower01.jpg"
        return  R.ok().data("url",url);
    }


}
