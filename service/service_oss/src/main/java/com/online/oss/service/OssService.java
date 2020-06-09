package com.online.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    //上传文件到oss
    public  String uploadFileAvatar(MultipartFile file);
}
