package com.danbro.edu.controller.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname EduTeacherInsertDto
 * @Description TODO 后台用户添加讲师的参数
 * @Date 2020/12/15 13:53
 * @Author Danrbo
 */
@Data
public class InsertTeacherParam implements DtoConvert<InsertTeacherParam, EduTeacher> {
    /**
     * 讲师姓名
     */
    @NotEmpty(message = "姓名不能为空！")
    private String name;
    /**
     * 讲师简介
     */
    @NotEmpty(message = "讲师简介不能为空！")
    private String intro;
    /**
     * 讲师资历,一句话说明讲师
     */
    @NotEmpty(message = "讲师资历不能为空")
    private String career;
    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @ApiModelProperty(value = "讲师等级", example = "1")
    @NotNull(message = "讲师头衔不能为空")
    private Integer level;
    /**
     * 讲师头像
     */
    @NotEmpty(message = "讲师头像必须上传")
    private String avatar;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", example = "1")
    @NotNull(message = "讲师排序不能为空")
    private Integer sort;

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doForward(this);
    }

    @Override
    public InsertTeacherParam convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doBackward(eduTeacher);
    }

    private static class EduTeacherDtoConvertor extends Converter<InsertTeacherParam, EduTeacher> {
        @Override
        protected EduTeacher doForward(InsertTeacherParam insertTeacherParam) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(insertTeacherParam, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected InsertTeacherParam doBackward(EduTeacher eduTeacher) {
            InsertTeacherParam insertTeacherParam = new InsertTeacherParam();
            BeanUtils.copyProperties(eduTeacher, insertTeacherParam);
            return insertTeacherParam;
        }
    }
}
