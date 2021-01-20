package com.danbro.edu.controller;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.edu.controller.dto.SearchCourseConditionDto;
import com.danbro.edu.controller.param.InsertCourseParam;
import com.danbro.edu.controller.param.UpdateCourseParam;
import com.danbro.edu.controller.param.UpdateCourseStatusParam;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("添加课程基本信息")
    @PostMapping("info")
    public Result<CourseVo> insertCourseBasicInfo(@Validated @RequestBody InsertCourseParam courseParam) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseParam.convertTo())));
    }


    @ApiOperation("查看课程基本信息")
    @GetMapping("info/{courseId}")
    public Result<CourseVo> findCourseInfo(@NotBlank(message = "课程ID不能为空！") @Min(value = 1, message = "课程ID不能小于0！") @PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseBasicInfo(courseId));
    }

    @ApiOperation("修改课程基本信息")
    @PutMapping("info")
    public Result<CourseVo> updateCourseInfo(@Validated @RequestBody UpdateCourseParam courseParam) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseParam.convertTo())));
    }

    @ApiOperation("查询要发布的课程信息")
    @GetMapping("publish/{courseId}")
    public Result<CoursePublishVo> updateCourseInfo(@NotBlank(message = "课程ID不能为空！") @Min(value = 1, message = "课程ID不能小于0！") @PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseInfoForPublish(courseId));
    }

    @ApiOperation("修改课程发布状态")
    @PutMapping("publish")
    public Result<CourseVo> updatePublishStatus(@Validated @RequestBody UpdateCourseStatusParam courseStatusParam) {
        return Result.ofSuccess(new CourseVo().convertFrom(eduCourseService.insertOrUpdateCourse(courseStatusParam.convertTo())));
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