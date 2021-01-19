package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.danbro.edu.controller.param.InsertChapterParam;
import com.danbro.edu.controller.param.UpdateChapterParam;
import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.service.EduChapterService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 课程(EduChapter)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Api(tags = "课程章节接口")
@RestController
@Validated
@RequestMapping("edu/chapter")
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
    @ApiOperation("查找课程里所有的章节")
    @GetMapping("{courseId}")
    public Result<List<ChapterVo>> getChapter(@NotBlank(message = "课程ID不能为空！")
                                              @Min(value = 1, message = "课程ID不能小于0！")
                                              @PathVariable String courseId) {
        return Result.ofSuccess(eduChapterService.findAllByCourseId(courseId));
    }

    /**
     * 添加章节
     *
     * @param chapterParam 要添加的章节参数
     * @return 添加结果
     */
    @ApiOperation("添加章节")
    @PostMapping("")
    public Result<ChapterVo> insertChapter(@Validated @RequestBody InsertChapterParam chapterParam) {
        return Result.ofSuccess(new ChapterVo().convertFrom(eduChapterService.insertOrUpdateChapter(chapterParam.convertTo())));
    }

    /**
     * 通过 chapterId 删除章节及对应的小节（视频信息）
     *
     * @param chapterId 章节ID
     * @return 删除结果
     */
    @ApiOperation("删除章节")
    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@NotBlank(message = "章节ID不能为空！") @Min(value = 1, message = "章节ID不能小于0！") @PathVariable String chapterId) {
        eduChapterService.removeChapterAndVideoByChapterId(chapterId);
        return Result.ofSuccess();
    }

    /**
     * 通过 eduChapter 更新章节
     *
     * @param chapterParam 要更新chapter参数
     * @return 更新结果
     */
    @ApiOperation("修改章节")
    @PutMapping("chapter")
    public Result<ChapterVo> updateChapter(@RequestBody UpdateChapterParam chapterParam) {
        return Result.ofSuccess(new ChapterVo().convertFrom(eduChapterService.insertOrUpdateChapter(chapterParam.convertTo())));
    }
}