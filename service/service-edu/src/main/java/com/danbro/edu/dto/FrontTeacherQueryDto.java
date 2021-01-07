package com.danbro.edu.dto;

import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Classname TeacherQueryVo
 * @Description TODO 接收前端传来的分页查询参数
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Data
public class FrontTeacherQueryDto implements DtoConvert<FrontTeacherQueryDto, EduTeacher> {
    @ApiModelProperty("教师名")
    private String name;

    @ApiModelProperty(value = "讲师等级",example = "1")
    private Integer level;

    @ApiModelProperty("创建时间")
    private Date start;

    @ApiModelProperty("修改时间")
    private Date end;

    @Override
    public FrontTeacherQueryDto convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doBackward(eduTeacher);
    }

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doForward(this);
    }


    private static class EduTeacherDtoConvertor extends Converter<FrontTeacherQueryDto, EduTeacher> {
        @Override
        protected EduTeacher doForward(FrontTeacherQueryDto frontTeacherQueryDto) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(frontTeacherQueryDto, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected FrontTeacherQueryDto doBackward(EduTeacher eduTeacher) {
            FrontTeacherQueryDto frontTeacherQueryDto = new FrontTeacherQueryDto();
            BeanUtils.copyProperties(eduTeacher, frontTeacherQueryDto);
            return frontTeacherQueryDto;
        }
    }
}
