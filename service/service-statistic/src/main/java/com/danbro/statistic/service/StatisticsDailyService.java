package com.danbro.statistic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.statistic.dto.QueryStatisticsParam;
import com.danbro.statistic.entity.StatisticsDaily;
import com.danbro.statistic.vo.StatisticsDailyListVo;

/**
 * 网站统计日数据(StatisticsDaily)表服务接口
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    StatisticsDaily getUserRegisterNumByDate(String date);

    /**
     * 查询统计的条件
     * @param statisticsParam 查询条件
     * @return
     */
    StatisticsDailyListVo getStatistic(QueryStatisticsParam statisticsParam);

}