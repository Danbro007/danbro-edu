package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.danbro.edu.dto.InPutEduTeacherInsertDto;
import com.danbro.edu.dto.InPutEduTeacherUpdateDto;
import com.danbro.edu.dto.FrontTeacherQueryDto;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Api("教师资源")

@RestController
@RequestMapping("edu")
public class EduTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("查找所有的教师")
    @GetMapping("teacher")
    public Result<List<EduTeacher>> findAll() {
        List<EduTeacher> teacherList = eduTeacherService.list();
        return Result.ofSuccess(teacherList);
    }

    @ApiOperation("通过教师id删除指定的教师")
    @DeleteMapping("teacher/{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "教师id", required = true, example = "1189389726308478977") @PathVariable String id) {
        if (id != null && !StringUtils.isEmpty(id)) {
            if (eduTeacherService.removeById(id)) {
                return Result.ofSuccess();
            }
        }
        throw new MyCustomException(ResultCode.DELETE_TEACHER_NOT_FOUND);
    }

    @ApiOperation("通过教师id查找指定的教师")
    @GetMapping("teacher/{id}")
    public Result<EduTeacher> findOne(@ApiParam(name = "id", value = "教师id", required = true, example = "1189389726308478977") @PathVariable String id) {
        EduTeacher teacherInfo = eduTeacherService.getById(id);
        return Result.ofSuccess(teacherInfo);
    }


    @ApiOperation("带条件的分页查询教师")
    @PostMapping("teacher/{current}/{limit}")
    public Result<OutPutPagingDto<EduTeacher>> pagingFindByCondition(@ApiParam(name = "current", value = "当前页数", example = "1") @PathVariable Integer current,
                                                                     @ApiParam(name = "limit", value = "当前页显示记录数", example = "10") @PathVariable Integer limit,
                                                                     @RequestBody(required = false) FrontTeacherQueryDto frontTeacherQueryDto) {
        Page<EduTeacher> page = eduTeacherService.pagingFindTeacherByCondition(current, limit, frontTeacherQueryDto);
        OutPutPagingDto<EduTeacher> outPutPagingDto = new OutPutPagingDto<EduTeacher>().setRows(page.getRecords()).setTotal(page.getTotal());
        return Result.ofSuccess(outPutPagingDto);


    }

    @ApiOperation("添加教师")
    @PostMapping("teacher")
    public Result insert(@Valid @RequestBody(required = true) InPutEduTeacherInsertDto inPutEduTeacherInsertDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new MyCustomException(ResultCode.FAILURE, errors);
        }
        EduTeacher eduTeacher = inPutEduTeacherInsertDto.convertTo();
        try {
            if (eduTeacherService.save(eduTeacher)) {
                return Result.ofSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyCustomException(ResultCode.INSERT_TEACHER_FAILURE);
        }
        throw new MyCustomException(ResultCode.INSERT_TEACHER_FAILURE);
    }


    @ApiOperation("修改教师信息")
    @PutMapping("teacher")
    public Result update(@Valid @RequestBody InPutEduTeacherUpdateDto inPutEduTeacherUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            throw new MyCustomException(ResultCode.FAILURE, errors);
        }
        EduTeacher eduTeacher = inPutEduTeacherUpdateDto.convertTo();
        if (eduTeacherService.updateById(eduTeacher)) {
            return Result.ofSuccess();
        }
        throw new MyCustomException(ResultCode.UPDATE_TEACHER_FAILURE);
    }


}