package com.danbro.cms.controller.user;

import com.danbro.cms.rpcClient.TeacherClient;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserEduTeacherController
 * @Description TODO 前台用户查询讲师的控制器
 * @Date 2021/1/4 15:46
 * @Author Danrbo
 */
@CrossOrigin
@RequestMapping("cms")
@RestController
public class UserTeacherController {

    @Autowired
    TeacherClient teacherClient;

    @ApiOperation("获取等级排名为前 limit 名的讲师信息")
    @GetMapping("teacher/top/{limit}")
    public Result getTopTeacherList(@PathVariable String limit) {
        return teacherClient.getTopTeacherList(limit);
    }
}