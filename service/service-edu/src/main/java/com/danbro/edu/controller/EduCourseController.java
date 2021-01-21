package com.danbro.edu.controller;

import javax.annotation.Resource;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.ValidParam;
import com.danbro.edu.controller.param.QueryCourseParam;
import com.danbro.edu.controller.param.CourseParam;
import com.danbro.edu.controller.param.CourseStatusParam;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 课程(EduCourse)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Validated
@RestController
@Api(tags = "课程接口")
@RequestMapping("edu/course")
public class EduCourseController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;

    @ValidParam
    @ApiOperation("添加课程基本信息")
    @PostMapping("info")
    public Result<CourseVo> insertCourseBasicInfo(@Validated(Insert.class) @RequestBody CourseParam courseParam, BindingResult result) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseParam.convertTo())));
    }


    @ApiOperation("查看课程基本信息")
    @GetMapping("info/{courseId}")
    public Result<CourseVo> findCourseInfo(@IsAssignID @PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseBasicInfo(courseId));
    }

    @ValidParam
    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result<CourseVo> updateCourseInfo(@Validated(Update.class) @RequestBody CourseParam courseParam, BindingResult result) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseParam.convertTo())));
    }

    @ApiOperation("查询要发布的课程信息")
    @GetMapping("publish/{courseId}")
    public Result<CoursePublishVo> updateCourseInfo(@IsAssignID @PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseInfoForPublish(courseId));
    }

    @ValidParam
    @ApiOperation("修改课程发布状态")
    @PutMapping("publish")
    public Result<CourseVo> updatePublishStatus(@Validated @RequestBody CourseStatusParam courseStatusParam, BindingResult result) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseStatusParam.convertTo())));
    }

    @ApiOperation("分页查询课程列表")
    @PostMapping("list/{current}/{limit}")
    public Result<OutPutPagingDto<EduCourse>> pagingFindByCondition(@PathVariable Integer current,
                                                                    @PathVariable Integer limit,
                                                                    @RequestBody(required = false) QueryCourseParam courseParam) {
        return Result.ofSuccess(eduCourseService.pagingFindByCondition(current, limit, courseParam));
    }

    @ApiOperation("删除课程")
    @DeleteMapping("{id}")
    public Result deleteCourseById(@IsAssignID @PathVariable String id) {
        return Result.ofSuccess(eduCourseService.removeCourse(id));
    }


}