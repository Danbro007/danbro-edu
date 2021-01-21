package com.danbro.statistic.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname QueryStatisticsDto
 * @Description TODO
 * @Date 2021/1/12 14:39
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class QueryStatisticsDto {
    /**
     * 查询数据类型
     */
    private String type;
    /**
     * 开始时间
     */
    private String begin;
    /**
     * 截止时间
     */
    private String end;
}
