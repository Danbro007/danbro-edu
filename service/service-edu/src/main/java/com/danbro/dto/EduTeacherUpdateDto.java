package com.danbro.dto;

import com.danbro.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
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
public class EduTeacherUpdateDto implements DtoConvert<EduTeacherUpdateDto, EduTeacher> {
    /**
     * 教师id
     */
    @NotEmpty(message = "教师id不能为空！")
    private String id;
    /**
     * 讲师姓名
     */
    private String name;
    /**
     * 讲师简介
     */
    private String intro;
    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;
    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Integer level;
    /**
     * 讲师头像
     */
    private String avatar;
    /**
     * 排序
     */
    private Integer sort;

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doForward(this);
    }

    @Override
    public EduTeacherUpdateDto convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doBackward(eduTeacher);
    }

    private static class EduTeacherDtoConvertor extends Converter<EduTeacherUpdateDto, EduTeacher> {
        @Override
        protected EduTeacher doForward(EduTeacherUpdateDto eduTeacherInsertDto) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(eduTeacherInsertDto, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected EduTeacherUpdateDto doBackward(EduTeacher eduTeacher) {
            EduTeacherUpdateDto eduTeacherInsertDto = new EduTeacherUpdateDto();
            BeanUtils.copyProperties(eduTeacher, eduTeacherInsertDto);
            return eduTeacherInsertDto;
        }
    }
}
