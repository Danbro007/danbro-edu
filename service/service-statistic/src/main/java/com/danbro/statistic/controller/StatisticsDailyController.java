package com.danbro.statistic.controller;

import com.danbro.enums.Result;
import com.danbro.statistic.dto.QueryStatisticsDto;
import com.danbro.statistic.entity.StatisticsDaily;
import com.danbro.statistic.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 网站统计日数据(StatisticsDaily)表控制层
 *
 * @author makejava
 * @since 2021-01-12 10:35:54
 */
@CrossOrigin
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
    public Result getUserRegisterStatistic(@PathVariable String date) {
        statisticsDailyService.getUserRegisterNumByDate(date);
        return Result.ofSuccess();
    }

    @ApiOperation("查询每日用户登录、注册和每日课程播放、课程数统计")
    @GetMapping("count/{type}")
    public Result<Map<String, List<String>>> getStatistic(@PathVariable String type,
                                                          @RequestParam String begin,
                                                          @RequestParam String end) {
        QueryStatisticsDto queryStatisticsDto = new QueryStatisticsDto().setBegin(begin).setEnd(end).setType(type);
        Map<String, List<String>> statistic = statisticsDailyService.getStatistic(queryStatisticsDto);
        return Result.ofSuccess(statistic);
    }

}