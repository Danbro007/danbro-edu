package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;

import com.danbro.edu.dto.FrontCourseConditionPagingDto;
import com.danbro.edu.dto.FrontCourseConditionPagingResultDto;
import com.danbro.edu.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname FrontCourseController
 * @Description TODO 展示给前台用户课程的控制器
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@RestController
@CrossOrigin
@RequestMapping("edu/front/course")
public class FrontCourseController {
    @Resource
    EduCourseService eduCourseService;

    @ApiOperation("获取观看课程前 limit 名的课程信息")
    @GetMapping("top/{limit}")
    public Result getTopCourseList(@PathVariable String limit) {
        List<EduCourse> topCourseList = eduCourseService.getTopCourseList(limit);
        return Result.successOf("courseList", topCourseList);
    }

    @ApiOperation("待查询条件的课程分页")
    @PostMapping("{current}/{limit}")
    public Result pagingFindCourseByCondition(@PathVariable Long current,
                                              @PathVariable Long limit,
                                              @RequestBody(required = false) FrontCourseConditionPagingDto dto) {
        FrontCourseConditionPagingResultDto resultDto = eduCourseService.pagingFindCourseByCondition(current, limit, dto);
        return Result.successOf("courseList", resultDto);
    }

    @ApiOperation("通过课程ID查看课程的下面的所有章节和小节")
    @GetMapping("{courseId}")
    public Result getCourseAllInfo(@PathVariable String courseId) {
        FrontCourseDetailInfoDto courseDetailInfo = eduCourseService.getCourseDetailInfo(courseId);
        return Result.successOf("courseInfo", courseDetailInfo);
    }
}
