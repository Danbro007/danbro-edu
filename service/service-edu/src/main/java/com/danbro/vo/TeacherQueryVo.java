package com.danbro.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

/**
 * @Classname TeacherQueryVo
 * @Description TODO
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
    private Date start;
    @ApiModelProperty("修改时间")
    private Date end;
}
