package com.danbro.dto;

import com.danbro.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Classname EduTeacherInsertDto
 * @Description TODO
 * @Date 2020/12/15 13:53
 * @Author Danrbo
 */
@Data
public class EduTeacherInsertDto implements DtoConvert<EduTeacherInsertDto, EduTeacher> {
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
//    @NotEmpty(message = "讲师头像必须上传")
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
    public EduTeacherInsertDto convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doBackward(eduTeacher);
    }

    private static class EduTeacherDtoConvertor extends Converter<EduTeacherInsertDto, EduTeacher> {
        @Override
        protected EduTeacher doForward(EduTeacherInsertDto eduTeacherInsertDto) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(eduTeacherInsertDto, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected EduTeacherInsertDto doBackward(EduTeacher eduTeacher) {
            EduTeacherInsertDto eduTeacherInsertDto = new EduTeacherInsertDto();
            BeanUtils.copyProperties(eduTeacher, eduTeacherInsertDto);
            return eduTeacherInsertDto;
        }
    }
}
