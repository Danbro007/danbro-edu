package com.danbro.statistic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.statistic.controller.param.QueryStatisticsParam;
import com.danbro.statistic.entity.StatisticsDaily;
import com.danbro.statistic.controller.vo.StatisticsDailyListVo;

/**
 * 网站统计日数据(StatisticsDaily)表服务接口
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    /**
     * 通过日期获取那天的注册人数
     *
     * @param date 日期
     * @return 统计信息
     */
    StatisticsDaily getUserRegisterNumByDate(String date);

    /**
     * 查询统计的条件
     *
     * @param statisticsParam 查询条件
     * @return 统计信息
     */
    StatisticsDailyListVo getStatistic(QueryStatisticsParam statisticsParam);

}