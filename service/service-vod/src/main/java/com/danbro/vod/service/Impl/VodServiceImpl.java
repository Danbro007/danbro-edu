package com.danbro.vod.service.Impl;

import java.io.IOException;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.vod.service.VodService;
import com.danbro.vod.utils.VodAliYunUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation("上传单个视频")
    @Override
    public String uploadVideo(MultipartFile multipartFile) throws IOException {
        // 视频文件名
        String filename = multipartFile.getOriginalFilename();
        if (filename == null || StringUtils.isEmpty(filename.trim())) {
            throw new EduException(ResultCode.VOD_FILENAME_IS_EMPTY);
        }
        // 上传到阿里云后显示的视频名
        String title = filename.substring(0, filename.lastIndexOf("."));
        // 创建上传视频的请求
        UploadStreamRequest request = new UploadStreamRequest(
                vodAliYunUtils.getAccessKeyId(),
                vodAliYunUtils.getAccessKeySecret(),
                title,
                filename,
                multipartFile.getInputStream()
        );
        UploadVideoImpl uploader = new UploadVideoImpl();
        // 打印上传文件的进度条
        request.setPrintProgress(true);
        // 上传结果的响应
        UploadStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            return response.getVideoId();
        }
        throw new EduException(ResultCode.VOD_UPLOAD_VIDEO_FAILURE);
    }

    @ApiOperation("删除单个在阿里云的视频")
    @Override
    public void deleteVideo(String videoId) {
        DefaultAcsClient client;
        try {
            client = vodAliYunUtils.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new EduException(ResultCode.VOD_CLIENT_CONNECTION_ERROR);
        }

    }

    @ApiOperation("批量删除在阿里云的视频")
    @Override
    public void batchDeleteVideo(String videoList) {
        DefaultAcsClient client;
        try {
            client = vodAliYunUtils.initVodClient();
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoList);
            client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new EduException(ResultCode.VOD_CLIENT_CONNECTION_ERROR);
        }
    }

    @ApiOperation("获取视频的播放凭证")
    @Override
    public String getVideoPlayAuth(String videoId) {
        DefaultAcsClient client;
        try {
            client = vodAliYunUtils.initVodClient();
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
            return acsResponse.getPlayAuth();
        } catch (ClientException e) {
            throw new EduException(ResultCode.VOD_CLIENT_CONNECTION_ERROR);
        }
    }
}

