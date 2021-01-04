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

    /**
     * 通过 CourseId 删除所有的小节
     * @param id
     * @return
     */
    Boolean removeByCourseId(String id);

    /**
     * 先删除阿里云里的视频然后再把小节ID里的 videoSourceId 和 videoOriginalName 清空
     * @param videoId 小节ID
     * @return 删除结果
     */
    Boolean removeAliyunVideo(String videoId);

}