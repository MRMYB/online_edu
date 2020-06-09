package com.online.oss.service.serviceImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.oss.service.OssService;
import com.online.oss.utils.ConstantPropertiesUtils;
import org.apache.poi.hssf.record.DVALRecord;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endPoind = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
//            //第1种.创建OSS实例  普通设置
//            OSS ossClient = new OSSClientBuilder().build(endPoind, accessKeyId, accessKeySecret);
//
//            //获取上传文件输入流  异常处理 try-catch
//            InputStream inputStream = file.getInputStream();
//
//            //获取文件名称 file.getOriginalFilename()是得到上传时完整的名字
//            String fileName = file.getOriginalFilename();

            //第2种.创建OSS实例 设置加密文件名 防止上传同名文件被覆盖
            OSS ossClient = new OSSClientBuilder().build(endPoind, accessKeyId, accessKeySecret);

            //获取上传文件输入流  异常处理 try-catch
            InputStream inputStream = file.getInputStream();

            //获取文件名称 file.getOriginalFilename()是得到上传时完整的名字
            String fileName = file.getOriginalFilename();

            //1.在文件名称中随机添加唯一值uuid
            String uuid=UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;

            //2.把文件按照日期进行分类
            //获取当前时间
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            fileName=dataPath+fileName;



            //调用oss方法实现上传
            //第一个参数 Bucket名称
            //第二个参数 上传到oss文件路径和文件名称 例如 abc/abc/0014.jpg
            //第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            //最后关闭OssClient
            ossClient.shutdown();

            //把文件上传后文件路径返回
            //需要把上传到阿里云oss路径手动拼接
            //https://edu-online-2020.oss-cn-shenzhen.aliyuncs.com/flower01.jpg
            String url = "https://" + bucketName + "." + endPoind + "/" + fileName;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }
}
