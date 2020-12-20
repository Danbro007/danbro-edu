package com.danbro.edu.controller;

import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.service.EduChapterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 课程(EduChapter)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("eduChapter")
public class EduChapterController {
    /**
     * 服务对象
     */
    @Resource
    private EduChapterService eduChapterService;



}