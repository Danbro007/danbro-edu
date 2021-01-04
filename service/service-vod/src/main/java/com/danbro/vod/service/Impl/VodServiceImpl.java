package com.danbro.vod.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.danbro.vod.service.VodService;
import com.danbro.vod.utils.VodAliYunUtils;
import org.apache.commons.lang.StringUtils;
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
        UploadStreamRequest request = new UploadStreamRequest(vodAliYunUtils.getAccessKeyId(),
                vodAliYunUtils.getAccessKeySecret(),
                title,
                filename,
                multipartFile.getInputStream());
        UploadVideoImpl uploader = new UploadVideoImpl();
        request.setPrintProgress(true);
        UploadStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            return response.getVideoId();
        }
        return null;
    }

    @Override
    public void deleteVideo(String videoId) throws ClientException {
        DefaultAcsClient client = vodAliYunUtils.initVodClient();
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }

    @Override
    public void batchDeleteVideo(String videoList) throws ClientException {
        DefaultAcsClient client = vodAliYunUtils.initVodClient();
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoList);
        client.getAcsResponse(request);
    }


}