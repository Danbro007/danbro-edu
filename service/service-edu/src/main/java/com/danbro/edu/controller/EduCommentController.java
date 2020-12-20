package com.danbro.edu.controller;

import com.danbro.edu.entity.EduComment;
import com.danbro.edu.service.EduCommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论(EduComment)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("eduComment")
public class EduCommentController {
    /**
     * 服务对象
     */
    @Resource
    private EduCommentService eduCommentService;

}