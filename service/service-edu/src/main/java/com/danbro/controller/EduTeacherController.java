package com.danbro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.entity.EduTeacher;
import com.danbro.service.EduTeacherService;
import com.danbro.vo.TeacherQueryVo;
import enums.Result;
import enums.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */

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
    @GetMapping("/findAll")
    public Result findAll() {
        List<EduTeacher> eduTeachers = eduTeacherService.list();
        return eduTeachers != null ? Result.successOf(ResultCode.Success, "items", eduTeachers) : Result.failureOf(ResultCode.Failure);
    }

    @ApiOperation("通过教师id删除指定的教师")
    @DeleteMapping("{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            boolean b = eduTeacherService.removeById(id);
            return eduTeacherService.removeById(id) ? Result.successOf(ResultCode.Success) : Result.failureOf(ResultCode.Failure);
        }
        return Result.failureOf(ResultCode.Failure);
    }

    @ApiOperation("通过教师id查找指定的教师")
    @GetMapping("/findOne/{id}")
    public Result findOne(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            EduTeacher eduTeacher = eduTeacherService.getById(id);
            return eduTeacher != null ? Result.successOf(ResultCode.Success, "items", eduTeacher) : Result.failureOf(ResultCode.Failure);
        }
        return Result.failureOf(ResultCode.Failure);
    }


    @ApiOperation("分页查询教师")
    @GetMapping("/findAll/{current}/{limit}")
    public Result pagingFind(@ApiParam(name = "current", value = "当前页数", required = true) @PathVariable Integer current,
                             @ApiParam(name = "limit", value = "当前页显示记录数", required = true) @PathVariable Integer limit) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        eduTeacherService.page(eduTeacherPage);
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("total", eduTeacherPage.getTotal());
        map.put("rows", eduTeacherPage.getRecords());
        return Result.successOf(ResultCode.Success, map);
    }

    @ApiOperation("带条件的分页查询教师")
    @PostMapping("/findAll/{current}/{limit}")
    public Result pagingFindByCondition(@ApiParam(name = "current", value = "当前页数", required = true) @PathVariable Integer current,
                                        @ApiParam(name = "limit", value = "当前页显示记录数", required = true) @PathVariable Integer limit,
                                        @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        return eduTeacherService.pagingFindTeacherByCondition(current, limit, teacherQueryVo);
    }

}