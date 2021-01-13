package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.dto.CourseTopDto;
import com.danbro.dto.OrderDto;
import com.danbro.dto.UserInfoDto;
import com.danbro.edu.dto.FrontCourseConditionPagingDto;
import com.danbro.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.dto.FrontPagingDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.rpcClient.OrderClient;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname FrontCourseController
 * @Description TODO 展示给前台用户课程的控制器
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@RestController

@RequestMapping("edu/front/course")
public class FrontCourseController {
    @Resource
    EduCourseService eduCourseService;


    @Autowired
    OrderClient orderClient;

    @ApiOperation("获取观看课程前 limit 名的课程信息")
    @GetMapping("top/{limit}")
    public Result<List<CourseTopDto>> getTopCourseList(@PathVariable String limit) {
        List<CourseTopDto> topCourseList = eduCourseService.getTopCourseList(limit);
        return Result.ofSuccess(topCourseList);
    }

    @ApiOperation("待查询条件的课程分页")
    @PostMapping("{current}/{limit}")
    public Result<FrontPagingDto<EduCourse>> pagingFindCourseByCondition(@PathVariable Long current,
                                                                         @PathVariable Long limit,
                                                                         @RequestBody(required = false) FrontCourseConditionPagingDto dto) {
        FrontPagingDto<EduCourse> resultDto = eduCourseService.pagingFindCourseByCondition(current, limit, dto);
        return Result.ofSuccess(resultDto);
    }

    @ApiOperation("通过课程ID查看课程的下面的所有章节和小节")
    @GetMapping("{courseId}")
    public Result<FrontCourseDetailInfoDto> getCourseAllInfo(@PathVariable String courseId) {
        FrontCourseDetailInfoDto courseDetailInfo = eduCourseService.getCourseDetailInfo(courseId);
        return Result.ofSuccess(courseDetailInfo);
    }

    @ApiOperation("通过课程ID和用户Id查询订单信息")
    @GetMapping("order/{courseId}")
    public Result<OrderDto> getCourseOrderInfo(@PathVariable String courseId,
                                               HttpServletRequest request) {
        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
        return orderClient.getOrderInfoByCourseId(userInfoDto.getId(), courseId);
    }
}
