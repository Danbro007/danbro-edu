package com.danbro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.entity.EduTeacher;
import com.danbro.service.EduTeacherService;
import com.danbro.vo.TeacherQueryVo;
import enums.Result;
import enums.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Api("教师资源")
@RestController
@RequestMapping("eduTeacher")
public class EduTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    /**
     * 查询多条教师数据
     *
     * @return 多条数据
     */
    @ApiOperation("查找所有的教师")
    @GetMapping("/")
    public Result findAll() {
        List<EduTeacher> eduTeachers = eduTeacherService.list();
        return eduTeachers != null ? Result.successOf(ResultCode.Success, "items", eduTeachers) : Result.failureOf(ResultCode.Failure);
    }

    @ApiOperation("通过教师id删除指定的教师")
    @DeleteMapping("/{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            boolean b = eduTeacherService.removeById(id);
            return eduTeacherService.removeById(id) ? Result.successOf(ResultCode.Success) : Result.failureOf(ResultCode.Failure);
        }
        return Result.failureOf(ResultCode.Failure);
    }

    @ApiOperation("通过教师id查找指定的教师")
    @GetMapping("/{id}")
    public Result findOne(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            EduTeacher eduTeacher = eduTeacherService.getById(id);
            return eduTeacher != null ? Result.successOf(ResultCode.Success, "items", eduTeacher) : Result.failureOf(ResultCode.Failure);
        }
        return Result.failureOf(ResultCode.Failure);
    }


    @ApiOperation("带条件的分页查询教师")
    @PostMapping("/{current}/{limit}")
    public Result pagingFindByCondition(@ApiParam(name = "current", value = "当前页数", required = true) @PathVariable Integer current,
                                        @ApiParam(name = "limit", value = "当前页显示记录数", required = true) @PathVariable Integer limit,
                                        @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        return eduTeacherService.pagingFindTeacherByCondition(current, limit, teacherQueryVo);
    }


    @ApiOperation("添加教师")
    @PostMapping("/")
    public Result insert(@Valid @RequestBody(required = true) EduTeacher eduTeacher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return Result.failureOf(ResultCode.Failure,"errors",allErrors);
        }
        return eduTeacherService.save(eduTeacher) ? Result.successOf(ResultCode.Success) : Result.successOf(ResultCode.Failure);
    }

}