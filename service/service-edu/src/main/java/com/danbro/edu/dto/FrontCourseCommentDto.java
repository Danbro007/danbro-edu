package com.danbro.edu.dto;

import java.util.Date;
import lombok.Data;

/**
 * @Classname FrontCourseCommentDto
 * @Description TODO 给前台用户显示的课程评论
 * @Date 2021/1/7 21:02
 * @Created by Administrator
 */
@Data
public class FrontCourseCommentDto {
    private String nickname;
    private String avatar;
    private String content;
    private Date gmtModified;
}
