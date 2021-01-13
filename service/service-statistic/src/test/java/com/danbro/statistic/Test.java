package com.danbro.statistic;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.danbro.statistic.entity.StatisticsDaily;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2021/1/12 16:26
 * @Author Danrbo
 */

public class Test {
    @org.junit.Test
    public void test() {
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setId("123");
        Object getId = ReflectUtil.invoke(statisticsDaily, "getId");
        System.out.println(getId);
        String login_num = StrUtil.toCamelCase("login_num");
        System.out.println(login_num);
    }
}
