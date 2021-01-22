package com.danbro.statistic.controller.vo;

import com.danbro.impl.VoConvert;
import com.danbro.statistic.entity.StatisticsDaily;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 网站统计日数据(StatisticsDaily)实体类
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
@Accessors(chain = true)
@Data
@ApiModel("返回的统计数据")
public class StatisticsDailyVo implements Serializable, VoConvert<StatisticsDailyVo, StatisticsDaily> {
    private static final long serialVersionUID = 4890730277005324396L;

    @ApiModelProperty("统计数据的ID")
    private String id;

    @ApiModelProperty("统计日期")
    private String dateCalculated;

    @ApiModelProperty("注册人数")
    private Integer registerNum;

    @ApiModelProperty("注册人数")
    private Integer loginNum;

    @ApiModelProperty("每日播放视频数")
    private Integer videoViewNum;

    @ApiModelProperty("每日新增课程数")
    private Integer courseNum;

    @Override
    public StatisticsDailyVo convertFrom(StatisticsDaily statisticsDaily) {
        BeanUtils.copyProperties(statisticsDaily, this);
        return this;
    }
}