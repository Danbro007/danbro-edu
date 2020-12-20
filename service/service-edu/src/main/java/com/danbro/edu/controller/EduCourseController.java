package com.danbro.edu.controller;

import com.danbro.edu.dto.EduCourseInputDto;
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
@RequestMapping("edu")
public class EduCourseController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;

    @ApiOperation("添加课程基本信息")
    @PostMapping("course")
    public Result insert(@RequestBody EduCourseInputDto eduCourseInputDto) {
        if (eduCourseInputDto != null) {
            eduCourseService.insert(eduCourseInputDto);
            return Result.failureOf(ResultCode.SUCCESS);
        }
        return Result.failureOf(ResultCode.FAILURE);
    }
}