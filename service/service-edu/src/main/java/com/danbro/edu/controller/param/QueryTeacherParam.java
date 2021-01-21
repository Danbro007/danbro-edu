package com.danbro.edu.controller.param;

import java.util.Date;
import com.danbro.enity.EduTeacher;
import com.danbro.impl.ParamConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname TeacherQueryVo
 * @Description TODO 查询讲师的参数
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Data
@ApiModel("查询讲师的参数")
public class QueryTeacherParam implements ParamConvert<EduTeacher> {
    @ApiModelProperty("讲师名")
    private String name;

    @ApiModelProperty(value = "讲师等级", example = "1")
    private Integer level;

    @ApiModelProperty("创建时间")
    private Date start;

    @ApiModelProperty("修改时间")
    private Date end;


    @Override
    public EduTeacher convertTo() {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(this, eduTeacher);
        return eduTeacher;
    }
}
