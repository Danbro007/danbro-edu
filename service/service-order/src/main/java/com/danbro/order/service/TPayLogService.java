package com.danbro.order.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.order.dto.WeChatPayReturnDto;
import com.danbro.order.entity.TPayLog;

/**
 * 支付日志表(TPayLog)表服务接口
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
public interface TPayLogService extends IService<TPayLog> {
    /**
     * 通过订单编号创建微信支付二维码
     *
     * @param orderNo 订单编号
     * @return 微信支付二维码的信息
     * @throws Exception 微信支付服务异常
     */
    WeChatPayReturnDto createNative(String orderNo) throws Exception;

    /**
     * 通过订单编号查询到订单状态
     *
     * @param orderNo 订单编号
     * @return 订单信息
     * @throws Exception 异常
     */
    Map<String, String> queryOrderPayStatus(String orderNo) throws Exception;

    /**
     * 更新订单状态（已支付）并创建订单支付日志。
     *
     * @param orderNo 订单编号
     */
    void updateOrderStatus(String orderNo);

}