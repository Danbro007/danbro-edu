package com.danbro.edu.controller.param;

import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Classname TeacherQueryVo
 * @Description TODO 查询讲师的参数
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Data
public class QueryTeacherParam implements DtoConvert<QueryTeacherParam, EduTeacher> {
    @ApiModelProperty("讲师名")
    private String name;

    @ApiModelProperty(value = "讲师等级", example = "1")
    private Integer level;

    @ApiModelProperty("创建时间")
    private Date start;

    @ApiModelProperty("修改时间")
    private Date end;

    @Override
    public QueryTeacherParam convertFrom(EduTeacher eduTeacher) {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doBackward(eduTeacher);
    }

    @Override
    public EduTeacher convertTo() {
        EduTeacherDtoConvertor eduTeacherDtoConvertor = new EduTeacherDtoConvertor();
        return eduTeacherDtoConvertor.doForward(this);
    }


    private static class EduTeacherDtoConvertor extends Converter<QueryTeacherParam, EduTeacher> {
        @Override
        protected EduTeacher doForward(QueryTeacherParam queryTeacherParam) {
            EduTeacher eduTeacher = new EduTeacher();
            BeanUtils.copyProperties(queryTeacherParam, eduTeacher);
            return eduTeacher;
        }

        @Override
        protected QueryTeacherParam doBackward(EduTeacher eduTeacher) {
            QueryTeacherParam queryTeacherParam = new QueryTeacherParam();
            BeanUtils.copyProperties(eduTeacher, queryTeacherParam);
            return queryTeacherParam;
        }
    }
}
