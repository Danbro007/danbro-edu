package com.danbro.edu.utils;

import java.util.HashMap;

/**
 * @Classname SortType
 * @Description TODO 前台用户排序类型Enum
 * @Date 2021/1/7 12:35
 * @Author Danrbo
 */

public enum SortType {
    /**
     * 排序枚举类
     */
    SALE_SORT("buy_count"),
    CREATE_TIME_SORT("gmt_create"),
    PRICE_SORT("price");

    private String type;


    SortType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
