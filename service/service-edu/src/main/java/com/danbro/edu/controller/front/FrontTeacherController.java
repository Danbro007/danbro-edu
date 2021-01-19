package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;

import com.danbro.dto.TeacherTopDto;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.dto.FrontTeacherInfoQueryDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname FrontTeacherController
 * @Description TODO 展示给前台用户讲师的控制器
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@RestController

@RequestMapping("edu/front")
public class FrontTeacherController {
    @Resource
    EduTeacherService eduTeacherService;

    @ApiOperation("获取等级排名为前 limit 名的讲师信息")
    @GetMapping("teacher/top/{limit}")
    public Result<List<TeacherTopDto>> getTopTeacherList(@PathVariable String limit) {

        List<TeacherTopDto> topTeacherList = eduTeacherService.getTopTeacherList(limit);
        return Result.ofSuccess(topTeacherList);
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("teacher/{current}/{limit}")
    public Result<FrontPagingDto<EduTeacher>> pagingFind(@ApiParam(name = "current", value = "当前页数", example = "1") @PathVariable Integer current,
                                                         @ApiParam(name = "limit", value = "当前页显示记录数", example = "10") @PathVariable Integer limit) {
        FrontPagingDto<EduTeacher> frontPagingDto = eduTeacherService.pagingFindTeacher(current, limit);
        return Result.ofSuccess(frontPagingDto);
    }

    @ApiOperation("获取讲师信息")
    @GetMapping("teacher/{id}")
    public Result<FrontTeacherInfoQueryDto> getTeacherInfo(@PathVariable String id) {
        FrontTeacherInfoQueryDto teacherInfo = eduTeacherService.getTeacherInfoById(id);
        return Result.ofSuccess(teacherInfo);
    }

}