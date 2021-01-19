package com.danbro.edu.controller;

import com.danbro.edu.controller.param.InsertTeacherParam;
import com.danbro.edu.controller.param.QueryTeacherParam;
import com.danbro.edu.controller.param.UpdateTeacherParam;
import com.danbro.edu.controller.vo.TeacherVo;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Api(tags = "讲师接口")
@RestController
@Validated
@RequestMapping("edu")
public class EduTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("查找所有的教师")
    @GetMapping("teachers")
    public Result<List<EduTeacher>> findAll() {
        return Result.ofSuccess(eduTeacherService.list());
    }

    @ApiOperation("通过教师id删除指定的教师")
    @DeleteMapping("teacher/{id}")
    public Result deleteTeacherById(@NotBlank(message = "讲师ID不为能空！") @Min(value = 1, message = "讲师ID不能小于0！") @ApiParam(name = "id", value = "讲师ID", required = true, example = "1189389726308478977")
                                    @PathVariable String id) {
        if (eduTeacherService.removeById(id)) {
            return Result.ofSuccess();
        }
        throw new MyCustomException(ResultCode.DELETE_TEACHER_NOT_FOUND);
    }

    @ApiOperation("通过讲师ID查找指定的讲师")
    @GetMapping("teacher/{id}")
    public Result<TeacherVo> findOne(@NotBlank(message = "讲师ID不为能空！") @Min(value = 1, message = "讲师ID不能小于0！") @ApiParam(name = "id", value = "讲师ID", required = true, example = "1189389726308478977")
                                     @PathVariable String id) {
        return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.getById(id)));
    }


    @ApiOperation("带条件的分页查询教师")
    @PostMapping("teacher/list/{current}/{limit}")
    public Result<OutPutPagingDto<TeacherVo>> pagingFindByCondition(@NotNull(message = "查询的页数不能为空!") @Min(value = 1, message = "查询的页数必须大于0！") @ApiParam(name = "current", value = "当前页数") @PathVariable Integer current,
                                                                    @NotNull(message = "每页的显示数不能为空!") @Min(value = 1, message = "每页的显示数必须大于0！") @ApiParam(name = "limit", value = "当前页显示记录数") @PathVariable Integer limit,
                                                                    @RequestBody(required = false) @ApiParam(name = "queryTeacherParam", value = "查询讲师的参数") QueryTeacherParam queryTeacherParam) {
        return Result.ofSuccess(eduTeacherService.pagingFindTeacherByCondition(current, limit, queryTeacherParam));
    }

    @ApiOperation("添加教师")
    @PostMapping("teacher")
    public Result<TeacherVo> insert(@Validated @RequestBody InsertTeacherParam insertTeacherParam) {
        try {
            return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.insertOrUpdateTeacher(insertTeacherParam.convertTo())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyCustomException(ResultCode.INSERT_TEACHER_FAILURE);
        }
    }


    @ApiOperation("修改教师信息")
    @PutMapping("teacher")
    public Result<TeacherVo> update(@Validated @RequestBody UpdateTeacherParam updateTeacherParam) {
        try {
            return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.insertOrUpdateTeacher(updateTeacherParam.convertTo())));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyCustomException(ResultCode.INSERT_TEACHER_FAILURE);
        }
    }
}