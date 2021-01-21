package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.anotation.ValidParam;
import com.danbro.edu.controller.param.QueryTeacherParam;
import com.danbro.edu.controller.param.TeacherParam;
import com.danbro.edu.controller.vo.TeacherVo;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.Result;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("通过教师ID删除指定的教师")
    @DeleteMapping("teacher/{id}")
    public Result deleteTeacherById(@IsAssignID @ApiParam(name = "id", value = "讲师ID", required = true)
                                    @PathVariable String id) {
        return Result.ofSuccess(eduTeacherService.removeById(id));
    }

    @ApiOperation("通过讲师ID查找指定的讲师")
    @GetMapping("teacher/{id}")
    public Result<TeacherVo> findOne(@IsAssignID @ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id) {
        return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.getById(id)));
    }


    @ApiOperation("带条件的分页查询教师")
    @PostMapping("teacher/list/{current}/{limit}")
    public Result<OutPutPagingDto<TeacherVo>> pagingFindByCondition(@IsPositiveNum(message = "查询的页数不能为空并且必须大于0!") @ApiParam(name = "current", value = "当前页数") @PathVariable Integer current,
                                                                    @IsPositiveNum(message = "每页的显示数不能为空并且必须大于0!") @ApiParam(name = "limit", value = "当前页显示记录数") @PathVariable Integer limit,
                                                                    @RequestBody(required = false) @ApiParam(name = "queryTeacherParam", value = "查询讲师的参数") QueryTeacherParam queryTeacherParam) {
        return Result.ofSuccess(eduTeacherService.pagingFindTeacherByCondition(current, limit, queryTeacherParam));
    }

    @ValidParam
    @ApiOperation("添加教师信息")
    @PostMapping("teacher")
    public Result<TeacherVo> insertTeacher(@Validated(Insert.class) @RequestBody TeacherParam teacherParam, BindingResult bindingResult) {

        return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.insertOrUpdateTeacher(teacherParam.convertTo())));
    }

    @ValidParam
    @ApiOperation("修改教师信息")
    @PutMapping("teacher")
    public Result<TeacherVo> updateTeacher(@Validated(Update.class) @RequestBody TeacherParam teacherParam, BindingResult bindingResult) {
        return Result.ofSuccess(new TeacherVo().convertFrom(eduTeacherService.insertOrUpdateTeacher(teacherParam.convertTo())));
    }
}