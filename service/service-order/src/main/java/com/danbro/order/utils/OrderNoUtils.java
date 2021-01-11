package com.danbro.order.utils;

import cn.hutool.core.util.RandomUtil;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Classname OrderNoUtils
 * @Description TODO
 * @Date 2021/1/11 10:12
 * @Author Danrbo
 */

public class OrderNoUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getOrderNo() {
        return LocalDate.now().format(DATE_TIME_FORMATTER) + RandomUtil.randomNumbers(8);
    }

    public static void main(String[] args) {
        String orderNo = getOrderNo();
        System.out.println(orderNo + RandomUtil.randomNumbers(8));
    }
}
