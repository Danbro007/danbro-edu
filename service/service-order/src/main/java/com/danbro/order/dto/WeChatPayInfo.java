package com.danbro.order.dto;

import java.util.HashMap;
import java.util.Map;
import cn.hutool.core.annotation.Alias;
import cn.hutool.core.bean.BeanUtil;
import com.danbro.order.utils.WeChatPayUtils;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname WeChatPayInfo
 * @Description TODO 微信支付参数
 * @Date 2021/1/11 14:08
 * @Author Danrbo
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class WeChatPayInfo {

    public WeChatPayInfo() {
        this.appId = WeChatPayUtils.APP_ID;
        this.mchId = WeChatPayUtils.MCH_ID;
        this.nonceStr = WXPayUtil.generateNonceStr();
        this.spBillCreateIp = WeChatPayUtils.BILL_CREATE_IP;
        this.notifyUrl = WeChatPayUtils.NOTIFY_URL;
        this.tradeType = WeChatPayUtils.TRADE_TYPE;
    }

    @Alias("appid")
    private String appId;
    @Alias("mch_id")
    private String mchId;
    @Alias("nonce_str")
    private String nonceStr;
    /**
     * 订单的课程名
     */
    private String body;
    /**
     * 订单编号
     */
    @Alias("out_trade_no")
    private String outTradeNo;
    /**
     * 订单总金额
     */
    @Alias("total_fee")
    private String totalFee;
    /**
     * 交易创建的IP
     */
    @Alias("spbill_create_ip")
    private String spBillCreateIp;
    /**
     * 支付成功后要通知的接口
     */
    @Alias("notify_url")
    private String notifyUrl;
    /**
     * 交易类型
     */
    @Alias("trade_type")
    private String tradeType;

    public Map<String, String> getWeChatMap() {
        Map<String, Object> map = BeanUtil.beanToMap(this, true, false);
        HashMap<String, String> hashMap = new HashMap<>();
        map.forEach((key, value) -> hashMap.put(key, (String) value));
        return hashMap;
    }
}
