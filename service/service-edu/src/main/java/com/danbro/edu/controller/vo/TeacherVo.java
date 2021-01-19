package com.danbro.edu.controller.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname TeacherVo
 * @Description TODO 创建或者更新完毕后的讲师信息
 * @Date 2021/1/19 14:13
 * @Author Danrbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherVo implements Serializable, VoConvert<TeacherVo, EduTeacher> {
    private static final long serialVersionUID = -1183587142346663463L;
    @ApiModelProperty(name = "讲师ID")
    private String id;

    @ApiModelProperty(name = "讲师姓名")
    private String name;

    @ApiModelProperty(name = "讲师简介")
    private String intro;

    @ApiModelProperty(name = "讲师生涯")
    private String career;

    @ApiModelProperty(name = "讲师等级")
    private Integer level;

    @ApiModelProperty(name = "讲师头像")
    private String avatar;

    @ApiModelProperty(name = "讲师排序")
    private Integer sort;

    @ApiModelProperty(name = "创建讲师的时间")
    private Date gmtCreate;

    @ApiModelProperty(name = "修改讲师的时间")
    private Date gmtModified;

    @Override
    public TeacherVo convertFrom(EduTeacher eduTeacher) {
        BeanUtils.copyProperties(eduTeacher, this);
        return this;
    }
}
