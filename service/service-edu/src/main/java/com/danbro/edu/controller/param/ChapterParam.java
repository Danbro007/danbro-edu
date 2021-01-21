package com.danbro.edu.controller.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.edu.entity.EduChapter;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname InsertChapterParam
 * @Description TODO 添加或更新章节的参数
 * @Date 2021/1/19 15:25
 * @Author Danrbo
 */
@ApiModel("添加或更新章节的参数")
@Data
public class ChapterParam implements ParamConvert<EduChapter> {
    @IsAssignID(message = "更新时章节ID必须存在并且大于0！", groups = {Update.class})
    @Null(message = "创建时章节ID不能存在！", groups = {Insert.class})
    private String id;

    @ApiModelProperty("课程ID")
    @IsAssignID(message = "更新时课程ID必须存在并且大于0！", groups = {Insert.class, Update.class})
    private String courseId;

    @NotBlank(message = "章节名不能为空！", groups = {Insert.class, Update.class})
    @ApiModelProperty("章节名")
    private String title;

    @IsPositiveNum(message = "章节排序必须大于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("章节排序")
    private String sort;

    @Override
    public EduChapter convertTo() {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(this, eduChapter);
        eduChapter.setSort(Integer.parseInt(this.getSort()));
        return eduChapter;
    }
}
