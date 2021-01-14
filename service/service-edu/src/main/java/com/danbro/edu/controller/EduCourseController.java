package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.edu.dto.InPutEduCourseInsertDto;
import com.danbro.edu.dto.InPutEduCourseUpdatePublishStatusDto;
import com.danbro.edu.dto.OutPutEduCoursePublishDto;
import com.danbro.edu.dto.SearchCourseConditionDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 * 课程(EduCourse)表控制层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@RestController
@RequestMapping("edu/course")

public class EduCourseController {
    /**
     * 服务对象
     */
    @Resource
    private EduCourseService eduCourseService;


    @ApiOperation("添加课程信息")
    @PostMapping("info")
    public Result<String> insert(@Valid @RequestBody InPutEduCourseInsertDto inPutEduCourseInsertDto, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            throw new MyCustomException(ResultCode.FAILURE, errors);
        }
        EduCourse course = eduCourseService.insert(inPutEduCourseInsertDto);
        if (course != null) {
            return Result.ofSuccess(course.getId());
        }
        return Result.ofFail(ResultCode.INSERT_COURSE_FAILURE);
    }


    @ApiOperation("查看课程基本详细")
    @GetMapping("info/{courseId}")
    public Result<EduCourseBasicInfoDto> findCourseInfo(@PathVariable String courseId) {
        EduCourseBasicInfoDto courseInfo = eduCourseService.getCourseBasicInfo(courseId);
        if (courseInfo == null) {
            throw new MyCustomException(ResultCode.COURSE_IS_NOT_EXIST);
        }
        return Result.ofSuccess(courseInfo);
    }


    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result updateCourseInfo(@Valid @RequestBody InPutEduCourseInsertDto inPutEduCourseInsertDto, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            throw new MyCustomException(ResultCode.FAILURE, errors);
        }
        Boolean flag = eduCourseService.updateCourseInfo(inPutEduCourseInsertDto);
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
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            throw new MyCustomException(ResultCode.FAILURE, errors);
        }
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