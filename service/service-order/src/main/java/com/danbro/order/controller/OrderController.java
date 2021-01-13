package com.danbro.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.OrderDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.dto.UserInfoDto;
import com.danbro.enity.TOrder;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.order.rpcClient.CourseClient;
import com.danbro.order.rpcClient.UserClient;
import com.danbro.order.service.TOrderService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public Result<OrderDto> createOrder(@PathVariable String courseId, HttpServletRequest request) {
        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfoDto == null) {
            throw new MyCustomException(ResultCode.USER_NO_LOGIN);
        }
        Result<UcenterMemberInfoDto> userInfoDtoResult = userClient.getUserInfo(userInfoDto.getId());
        Result<EduCourseBasicInfoDto> courseBasicInfoResultDto = courseClient.getCourseBasicInfo(courseId);
        OrderDto order = tOrderService.insertOrder(userInfoDtoResult.getData(), courseBasicInfoResultDto.getData());
        return Result.ofSuccess(order);
    }

    @ApiOperation("通过订单编号查询订单信息")
    @GetMapping("info/{orderNo}")
    public Result<OrderDto> getOrderStatusByOrderNo(@PathVariable String orderNo) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        TOrder order = tOrderService.getOne(queryWrapper);
        OrderDto orderDto = new OrderDto();
        return Result.ofSuccess(orderDto.convertFrom(order));
    }

    @ApiOperation("通过用户id和订单课程id查询订单信息")
    @GetMapping("info/status/{userId}/{courseId}")
    public Result<OrderDto> getOrderInfoByCourseId(@PathVariable String userId,@PathVariable String courseId) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", userId);
        queryWrapper.eq("course_id", courseId);
        TOrder order = tOrderService.getOne(queryWrapper);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return Result.ofSuccess(orderDto);
    }
}