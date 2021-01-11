package com.danbro.order.typeEnum;

/**
 * @Classname PayTypeEnum
 * @Description TODO 支付方式
 * @Date 2021/1/11 11:04
 * @Author Danrbo
 */
public enum PayType {
    /**
     * 支付方式
     */
    WECHAT(1),
    ALIPAY(2);

    private Integer type;

    public Integer getType() {
        return type;
    }


    PayType(Integer type) {
        this.type = type;
    }
}
