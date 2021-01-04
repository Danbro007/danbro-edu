package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.entity.EduVideo;

/**
 * 课程视频(EduVideo)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 通过 CourseId 删除所有的小节
     * 先找到与 CourseId 相关的小节视频ID，然后到阿里云删除，最后再把数据库数据删除。
     *
     * @param courseId 课程id
     * @return
     */
    Boolean removeByCourseId(String courseId);

    /**
     * 先删除阿里云里的视频然后再把小节ID里的 videoSourceId 和 videoOriginalName 清空
     *
     * @param videoId 视频ID
     * @return 删除结果
     */
    Boolean removeAliyunVideo(String videoId);

    /**
     * 先找到 chapter 下所有的 videoSourceId ，
     * 然后通过这些id把阿里云的视频删除，最后再删除数据库的数据。
     *
     * @param chapterId
     * @return
     */
    Boolean removeByChapterId(String chapterId);

}