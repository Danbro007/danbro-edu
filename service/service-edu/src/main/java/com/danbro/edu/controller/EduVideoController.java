package com.danbro.edu.controller;

import com.danbro.edu.dto.InPutEduVideoInsertDto;
import com.danbro.edu.dto.InPutEduVideoUpdateDto;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 课程视频(EduVideo)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@CrossOrigin
@RestController
@RequestMapping("edu")
public class EduVideoController {
    /**
     * 服务对象
     */
    @Resource
    private EduVideoService eduVideoService;

    @ApiOperation("添加小节信息")
    @PostMapping("video")
    public Result insertVideo(@RequestBody InPutEduVideoInsertDto inputVideo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(inputVideo, eduVideo);
        boolean b = eduVideoService.save(eduVideo);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.INSERT_VIDEO_FAILURE);
    }

    @ApiOperation("修改小节信息")
    @PutMapping("video")
    public Result updateVideo(@RequestBody InPutEduVideoUpdateDto updateVideo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(updateVideo, eduVideo);
        boolean b = eduVideoService.updateById(eduVideo);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.UPDATE_VIDEO_FAILURE);
    }

    @ApiOperation("删除整个小节信息")
    @DeleteMapping("video/{id}")
    public Result deleteVideoInfo(@PathVariable String id) {
        Boolean b = eduVideoService.removeByVideoId(id);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_VIDEO_FAILURE);
    }

    @ApiOperation("通过小节ID删除小节里的视频（数据库和阿里云都要删除掉）")
    @DeleteMapping("video/aliyun/{id}")
    public Result deleteVideo(@PathVariable String id) {
        Boolean b = eduVideoService.removeAliyunVideo(id);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_VIDEO_FAILURE);
    }


}