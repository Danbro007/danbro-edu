package com.danbro.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Classname VodService
 * @Description TODO
 * @Date 2020/12/31 10:36
 * @Author Danrbo
 */
public interface VodService {
    String uploadVideo(MultipartFile multipartFile) throws IOException;

    void deleteVideo(String videoId) throws ClientException;

    void batchVideoList(List<String> videoList) throws ClientException;
}
