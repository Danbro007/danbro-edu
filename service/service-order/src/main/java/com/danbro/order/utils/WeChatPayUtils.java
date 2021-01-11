package com.danbro.order.utils;

import java.util.Map;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Classname WechatPayUtils
 * @Description TODO
 * @Date 2021/1/11 18:22
 * @Created by Administrator
 */
@Component
@Data
public class WeChatPayUtils implements InitializingBean {
    @Value("${wxpay.appid}")
    private String appId;
    @Value("${wxpay.mchid}")
    private String mchId;
    @Value("${wxpay.notifyurl}")
    private String notifyUrl;
    @Value("${wxpay.tradetype}")
    private String tradeType;
    @Value("${wxpay.request.url}")
    private String requestUrl;
    @Value("${wxpay.bill.create.ip}")
    private String billCreateIp;
    @Value("${wxpay.key}")
    private String key;
    @Value("${wxpay.paystatus.query.url}")
    private String payStatusQueryUrl;

    public static String APP_ID;
    public static String MCH_ID;
    public static String NOTIFY_URL;
    public static String TRADE_TYPE;
    public static String REQUEST_URL;
    public static String BILL_CREATE_IP;
    public static String KEY;
    private static String PAY_STATUS_QUERY_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = this.appId;
        MCH_ID = this.mchId;
        NOTIFY_URL = this.notifyUrl;
        TRADE_TYPE = this.tradeType;
        REQUEST_URL = this.notifyUrl;
        BILL_CREATE_IP = this.billCreateIp;
        KEY = this.key;
        PAY_STATUS_QUERY_URL = this.payStatusQueryUrl;
    }

    public Map<String, String> sendRequest(String url, Map<String, String> map) throws Exception {
        HttpClient client = new HttpClient(url);
        //client设置参数
        client.setXmlParam(WXPayUtil.generateSignedXml(map, this.getKey()));
        client.setHttps(true);
        client.post();
        //3、返回第三方的数据
        String xml = client.getContent();
        return WXPayUtil.xmlToMap(xml);
    }
}
