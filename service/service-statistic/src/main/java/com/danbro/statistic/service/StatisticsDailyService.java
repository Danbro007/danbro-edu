package com.danbro.statistic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.statistic.dto.QueryStatisticsDto;
import com.danbro.statistic.entity.StatisticsDaily;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 网站统计日数据(StatisticsDaily)表服务接口
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    StatisticsDaily getUserRegisterNumByDate(String date);

    Map<String, List<String>> getStatistic(QueryStatisticsDto dto);

}