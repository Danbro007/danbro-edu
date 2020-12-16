package com.danbro.controller;

import com.danbro.dto.EduTeacherInsertDto;
import com.danbro.dto.EduTeacherQueryDto;
import com.danbro.dto.EduTeacherUpdateDto;
import com.danbro.entity.EduTeacher;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Api("教师资源")
@CrossOrigin
@RestController
@RequestMapping("eduTeacher")
public class EduTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("查找所有的教师")
    @GetMapping("/")
    public Result findAll() {
        List<EduTeacher> eduTeachers = eduTeacherService.list();
        if (eduTeachers != null) {
            return Result.successOf(ResultCode.SUCCESS, "items", eduTeachers);
        }
        throw new MyCustomException(ResultCode.TEACHER_NOT_FOUND);
    }

    @ApiOperation("通过教师id删除指定的教师")
    @DeleteMapping("/{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            if (eduTeacherService.removeById(id)) {
                return Result.successOf(ResultCode.SUCCESS);
            }
        }
        throw new MyCustomException(ResultCode.DELETE_TEACHER_NOT_FOUND);
    }

    @ApiOperation("通过教师id查找指定的教师")
    @GetMapping("/{id}")
    public Result findOne(@ApiParam(name = "id", value = "教师id", required = true) @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            EduTeacher eduTeacher = eduTeacherService.getById(id);
            if (eduTeacher != null) {
                return Result.successOf(ResultCode.SUCCESS, "items", eduTeacher);
            }
        }
        throw new MyCustomException(ResultCode.TEACHER_NOT_FOUND);
    }


    @ApiOperation("带条件的分页查询教师")
    @PostMapping("/{current}/{limit}")
    public Result pagingFindByCondition(@ApiParam(name = "current", value = "当前页数", required = true) @PathVariable Integer current,
                                        @ApiParam(name = "limit", value = "当前页显示记录数", required = true) @PathVariable Integer limit,
                                        @RequestBody(required = false) EduTeacherQueryDto eduTeacherQueryDto) {
        return eduTeacherService.pagingFindTeacherByCondition(current, limit, eduTeacherQueryDto);
    }


    @ApiOperation("添加教师")
    @PostMapping("/")
    public Result insert(@Valid @RequestBody(required = true) EduTeacherInsertDto eduTeacherInsertDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return Result.failureOf(ResultCode.FAILURE, "errors", allErrors);
        }
        EduTeacher eduTeacher = eduTeacherInsertDto.convertTo();
        if (eduTeacherService.save(eduTeacher)) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        throw new MyCustomException(ResultCode.INSERT_TEACHER_FAILURE);
    }


    @ApiOperation("修改教师信息")
    @PutMapping("/")
    public Result update(@Valid @RequestBody EduTeacherUpdateDto eduTeacherUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            return Result.failureOf(ResultCode.FAILURE, "errors", allErrors);
        }
        EduTeacher eduTeacher = eduTeacherUpdateDto.convertTo();
        if (eduTeacherService.updateById(eduTeacher)) {
            return Result.successOf(ResultCode.SUCCESS);
        }
        throw new MyCustomException(ResultCode.UPDATE_TEACHER_FAILURE);
    }
}