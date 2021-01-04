package com.danbro.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * @throws IOException
     */
    String uploadVideo(MultipartFile multipartFile) throws IOException;

    /**
     * 通过视频ID删除在阿里云的视频
     *
     * @param videoId 视频ID
     * @throws ClientException 访问阿里云视频点播的客户端异常
     */
    void deleteVideo(String videoId) throws ClientException;
}