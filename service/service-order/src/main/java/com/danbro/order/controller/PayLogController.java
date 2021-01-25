package com.danbro.order.controller;

import java.util.Map;
import javax.annotation.Resource;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.order.dto.WeChatPayReturnDto;
import com.danbro.order.service.TPayLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付日志表(TPayLog)表控制层
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
@RestController

@RequestMapping("order/pay")
public class PayLogController {
    /**
     * 服务对象
     */
    @Resource
    private TPayLogService tPayLogService;


    @ApiOperation("生成微信支付的二维码")
    @GetMapping("qrcode/{orderNo}")
    public Result<WeChatPayReturnDto> getPayQrCode(@PathVariable String orderNo) throws Exception {
        return Result.ofSuccess(tPayLogService.createNative(orderNo));
    }

    @ApiOperation("查询支付状态")
    @GetMapping("status/{orderNo}")
    public Result getPayStatus(@PathVariable String orderNo) throws Exception {
        Map<String, String> map = tPayLogService.queryOrderPayStatus(orderNo);
        if (map != null) {
            // 把订单的支付状态转换成已支付
            tPayLogService.updateOrderStatus(orderNo);
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.FAILURE);
    }
}