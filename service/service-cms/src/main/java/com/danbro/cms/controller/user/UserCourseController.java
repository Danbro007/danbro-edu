package com.danbro.cms.controller.user;

import java.util.List;

import com.danbro.anotation.IsPositiveNum;
import com.danbro.cms.rpcClient.CourseClient;
import com.danbro.dto.CourseTopDto;
import com.danbro.enums.Result;
import com.danbro.vo.CourseVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserCrmBannerController
 * @Description TODO 前台用户查询课程控制器
 * @Date 2021/1/4 15:43
 * @Author Danrbo
 */

@RequestMapping("cms")
@RestController
public class UserCourseController {

    @Autowired
    CourseClient courseClient;

    @ApiOperation("获取观看课程前 num 名的课程信息")
    @GetMapping("course/top/{limit}")
    public Result<List<CourseVo>> getTopCourseList(@IsPositiveNum @PathVariable String limit) {
        return courseClient.getTopCourseList(limit);
    }
}
