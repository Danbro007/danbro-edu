package com.danbro.vod.service.Impl;

import com.aliyuncs.exceptions.ClientException;
import com.danbro.vod.service.VodService;
import com.danbro.vod.utils.VodAliYunUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Classname VodServiceImpl
 * @Description TODO
 * @Date 2020/12/30 20:18
 * @Author Danrbo
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile multipartFile) throws IOException {
        // 视频文件名
        String filename = multipartFile.getOriginalFilename();
        // 上传到阿里云后显示的视频名
        String title = filename.substring(0, filename.lastIndexOf("."));
        return VodAliYunUtils.uploadVideo(title, multipartFile.getOriginalFilename(), multipartFile.getInputStream());
    }

    @Override
    public void deleteVideo(String videoId) throws ClientException {
        VodAliYunUtils.deleteVideo(videoId);
    }

    @Override
    public void batchVideoList(List<String> videoList) throws ClientException {
        VodAliYunUtils.batchDeleteVideo(videoList);
    }
}
