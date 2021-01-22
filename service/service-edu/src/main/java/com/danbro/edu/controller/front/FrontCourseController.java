package com.danbro.edu.controller.front;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.vo.FrontCourseDetailInfoVo;
import com.danbro.vo.OrderVo;
import com.danbro.dto.UserInfoDto;
import com.danbro.edu.controller.param.FrontQueryCourseParam;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.vo.CourseVo;
import com.danbro.edu.rpcClient.OrderClient;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.Result;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname FrontCourseController
 * @Description TODO 展示给前台用户课程的控制器
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@RestController
@Validated
@RequestMapping("edu/front/course")
public class FrontCourseController {
    @Resource
    EduCourseService eduCourseService;


    @Autowired
    OrderClient orderClient;

    @ApiOperation("获取观看课程前 limit 名的课程信息")
    @GetMapping("top/{limit}")
    public Result<List<CourseVo>> getTopCourseList(@IsPositiveNum(message = "参数非法") @PathVariable String limit) {
        return Result.ofSuccess(eduCourseService.getTopCourseList(limit));
    }

    @ApiOperation("带查询条件的课程分页")
    @PostMapping("{current}/{limit}")
    public Result<FrontPagingDto<CourseVo>> pagingFindCourseByCondition(@IsPositiveNum(message = "当前页非法！") @PathVariable Long current,
                                                                        @IsPositiveNum(message = "每页显示数非法！") @PathVariable Long limit,
                                                                        @RequestBody(required = false) FrontQueryCourseParam courseParam) {
        return Result.ofSuccess(eduCourseService.pagingFindCourseByCondition(current, limit, courseParam));
    }

    @ApiOperation("通过课程ID查看课程的下面的所有章节和小节")
    @GetMapping("{courseId}")
    public Result<FrontCourseDetailInfoVo> getCourseDetailInfo(@IsAssignID(message = "课程ID非法！") @PathVariable String courseId) {
        return Result.ofSuccess(eduCourseService.getCourseDetailInfo(courseId));
    }

    @ApiOperation("通过课程ID和用户Id查询订单信息")
    @GetMapping("order/{courseId}")
    public Result<OrderVo> getCourseOrderInfo(@IsAssignID(message = "课程ID非法！") @PathVariable String courseId,
                                              HttpServletRequest request) {
        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
        assert userInfoDto != null;
        return orderClient.getOrderInfoByCourseId(userInfoDto.getId(), courseId);
    }
}
