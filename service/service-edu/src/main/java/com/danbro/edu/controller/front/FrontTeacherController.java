package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;

import com.danbro.edu.dto.EduTeacherInfoQueryDto;
import com.danbro.edu.dto.FrontPagingFindTeacherResultDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
@RequestMapping("edu/front")
public class FrontTeacherController {
    @Resource
    EduTeacherService eduTeacherService;

    @ApiOperation("获取等级排名为前 limit 名的讲师信息")
    @GetMapping("teacher/top/{limit}")
    public Result getTopTeacherList(@PathVariable String limit) {

        List<EduTeacher> topTeacherList = eduTeacherService.getTopTeacherList(limit);
        return Result.successOf("teacherList", topTeacherList);
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("teacher/{current}/{limit}")
    public Result pagingFind(@ApiParam(name = "current", value = "当前页数", example = "1") @PathVariable Integer current,
                             @ApiParam(name = "limit", value = "当前页显示记录数", example = "10") @PathVariable Integer limit) {
        FrontPagingFindTeacherResultDto frontPagingFindTeacherResultDto = eduTeacherService.pagingFindTeacher(current, limit);
        return Result.successOf("data", frontPagingFindTeacherResultDto);
    }

    @ApiOperation("获取讲师信息")
    @GetMapping("teacher/{id}")
    public Result getTeacherInfo(@PathVariable String id) {
        EduTeacherInfoQueryDto teacherInfo = eduTeacherService.getTeacherInfoById(id);
        return Result.successOf("teacherInfo", teacherInfo);
    }

}