package com.danbro.dto;

import com.danbro.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @Classname TeacherQueryVo
 * @Description TODO 接收前端传来的分页查询参数
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Data
public class EduTeacherQueryDto implements DtoConvert<EduTeacherQueryDto, EduTeacher> {
    @ApiModelProperty("教师名")
    private String name;

    @ApiModelProperty("教师等级")
    private Integer level;

    @ApiModelProperty("创建时间")
    private LocalDateTime start;

    @ApiModelProperty("修改时间")
    private LocalDateTime end;

    @Override
    public EduTeacherQueryDto convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doBackward(eduTeacher);
    }

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doForward(this);
    }


    private static class EduTeacherDtoConvertor extends Converter<EduTeacherQueryDto, EduTeacher> {
        @Override
        protected EduTeacher doForward(EduTeacherQueryDto eduTeacherQueryDto) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(eduTeacherQueryDto, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected EduTeacherQueryDto doBackward(EduTeacher eduTeacher) {
            EduTeacherQueryDto eduTeacherQueryDto = new EduTeacherQueryDto();
            BeanUtils.copyProperties(eduTeacher, eduTeacherQueryDto);
            return eduTeacherQueryDto;
        }
    }
}
