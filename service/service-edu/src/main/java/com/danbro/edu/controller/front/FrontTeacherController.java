package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.vo.TeacherVo;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
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
    public Result<List<TeacherVo>> getTopTeacherList(@IsPositiveNum(message = "排名参数非法！") @PathVariable String limit) {

        return Result.ofSuccess(eduTeacherService.getTopTeacherList(limit));
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("teacher/{current}/{limit}")
    public Result<FrontPagingDto<TeacherVo>> pagingFind(@IsPositiveNum(message = "当前页数非法！") @PathVariable Integer current,
                                                        @IsPositiveNum(message = "每页显示数非法！")  @PathVariable Integer limit) {
        return Result.ofSuccess(eduTeacherService.pagingFindTeacher(current, limit));
    }

    @ApiOperation("获取讲师信息")
    @GetMapping("teacher/{id}")
    public Result<TeacherVo> getTeacherInfo(@IsAssignID(message = "讲师ID非法！") @PathVariable String id) {
        return Result.ofSuccess(eduTeacherService.getTeacherInfoById(id));
    }

}