package com.danbro.edu.controller.vo;

import java.io.Serializable;
import java.util.Date;
import com.danbro.edu.entity.EduComment;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname FrontCourseCommentDto
 * @Description TODO 给前台用户显示的课程评论
 * @Date 2021/1/7 21:02
 * @Created by Administrator
 */
@Data
@ApiModel("显示课程评论")
public class CourseCommentVo implements Serializable, VoConvert<CourseCommentVo, EduComment> {
    @ApiModelProperty("评论ID")
    private String id;

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("讲师ID")
    private String teacherId;

    @ApiModelProperty("会员ID")
    private String memberId;

    @ApiModelProperty("评论的会员昵称")
    private String nickname;

    @ApiModelProperty("会员头像")
    private String avatar;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论时间")
    private Date gmtModified;

    @Override
    public CourseCommentVo convertFrom(EduComment eduComment) {
        BeanUtils.copyProperties(eduComment, this);
        return this;
    }
}
