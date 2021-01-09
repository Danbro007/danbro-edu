package com.danbro.edu.dto;

import javax.validation.constraints.NotEmpty;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname EduTeacherInsertDto
 * @Description TODO 后台用户更新讲师信息的参数
 * @Date 2020/12/15 13:53
 * @Author Danrbo
 */
@Data
public class InPutEduTeacherUpdateDto implements DtoConvert<InPutEduTeacherUpdateDto, EduTeacher> {
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
    @ApiModelProperty(value = "讲师等级", example = "1")
    private Integer level;
    /**
     * 讲师头像
     */
    private String avatar;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", example = "1")
    private Integer sort;

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doForward(this);
    }

    @Override
    public InPutEduTeacherUpdateDto convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor convertor = new EduTeacherDtoConvertor();
        return convertor.doBackward(eduTeacher);
    }

    private static class EduTeacherDtoConvertor extends Converter<InPutEduTeacherUpdateDto, EduTeacher> {
        @Override
        protected EduTeacher doForward(InPutEduTeacherUpdateDto eduTeacherInsertDto) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(eduTeacherInsertDto, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected InPutEduTeacherUpdateDto doBackward(EduTeacher eduTeacher) {
            InPutEduTeacherUpdateDto eduTeacherInsertDto = new InPutEduTeacherUpdateDto();
            BeanUtils.copyProperties(eduTeacher, eduTeacherInsertDto);
            return eduTeacherInsertDto;
        }
    }
}
