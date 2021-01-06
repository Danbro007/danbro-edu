package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
