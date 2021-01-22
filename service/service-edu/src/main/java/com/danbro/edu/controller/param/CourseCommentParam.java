package com.danbro.edu.controller.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsAssignID;
import com.danbro.edu.entity.EduComment;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Classname FrontInsertCourseCommentDto
 * @Description TODO 课程评论
 * @Date 2021/1/7 22:42
 * @Created by Administrator
 */
@Data
@ApiModel("课程评论参数")
@Accessors(chain = true)
public class CourseCommentParam implements ParamConvert<EduComment> {

    @IsAssignID(message = "创建评论时ID不能存在！", groups = {Insert.class})
    @Null(message = "修改评论时必须有ID！", groups = {Update.class})
    @ApiModelProperty("评论ID")
    private String id;

    @IsAssignID(message = "课程ID必须存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程ID")
    private String courseId;

    @NotBlank(message = "课程pl不能为空！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程内容")
    private String content;

    @IsAssignID(message = "讲师ID必须存在！", groups = {Insert.class, Update.class})
    private String teacherId;

    @IsAssignID(message = "会员ID必须存在！", groups = {Insert.class, Update.class})
    private String memberId;

    @NotBlank(message = "会员名必须存在！", groups = {Insert.class, Update.class})
    private String nickname;

    @NotBlank(message = "会员头像必须存在！", groups = {Insert.class, Update.class})
    private String avatar;

    @Override
    public EduComment convertTo() {
        EduComment eduComment = new EduComment();
        BeanUtils.copyProperties(this, eduComment);
        return eduComment;
    }
}
