package com.danbro.statistic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.statistic.entity.StatisticsDaily;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 网站统计日数据(StatisticsDaily)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {
}