package com.danbro.statistic.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * 网站统计日数据(StatisticsDaily)实体类
 *
 * @author makejava
 * @since 2021-01-12 10:35:53
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDaily implements Serializable {
    private static final long serialVersionUID = -76766288670652955L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 统计日期
     */
    private String dateCalculated;
    /**
     * 注册人数
     */
    private Integer registerNum;
    /**
     * 登录人数
     */
    private Integer loginNum;
    /**
     * 每日播放视频数
     */
    private Integer videoViewNum;
    /**
     * 每日新增课程数
     */
    private Integer courseNum;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}