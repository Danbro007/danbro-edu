package com.danbro.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.edu.controller.param.InsertCourseParam;
import com.danbro.edu.controller.dto.InPutEduCourseUpdatePublishStatusDto;
import com.danbro.edu.controller.dto.OutPutEduCoursePublishDto;
import com.danbro.edu.controller.dto.SearchCourseConditionDto;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 课程(EduCourse)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Validated
@RestController
@RequestMapping("edu/course")

public class EduCourseController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;


    @ApiOperation("添加课程基本信息")
    @PostMapping("info")
    public Result<CourseVo> insertCourseBasicInfo(@Validated @RequestBody InsertCourseParam courseParam) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseParam.convertTo())));
    }


    @ApiOperation("查看课程基本信息")
    @GetMapping("info/{courseId}")
    public Result<CourseVo> findCourseInfo(@PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseBasicInfo(courseId));
    }


    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result updateCourseInfo(@Valid @RequestBody InsertCourseParam insertCourseParam, BindingResult result) {
        Boolean flag = eduCourseService.updateCourseInfo(insertCourseParam);
        if (flag) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.UPDATE_COURSE_INFO_FAILURE);
    }

    @ApiOperation("查询要发布的课程信息")
    @GetMapping("publish/{courseId}")
    public Result<OutPutEduCoursePublishDto> updateCourseInfo(@PathVariable String courseId) {
        OutPutEduCoursePublishDto courseInfoForPublish = eduCourseService.getCourseInfoForPublish(courseId);
        return Result.ofSuccess(courseInfoForPublish);
    }

    @ApiOperation("修改课程发布状态")
    @PutMapping("publish/status")
    public Result updatePublishStatus(@Valid @RequestBody InPutEduCourseUpdatePublishStatusDto statusDto, BindingResult result) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(statusDto, eduCourse);
        boolean b = eduCourseService.updateById(eduCourse);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.UPDATE_COURSE_PUBLISH_STATUS_FAILURE);
    }

    @ApiOperation("分页查询课程列表")
    @PostMapping("list/{current}/{limit}")
    public Result<OutPutPagingDto<EduCourse>> pagingFindByCondition(@PathVariable Integer current,
                                                                    @PathVariable Integer limit,
                                                                    @RequestBody(required = false) SearchCourseConditionDto conditionDto) {
        Page<EduCourse> page = eduCourseService.pagingFindByCondition(current, limit, conditionDto);
        OutPutPagingDto<EduCourse> outPutPagingDto = new OutPutPagingDto<EduCourse>()
                .setTotal(page.getTotal())
                .setRows(page.getRecords());
        return Result.ofSuccess(outPutPagingDto);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("{id}")
    public Result deleteCourseById(@PathVariable String id) {
        Boolean b = eduCourseService.removeCourse(id);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_COURSE_FAILURE);
    }


}