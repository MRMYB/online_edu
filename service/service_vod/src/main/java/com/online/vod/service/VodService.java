package com.online.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    //上传视频到阿里云
    String uploadAlyiVideo(MultipartFile file);
}
