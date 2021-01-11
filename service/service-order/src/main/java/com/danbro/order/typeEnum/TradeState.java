package com.danbro.order.typeEnum;

/**
 * @Classname TradeState
 * @Description TODO 交易状态
 * @Date 2021/1/11 11:18
 * @Author Danrbo
 */
public enum TradeState {
    /**
     * 交易状态
     */
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");
    private String status;

    TradeState(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
