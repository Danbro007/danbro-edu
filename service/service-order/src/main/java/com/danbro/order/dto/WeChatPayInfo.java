package com.danbro.order.dto;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Classname WeChatPayInfo
 * @Description TODO 微信支付参数
 * @Date 2021/1/11 14:08
 * @Author Danrbo
 */
@Data
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class WeChatPayInfo {

    private String appId;
    private String mchId;
    private String nonceStr;
    /**
     * 订单的课程名
     */
    private String body;
    /**
     * 订单编号
     */
    private String outTradeNo;
    /**
     * 订单总金额
     */
    private BigDecimal totalFee;
    /**
     * 交易创建的IP
     */
    private String spBillCreateIp;
    /**
     * 支付成功后要通知的接口
     */
    private String notifyUrl;
    /**
     * 交易类型
     */
    private String tradeType;

    private Map<String, String> mapping;


    public Map<String, Object> getWeChatMap() {
        WeChatPayInfo weChatPayInfo = WeChatPayInfo.builder().appId("1").body("2").mchId("3").build();
        Map<String, Object> map = BeanUtil.beanToMap(weChatPayInfo, true, true);
        Object app_id = map.get("app_id");
        map.remove("app_id");
        map.put("appid", app_id);
        return map;
    }


}
