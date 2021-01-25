package com.danbro.edu.controller;

import javax.annotation.Resource;

import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsUUID;
import com.danbro.anotation.ValidParam;
import com.danbro.edu.controller.param.VideoParam;
import com.danbro.edu.controller.vo.VideoVo;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.Result;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 课程视频(EduVideo)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */

@Api(tags = "小节接口")
@RestController
@RequestMapping("edu")
@Validated
public class EduVideoController {
    /**
     * 服务对象
     */
    @Resource
    private EduVideoService eduVideoService;

    @ValidParam
    @ApiOperation("添加小节信息")
    @PostMapping("video")
    public Result<VideoVo> insertVideo(@Validated(Insert.class) @RequestBody VideoParam videoParam, BindingResult result) {
        return Result.ofSuccess(new VideoVo().convertFrom(eduVideoService.insertOrUpdateVideo(videoParam.convertTo())));

    }

    @ValidParam
    @ApiOperation("修改小节信息")
    @PutMapping("video")
    public Result<VideoVo> updateVideo(@Validated(Update.class) @RequestBody VideoParam videoParam, BindingResult result) {
        return Result.ofSuccess(new VideoVo().convertFrom(eduVideoService.insertOrUpdateVideo(videoParam.convertTo())));
    }

    @ApiOperation("通过小节ID删除整个小节信息")
    @DeleteMapping("video/{id}")
    public Result deleteVideoInfo(@IsAssignID @PathVariable String id) {
        return Result.ofSuccess(eduVideoService.removeByVideoId(id));
    }

    @ApiOperation("通过小节ID删除小节里的视频（数据库和阿里云都要删除掉）")
    @DeleteMapping("video/aliyun/{id}")
    public Result deleteVideo(@IsUUID(message = "视频ID非法！") @PathVariable String id) {
        eduVideoService.removeAliyunVideo(id);
        return Result.ofSuccess();
    }
}