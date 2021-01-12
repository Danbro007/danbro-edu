package com.danbro.statistic.schedule;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.danbro.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Locale;


/**
 * @Classname CreateStatisticTask
 * @Description TODO
 * @Date 2021/1/12 12:55
 * @Author Danrbo
 */
@Component
public class StatisticTask {
    @Autowired
    StatisticsDailyService statisticsDailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void createUserRegisterStatistic() {
        DateTime yesterday = DateUtil.yesterday();
        statisticsDailyService.getUserRegisterNumByDate(yesterday.toDateStr());
    }

}
