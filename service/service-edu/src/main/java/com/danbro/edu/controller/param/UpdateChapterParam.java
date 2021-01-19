package com.danbro.edu.controller.param;

import com.danbro.edu.entity.EduChapter;
import com.danbro.impl.ParamConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Classname UpdateChapterParam
 * @Description TODO
 * @Date 2021/1/19 15:40
 * @Author Danrbo
 */
@Data
@ApiModel("更新章节的参数")
public class UpdateChapterParam implements ParamConvert<EduChapter> {
    @NotBlank(message = "章节ID不能为空！")
    @ApiModelProperty("章节ID")
    @Min(value = 1, message = "章节ID不能小于0！")
    private String id;


    @NotBlank(message = "课程ID不能为空！")
    @ApiModelProperty("课程ID")
    @Min(value = 1, message = "讲师ID不能小于0！")
    private String courseId;

    @NotBlank(message = "章节名不能为空！")
    @ApiModelProperty("章节名")
    private String title;

    @Min(value = 1, message = "章节排序不能小于0！")
    @NotEmpty(message = "章节排序不能为空！")
    @ApiModelProperty("章节排序")
    private Integer sort;

    @Override
    public EduChapter convertTo() {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(this, eduChapter);
        return eduChapter;
    }
}
