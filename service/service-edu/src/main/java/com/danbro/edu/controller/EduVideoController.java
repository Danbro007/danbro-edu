package com.danbro.edu.controller;

import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.service.EduVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 课程视频(EduVideo)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("eduVideo")
public class EduVideoController {
    /**
     * 服务对象
     */
    @Resource
    private EduVideoService eduVideoService;


}