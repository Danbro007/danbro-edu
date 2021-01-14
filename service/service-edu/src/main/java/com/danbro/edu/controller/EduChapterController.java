package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.edu.dto.InPutEduChapterInsertDto;
import com.danbro.edu.dto.OutPutEduChapterDto;
import com.danbro.edu.dto.InPutEduChapterUpdateDto;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.service.EduChapterService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("查找课程里所有的章节")
    @GetMapping("chapter/{courseId}")
    public Result<List<OutPutEduChapterDto>> getChapter(@PathVariable String courseId) {
        List<OutPutEduChapterDto> chapterOutPut = eduChapterService.findAllByCourseId(courseId);
        return Result.ofSuccess(chapterOutPut);
    }

    /**
     * 添加章节
     *
     * @param inPutEduChapterInsertDto 要添加的章节参数
     * @return 添加结果
     */
    @ApiOperation("添加章节")
    @PostMapping("chapter")
    public Result insertChapter(@RequestBody InPutEduChapterInsertDto inPutEduChapterInsertDto) {
        Boolean flag = eduChapterService.insert(inPutEduChapterInsertDto);
        if (flag) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.FAILURE);
    }

    /**
     * 通过 chapterId 删除章节及对应的小节（视频信息）
     *
     * @param chapterId 章节ID
     * @return 删除结果
     */
    @ApiOperation("删除章节")
    @DeleteMapping("chapter/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        boolean b = eduChapterService.removeChapterAndVideo(chapterId);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_CHAPTER_FAILURE);
    }

    /**
     * 通过 eduChapter 更新章节
     *
     * @param inPutEduChapterUpdateDto 要更新chapter参数
     * @return 更新结果
     */
    @ApiOperation("修改章节")
    @PutMapping("chapter")
    public Result updateChapter(@RequestBody InPutEduChapterUpdateDto inPutEduChapterUpdateDto) {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(inPutEduChapterUpdateDto, eduChapter);
        boolean b = eduChapterService.updateById(eduChapter);
        if (b) return Result.ofSuccess();
        return Result.ofFail(ResultCode.UPDATE_CHAPTER_FAILURE);
    }

}