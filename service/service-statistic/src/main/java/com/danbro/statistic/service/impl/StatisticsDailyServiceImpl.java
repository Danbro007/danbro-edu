package com.danbro.statistic.service.impl;

import java.util.ArrayList;
import java.util.List;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.statistic.dto.QueryStatisticsParam;
import com.danbro.statistic.entity.StatisticsDaily;
import com.danbro.statistic.mapper.StatisticsDailyMapper;
import com.danbro.statistic.rpcClient.UserClient;
import com.danbro.statistic.service.StatisticsDailyService;
import com.danbro.statistic.vo.StatisticsDailyListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 网站统计日数据(StatisticsDaily)表服务实现类
 *
 * @author makejava
 * @since 2021-01-12 10:35:54
 */
@Service("statisticsDailyService")
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UserClient userClient;

    @Override
    public StatisticsDaily getUserRegisterNumByDate(String date) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", date);
        this.remove(queryWrapper);
        Integer num = userClient.getUserRegisterByDate(date).getData();
        StatisticsDaily statisticsDaily = new StatisticsDaily()
                .setCourseNum(RandomUtil.randomInt(4))
                .setRegisterNum(num)
                .setDateCalculated(date)
                .setLoginNum(RandomUtil.randomInt(4))
                .setVideoViewNum(RandomUtil.randomInt(4));
        this.save(statisticsDaily);
        return statisticsDaily;
    }

    @Override
    public StatisticsDailyListVo getStatistic(QueryStatisticsParam dto) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", dto.getBegin(), dto.getEnd());
        queryWrapper.select("date_calculated", dto.getType());
        queryWrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> statisticsDailies = this.list(queryWrapper);
        List<String> dateList = new ArrayList<>();
        List<String> numList = new ArrayList<>();
        // 通过反射
        statisticsDailies.forEach(e -> {
            dateList.add(e.getDateCalculated());
            Integer data = ReflectUtil.invoke(e, String.format("get%s", StrUtil
                    .upperFirst(StrUtil
                            .toCamelCase(dto.getType()))));
            numList.add(data.toString());
        });
        return new StatisticsDailyListVo().setDateList(dateList).setNumList(numList);
    }
}