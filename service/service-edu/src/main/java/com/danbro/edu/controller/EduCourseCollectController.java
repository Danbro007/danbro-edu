package com.danbro.edu.controller;

import com.danbro.edu.service.EduCourseCollectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 课程收藏(EduCourseCollect)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("eduCourseCollect")
public class EduCourseCollectController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseCollectService eduCourseCollectService;



}