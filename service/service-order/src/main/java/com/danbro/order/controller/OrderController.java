package com.danbro.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.dto.UserInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.order.dto.OutPutOrderInsertDto;
import com.danbro.order.entity.TOrder;
import com.danbro.order.rpcClient.CourseClient;
import com.danbro.order.rpcClient.UserClient;
import com.danbro.order.service.TOrderService;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 订单(TOrder)表控制层
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
@CrossOrigin
@RestController
@RequestMapping("edu/order")
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
    public Result<OutPutOrderInsertDto> createOrder(@PathVariable String courseId, HttpServletRequest request) {
        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfoDto == null) {
            throw new MyCustomException(ResultCode.USER_NO_LOGIN);
        }
        Result<UcenterMemberInfoDto> userInfoDtoResult = userClient.getUserInfo(userInfoDto.getId());
        Result<EduCourseBasicInfoDto> courseBasicInfoResultDto = courseClient.getCourseBasicInfo(courseId);
        OutPutOrderInsertDto order = tOrderService.insertOrder(userInfoDtoResult.getData(), courseBasicInfoResultDto.getData());
        return Result.ofSuccess(order);
    }

    @ApiOperation("通过订单号查询订单支付状态")
    @GetMapping("status/{orderNo}")
    public Result<TOrder> getOrderStatusByOrderNo(@PathVariable String orderNo) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        TOrder order = tOrderService.getOne(queryWrapper);
        return Result.ofSuccess(order);
    }
}