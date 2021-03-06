package com.online.eduservice.service;

import com.online.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author myb
 * @since 2020-06-12
 */
public interface EduVideoService extends IService<EduVideo> {

    //根据课程id 删除小节信息
    public  void removeVideoByCourseId(String courseId);

}
