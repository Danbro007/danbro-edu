package com.danbro.edu.controller.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.danbro.edu.entity.EduCourse;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname CourseVo
 * @Description TODO
 * @Date 2021/1/19 16:00
 * @Author Danrbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseVo implements Serializable, VoConvert<CourseVo, EduCourse> {
    private static final long serialVersionUID = 3397618604401662583L;

    @ApiModelProperty("课程ID")
    private String id;

    @ApiModelProperty("讲师信息")
    private TeacherVo teacher;

    @ApiModelProperty("二级课程分类ID")
    private String subjectId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程价格")
    private BigDecimal price;

    @ApiModelProperty("课程的章节数")
    private Integer lessonNum;

    @ApiModelProperty("课程封面")
    private String cover;

    @ApiModelProperty("课程购买数")
    private Integer buyCount;

    @ApiModelProperty("课程观看数")
    private Long viewCount;

    @ApiModelProperty("课程发布状态")
    private String status;

    @ApiModelProperty("创建课程的时间")
    private Date gmtCreate;

    @ApiModelProperty("修改课程的时间")
    private Date gmtModified;

    @ApiModelProperty("课程简介")
    private String description;

    @Override
    public CourseVo convertFrom(EduCourse eduCourse) {
        BeanUtils.copyProperties(eduCourse, this);
        return this;
    }
}
