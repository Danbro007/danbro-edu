package com.danbro.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname TeacherQueryVo
 * @Description TODO 接收前端传来的分页查询参数
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Data
public class TeacherQueryVo {
    @ApiModelProperty("教师名")
    private String name;

    @ApiModelProperty("教师等级")
    private Integer level;

    @ApiModelProperty("创建时间")
    private LocalDateTime start;

    @ApiModelProperty("修改时间")
    private LocalDateTime end;
}
