package com.danbro.vod.service;

import java.io.IOException;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname VodService
 * @Description TODO
 * @Date 2020/12/31 15:27
 * @Author Danrbo
 */
public interface VodService {
    /**
     * 上传视频到阿里云
     *
     * @param multipartFile 上传的视频对象
     * @return 视频的ID
     * @throws IOException 上传文件的IO异常
     */
    String uploadVideo(MultipartFile multipartFile) throws IOException;

    /**
     * 通过视频ID删除在阿里云的视频
     *
     * @param videoId 视频ID
     * @throws ClientException 访问阿里云视频点播的客户端异常
     */
    void deleteVideo(String videoId) throws ClientException;

    /**
     * 通过多个视频ID批量删除阿里云的视频
     *
     * @param videoList 多个视频ID的字符串
     * @throws ClientException 访问阿里云视频点播的客户端异常
     */
    void batchDeleteVideo(String videoList) throws ClientException;

    /**
     * 根据视频ID获取到视频的播放凭证
     *
     * @param videoId 视频ID
     * @return 视频的播放凭证
     */
    String getVideoPlayAuth(String videoId) throws ClientException;


}