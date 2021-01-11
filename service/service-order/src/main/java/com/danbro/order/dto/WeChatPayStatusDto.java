package com.danbro.order.dto;

import java.util.HashMap;
import java.util.Map;
import cn.hutool.core.annotation.Alias;
import cn.hutool.core.bean.BeanUtil;
import com.danbro.order.utils.WeChatPayUtils;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname WeChatPayStatusDto
 * @Description TODO 查询微信支付状态的请求参数
 * @Date 2021/1/11 19:39
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
public class WeChatPayStatusDto {

    @Alias("appid")
    private String appId;
    @Alias("mch_id")
    private String mchId;
    @Alias("nonce_str")
    private String nonceStr;
    @Alias("out_trade_no")
    private String orderNo;


    public WeChatPayStatusDto() {
        this.appId = WeChatPayUtils.APP_ID;
        this.mchId = WeChatPayUtils.MCH_ID;
        this.nonceStr = WXPayUtil.generateNonceStr();
    }

    public Map<String, String> getWeChatMap() {
        Map<String, Object> map = BeanUtil.beanToMap(this, true, false);
        HashMap<String, String> hashMap = new HashMap<>();
        map.forEach((key, value) -> hashMap.put(key, (String) value));
        return hashMap;
    }
}
