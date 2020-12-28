package com.danbro.edu.controller;

import com.danbro.edu.dto.EduCourseInputDto;
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
    public Result insert(@RequestBody EduCourseInputDto eduCourseInputDto) {
        if (eduCourseInputDto != null) {
            EduCourse course = eduCourseService.insert(eduCourseInputDto);
            return Result.successOf(ResultCode.SUCCESS, "courseId", course.getId());
        }
        return Result.failureOf(ResultCode.FAILURE);
    }
}