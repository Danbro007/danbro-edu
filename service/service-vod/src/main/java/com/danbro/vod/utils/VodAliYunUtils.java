package com.danbro.vod.utils;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Classname VodAliYunUtils
 * @Description TODO
 * @Date 2020/12/31 16:31
 * @Author Danrbo
 */
@Component
public class VodAliYunUtils {
    @Value("${aliyun.vod.keyid}")
    private String accessKeyId;

    @Value("${aliyun.vod.keysecret}")
    private String accessKeySecret;

    /**
     * 点播服务接入区域
     */
    private static final String REGION_ID = "cn-shanghai";


    private DefaultAcsClient initVodClient() throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accessKeyId, accessKeySecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 上传视频到阿里云
     *
     * @param title
     * @param filename
     * @param inputStream
     * @return
     */
    public String uploadVideo(String title, String filename, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, filename, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        request.setPrintProgress(true);
        UploadStreamResponse response = uploader.uploadStream(request);
        if (response.isSuccess()) {
            return response.getVideoId();
        }
        return null;
    }

    /**
     * 删除视频
     *
     * @param videoId 视频ID
     * @return
     * @throws ClientException
     */
    public void deleteVideo(String videoId) throws ClientException {
        DefaultAcsClient client = initVodClient();
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }

//    public void batchDeleteVideo(List<String> videoList) throws ClientException {
//        StringBuilder videos = new StringBuilder();
//        for (int i = 0; i < videoList.size(); i++) {
//            if (i < videoList.size() - 1) {
//                videos.append(videoList.get(i)).append(",");
//            } else {
//                videos.append(videoList.get(i));
//            }
//        }
//        DefaultAcsClient client = initVodClient();
//        DeleteVideoRequest request = new DeleteVideoRequest();
//        request.setVideoIds(videos.toString());
//        client.getAcsResponse(request);
//    }
}