package com.danbro.edu.controller;

import com.danbro.edu.dto.EduVideoInsertInPutDto;
import com.danbro.edu.dto.EduVideoUpdateInPutDto;
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

    @ApiOperation("添加视频")
    @PostMapping("video")
    public Result insertVideo(@RequestBody EduVideoInsertInPutDto inputVideo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(inputVideo, eduVideo);
        boolean b = eduVideoService.save(eduVideo);
        if (b) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.INSERT_VIDEO_FAILURE);
    }

    @ApiOperation("修改视频")
    @PutMapping("video")
    public Result updateVideo(@RequestBody EduVideoUpdateInPutDto updateVideo) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(updateVideo, eduVideo);
        boolean b = eduVideoService.updateById(eduVideo);
        if (b) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.UPDATE_VIDEO_FAILURE);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("video/{id}")
    public Result updateVideo(@PathVariable String id) {
        boolean b = eduVideoService.removeById(id);
        if (b) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.DELETE_VIDEO_FAILURE);
    }

}