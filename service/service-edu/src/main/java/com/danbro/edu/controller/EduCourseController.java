package com.danbro.edu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.edu.dto.EduCourseDto;
import com.danbro.edu.dto.EduCoursePublishDto;
import com.danbro.edu.dto.EduCoursePublishStatusDto;
import com.danbro.edu.dto.SearchCourseConditionDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 课程(EduCourse)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("edu/course")
@CrossOrigin
public class EduCourseController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation("添加课程信息")
    @PostMapping("info")
    public Result insert(@Valid @RequestBody EduCourseDto eduCourseDto, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return Result.failureOf(ResultCode.FAILURE).setDataChain("errors", errors);
        }
        EduCourse course = eduCourseService.insert(eduCourseDto);
        return Result.successOf(ResultCode.SUCCESS, "courseId", course.getId());
    }


    @ApiOperation("查看课程基本详细")
    @GetMapping("info/{courseId}")
    public Result findCourseInfo(@PathVariable String courseId) {
        EduCourseDto courseInfo = eduCourseService.getCourseInfo(courseId);
        return Result.successOf(ResultCode.SUCCESS, "courseInfo", courseInfo);
    }


    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result updateCourseInfo(@Valid @RequestBody EduCourseDto eduCourseDto, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return Result.failureOf(ResultCode.FAILURE).setDataChain("errors", errors);
        }
        Boolean flag = eduCourseService.updateCourseInfo(eduCourseDto);
        if (flag) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.UPDATE_COURSE_INFO_FAILURE);
    }

    @ApiOperation("查询要发布的课程信息")
    @GetMapping("publish/{courseId}")
    public Result updateCourseInfo(@PathVariable String courseId) {
        EduCoursePublishDto courseInfoForPublish = eduCourseService.getCourseInfoForPublish(courseId);
        return Result.successOf(ResultCode.SUCCESS, "coursePublish", courseInfoForPublish);
    }

    @ApiOperation("修改课程发布状态")
    @PutMapping("publish/status")
    public Result updatePublishStatus(@Valid @RequestBody EduCoursePublishStatusDto statusDto, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return Result.failureOf(ResultCode.FAILURE).setDataChain("errors", errors);
        }
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(statusDto, eduCourse);
        boolean b = eduCourseService.updateById(eduCourse);
        if (b) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.UPDATE_COURSE_PUBLISH_STATUS_FAILURE);
    }

    @ApiOperation("分页查询课程列表")
    @PostMapping("list/{current}/{limit}")
    public Result pagingFindByCondition(@PathVariable Integer current,
                                        @PathVariable Integer limit,
                                        @RequestBody(required = false) SearchCourseConditionDto conditionDto) {
        Page<EduCourse> page = eduCourseService.pagingFindByCondition(current, limit, conditionDto);
        return Result.successOf(ResultCode.SUCCESS, "total", page.getTotal()).setDataChain("rows", page.getRecords());
    }

    @ApiOperation("删除课程")
    @DeleteMapping("{id}")
    public Result deleteCourseById(@PathVariable String id) {
        Boolean b = eduCourseService.removeCourse(id);
        if (b) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.DELETE_COURSE_FAILURE);
    }

}