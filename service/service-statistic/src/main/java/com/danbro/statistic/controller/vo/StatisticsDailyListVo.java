package com.danbro.statistic.controller.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
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
    private static final long serialVersionUID = -2032082412668090726L;

    @ApiModelProperty("日期列表")
    private List<String> dateList;

    @ApiModelProperty("日期相关的统计数据列表")
    private List<String> numList;
}
