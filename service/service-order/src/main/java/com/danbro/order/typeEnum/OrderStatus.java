package com.danbro.order.typeEnum;

/**
 * @Classname OrderStatus
 * @Description TODO 订单状态
 * @Date 2021/1/11 11:09
 * @Author Danrbo
 */
public enum OrderStatus {
    /**
     * 支付状态
     */
    PAID(true),
    UN_PAID(false),
    ;

    private Boolean status;

    OrderStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
