package com.danbro.edu.controller;

import com.danbro.edu.dto.EduCourseDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result insert(@RequestBody EduCourseDto eduCourseDto) {
        if (eduCourseDto != null) {
            EduCourse course = eduCourseService.insert(eduCourseDto);
            return Result.successOf(ResultCode.SUCCESS, "courseId", course.getId());
        }
        return Result.failureOf(ResultCode.FAILURE);
    }


    @ApiOperation("查看课程基本详细")
    @GetMapping("info/{courseId}")
    public Result findCourseInfo(@PathVariable String courseId) {
        EduCourseDto courseInfo = eduCourseService.getCourseInfo(courseId);
        return Result.successOf(ResultCode.SUCCESS, "courseInfo", courseInfo);
    }


    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result updateCourseInfo(@RequestBody EduCourseDto eduCourseDto) {
        Boolean flag = eduCourseService.updateCourseInfo(eduCourseDto);
        if (flag) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.UPDATE_COURSE_INFO_FAILURE);
    }
}