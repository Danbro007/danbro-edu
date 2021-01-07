package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.FrontCourseCommentPagingDto;
import com.danbro.edu.dto.FrontInsertCourseCommentDto;
import com.danbro.edu.entity.EduComment;

/**
 * 评论(EduComment)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCommentService extends IService<EduComment> {
    FrontCourseCommentPagingDto pagingGetCourseComment(String courseId, Long current, Long limit);

    Boolean insertCourseComment(FrontInsertCourseCommentDto courseCommentDto);
}