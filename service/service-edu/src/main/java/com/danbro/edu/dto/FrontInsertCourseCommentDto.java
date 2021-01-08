package com.danbro.edu.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FrontInsertCourseCommentDto
 * @Description TODO 添加课程评论
 * @Date 2021/1/7 22:42
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
@Builder
public class FrontInsertCourseCommentDto {
    private String courseId;
    private String content;
    private String teacherId;
    private String memberId;
    private String nickname;
    private String avatar;
}
