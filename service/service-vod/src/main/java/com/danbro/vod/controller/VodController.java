package com.danbro.vod.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import com.aliyuncs.exceptions.ClientException;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.vod.service.VodService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Classname VodController
 * @Description TODO 阿里云Vod服务控制器
 * @Date 2020/12/30 19:08
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("vod")
public class VodController {

    @Resource
    VodService vodService;

    @ApiOperation("上传视频到阿里云")
    @PostMapping("video")
    public Result<String> uploadVideo(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String videoId = vodService.uploadVideo(multipartFile);
            return Result.ofSuccess(videoId);
        } catch (IOException e) {
            return Result.ofFail(ResultCode.DELETE_VIDEO_FAILURE);
        }
    }

    @ApiOperation("通过视频ID删除单个视频")
    @DeleteMapping("video/{videoId}")
    public Result deleteVideo(@PathVariable String videoId) {
        try {
            vodService.deleteVideo(videoId);
            return Result.ofSuccess();
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.CLIENT_ALIYUN_CONNECTION_ERROR);
        }
    }

    @ApiOperation("通过视频ID批量删除视频")
    @DeleteMapping("video")
    public Result batchDeleteVideo(@RequestParam("videoList") List<String> videoList) {
        try {
            String videoIds = StringUtils.join(videoList.toArray(), ',');
            vodService.batchDeleteVideo(videoIds);
            return Result.ofSuccess();
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.CLIENT_ALIYUN_CONNECTION_ERROR);
        }
    }

    @ApiOperation("通过视频ID获取到播放视频的凭证")
    @GetMapping("video/auth/{videoId}")
    public Result<String> getVideoPlayAuth(@PathVariable String videoId) {
        try {
            String playAuth = vodService.getVideoPlayAuth(videoId);
            return Result.ofSuccess(playAuth);
        } catch (ClientException e) {
            throw new MyCustomException(ResultCode.CLIENT_ALIYUN_CONNECTION_ERROR);
        }
    }
}