package com.danbro.edu.controller;

import com.danbro.edu.dto.EduChapterOutPutDto;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.service.EduChapterService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程(EduChapter)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("edu")
public class EduChapterController {
    /**
     * 服务对象
     */
    @Resource
    private EduChapterService eduChapterService;

    /**
     * 根据 courseId 找到对应的 chapter 和 video
     *
     * @param courseId 课程Id
     * @return 章节列表
     */
    @GetMapping("chapter/{courseId}")
    public Result getChapter(@PathVariable String courseId) {
        List<EduChapterOutPutDto> chapterOutPut = eduChapterService.findAllByCourseId(courseId);
        return Result.successOf(ResultCode.SUCCESS, "chapters", chapterOutPut);
    }

}