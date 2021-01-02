package com.danbro.vod.service.Impl;

import com.aliyuncs.exceptions.ClientException;
import com.danbro.vod.service.VodService;
import com.danbro.vod.utils.VodAliYunUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Classname VodServiceImpl
 * @Description TODO
 * @Date 2020/12/31 15:28
 * @Author Danrbo
 */
@Service("vodService")
public class VodServiceImpl implements VodService {

    @Autowired
    VodAliYunUtils vodAliYunUtils;

    @Override
    public String uploadVideo(MultipartFile multipartFile) throws IOException {
        // 视频文件名
        String filename = multipartFile.getOriginalFilename();
        // 上传到阿里云后显示的视频名
        String title = filename.substring(0, filename.lastIndexOf("."));
        return vodAliYunUtils.uploadVideo(title, multipartFile.getOriginalFilename(), multipartFile.getInputStream());
    }

    @Override
    public void deleteVideo(String videoId) throws ClientException {
        vodAliYunUtils.deleteVideo(videoId);
    }

}