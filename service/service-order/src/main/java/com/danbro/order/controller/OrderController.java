package com.danbro.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.anotation.IsAssignID;
import com.danbro.dto.UserInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.order.rpcClient.CourseClient;
import com.danbro.order.rpcClient.UserClient;
import com.danbro.order.service.TOrderService;
import com.danbro.utils.JwtUtils;
import com.danbro.vo.CourseVo;
import com.danbro.vo.MemberVo;
import com.danbro.vo.OrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单(TOrder)表控制层
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
@Validated
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderService tOrderService;

    @Autowired
    UserClient userClient;

    @Autowired
    CourseClient courseClient;

    @ApiOperation("通过课程ID和用户ID创建订单")
    @PostMapping("/{courseId}")
    public Result<OrderVo> createOrder(@IsAssignID(message = "课程ID非法！") @PathVariable String courseId, HttpServletRequest request) {
        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfoDto == null) {
            throw new EduException(ResultCode.USER_NO_LOGIN);
        }
        Result<MemberVo> memberResult = userClient.getMemberInfoByMemberId(userInfoDto.getId());
        Result<CourseVo> courseResult = courseClient.getCourseBasicInfo(courseId);
        return Result.ofSuccess(new OrderVo().convertFrom(tOrderService.insertOrder(memberResult.getData(), courseResult.getData())));
    }

    @ApiOperation("通过订单编号查询订单信息")
    @GetMapping("info/{orderNo}")
    public Result<OrderVo> getOrderByOrderNo(@PathVariable String orderNo) {
        return Result.ofSuccess(new OrderVo().convertFrom(tOrderService.getOrderByOrderNo(orderNo)));
    }

    @ApiOperation("通过用户ID和订单课程ID查询订单信息")
    @GetMapping("info/{userId}/{courseId}")
    public Result<OrderVo> getOrderInfoByCourseId(@IsAssignID @PathVariable String userId,
                                                  @IsAssignID @PathVariable String courseId) {
        return Result.ofSuccess(new OrderVo().convertFrom(tOrderService.getOrderByUserIdAndCourseId(userId, courseId)));
    }
}