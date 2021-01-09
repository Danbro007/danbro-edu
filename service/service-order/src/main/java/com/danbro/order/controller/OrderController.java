package com.danbro.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.order.rpcClient.CourseClient;
import com.danbro.order.rpcClient.UserClient;
import com.danbro.order.service.TOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {
//        UserInfoDto userInfoDto = JwtUtils.getMemberIdByJwtToken(request);
//        if (userInfoDto == null) {
//            throw new MyCustomException(ResultCode.USER_NO_LOGIN);
//        }
//        Result userInfoResult = userClient.getUserInfo(userInfoDto.getId());
        UcenterMemberInfoDto userInfo = userClient.getUserInfo("1346732034546044929");
        Result<EduCourseBasicInfoDto> courseBasicInfo = courseClient.getCourseBasicInfo(courseId);
        Boolean b = tOrderService.insertOrder(userInfo, courseBasicInfo.getData());
        if (b) {
            return Result.ofSuccess();
        }
        throw new MyCustomException(ResultCode.INSERT_ORDER_FAILURE);

    }

}