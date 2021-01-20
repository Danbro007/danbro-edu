package com.danbro.edu.controller.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname EduTeacherInsertDto
 * @Description TODO 后台用户修改讲师信息的参数
 * @Date 2020/12/15 13:53
 * @Author Danrbo
 */
@Data
@ApiModel("更新讲师的参数")
public class TeacherParam implements ParamConvert<EduTeacher> {

    @ApiModelProperty("讲师ID")
    @NotEmpty(message = "更新讲师时ID不能为空！", groups = {Update.class})
    @Null(message = "创建讲师时ID只能为空！", groups = {Insert.class})
    private String id;

    @ApiModelProperty("讲师姓名")
    @NotEmpty(message = "姓名不能为空！", groups = {Insert.class, Update.class})
    private String name;

    @ApiModelProperty("讲师简介")
    @NotEmpty(message = "讲师简介不能为空！", groups = {Insert.class, Update.class})
    private String intro;

    @ApiModelProperty("讲师资历")
    @NotEmpty(message = "讲师资历不能为空", groups = {Insert.class, Update.class})
    private String career;

    @ApiModelProperty("讲师等级")
    @NotNull(message = "讲师头衔不能为空", groups = {Insert.class, Update.class})
    private Integer level;

    @ApiModelProperty("讲师头像")
    @NotEmpty(message = "讲师头像必须上传", groups = {Insert.class, Update.class})
    private String avatar;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "讲师排序不能为空", groups = {Insert.class, Update.class})
    private Integer sort;

    @Override
    public EduTeacher convertTo() {
        EduTeacher eduTeacher = new EduTeacher();
        BeanUtils.copyProperties(this, eduTeacher);
        return eduTeacher;
    }
}
