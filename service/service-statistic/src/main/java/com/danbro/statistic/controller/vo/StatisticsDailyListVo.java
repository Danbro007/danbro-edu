package com.danbro.statistic.controller.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname StatisticsDailyVo
 * @Description TODO 各种每日数据的集合
 * @Date 2021/1/21 22:28
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
public class StatisticsDailyListVo implements Serializable {
    private List<String> dateList;
    private List<String> numList;
}
