package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.entity.EduVideo;

/**
 * 课程视频(EduVideo)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduVideoService extends IService<EduVideo>{

    boolean removeByCourseId(String id);

//    boolean removeUploadVideo(String videoSourceId);
}