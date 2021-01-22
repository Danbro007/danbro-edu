package com.danbro.statistic.controller;

import java.util.Date;
import javax.annotation.Resource;

import com.danbro.enums.Result;
import com.danbro.statistic.controller.param.QueryStatisticsParam;
import com.danbro.statistic.controller.vo.StatisticsDailyVo;
import com.danbro.statistic.service.StatisticsDailyService;
import com.danbro.statistic.controller.vo.StatisticsDailyListVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网站统计日数据(StatisticsDaily)表控制层
 *
 * @author makejava
 * @since 2021-01-12 10:35:54
 */

@RestController
@RequestMapping("statistic/daily")
public class StatisticsDailyController {
    /**
     * 服务对象
     */
    @Resource
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("生成每日注册用户数据")
    @GetMapping("/register-count/{date}")
    public Result<StatisticsDailyVo> getUserRegisterStatistic(@PathVariable Date date) {
        return Result.ofSuccess(new StatisticsDailyVo().convertFrom(statisticsDailyService.getUserRegisterNumByDate(date.toString())));
    }

    @ApiOperation("查询每日用户登录、注册和每日课程播放、课程数统计")
    @GetMapping("count/{type}")
    public Result<StatisticsDailyListVo> getStatistic(@PathVariable String type,
                                                      @RequestParam String begin,
                                                      @RequestParam String end) {
        QueryStatisticsParam queryStatisticsParam = new QueryStatisticsParam().setBegin(begin).setEnd(end).setType(type);
        return Result.ofSuccess(statisticsDailyService.getStatistic(queryStatisticsParam));
    }

}