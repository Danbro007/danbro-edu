package com.danbro.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.danbro.enity.EduTeacher;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

    @ApiModelProperty(name = "讲师教授的课程")
    private List<CourseVo> courseList;

    @Override
    public TeacherVo convertFrom(EduTeacher eduTeacher) {
        BeanUtils.copyProperties(eduTeacher, this);
        return this;
    }
}
