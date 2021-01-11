package com.danbro.order.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.enity.TOrder;
import com.danbro.order.dto.WeChatPayInfo;
import com.danbro.order.dto.WeChatPayReturnDto;
import com.danbro.order.dto.WeChatPayStatusDto;
import com.danbro.order.entity.TPayLog;
import com.danbro.order.mapper.TPayLogMapper;
import com.danbro.order.service.TOrderService;
import com.danbro.order.service.TPayLogService;
import com.danbro.order.typeEnum.TradeState;
import com.danbro.order.utils.WeChatPayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付日志表(TPayLog)表服务实现类
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
@Service("tPayLogService")
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
    @Autowired
    WeChatPayUtils weChatPayUtils;
    @Autowired
    TOrderService tOrderService;


    @Override
    public WeChatPayReturnDto createNative(String orderNo) throws Exception {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        TOrder tOrder = tOrderService.getOne(queryWrapper);
        WeChatPayInfo weChatPayInfo = new WeChatPayInfo()
                .setBody(tOrder.getCourseTitle())
                .setOutTradeNo(tOrder.getOrderNo())
                .setTotalFee(tOrder.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
        Map<String, String> resultMap = weChatPayUtils.sendRequest(weChatPayUtils.getRequestUrl(), weChatPayInfo.getWeChatMap());
        return WeChatPayReturnDto.builder()
                .resultCode(resultMap.get("result_code"))
                .codeUrl(resultMap.get("code_url"))
                .outTradeNo(tOrder.getOrderNo())
                .totalFee(weChatPayInfo.getTotalFee())
                .courseId(tOrder.getCourseId())
                .build();
    }


    @Override
    public Map<String, String> queryOrderPayStatus(String orderNo) throws Exception {
        WeChatPayStatusDto payStatusDto = new WeChatPayStatusDto().setOrderNo(orderNo);
        Map<String, String> resultMap = weChatPayUtils.sendRequest(weChatPayUtils.getPayStatusQueryUrl(), payStatusDto.getWeChatMap());
        if (resultMap.get("trade_state").equals(TradeState.SUCCESS.getStatus())) {
            return resultMap;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", map.get("out_trade_no"));
        TOrder tOrder = tOrderService.getOne(queryWrapper);
        tOrder.setStatus(true);
        tOrderService.updateById(tOrder);
        TPayLog log = TPayLog.builder()
                .id(tOrder.getId())
                .payType(tOrder.getPayType())
                .orderNo(tOrder.getOrderNo())
                .totalFee(tOrder.getTotalFee())
                .tradeState(TradeState.SUCCESS.getStatus())
                .transactionId(map.get("transaction_id"))
                .attr(JSON.toJSONString(map))
                .payTime(new Date())
                .build();
        this.save(log);
    }
}
