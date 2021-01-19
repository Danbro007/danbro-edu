package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.controller.dto.FrontCourseCommentDto;
import com.danbro.edu.controller.dto.FrontInPutCommentInsertDto;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.entity.EduComment;

/**
 * 评论(EduComment)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCommentService extends IService<EduComment> {
    FrontPagingDto<FrontCourseCommentDto> pagingGetCourseComment(String courseId, Long current, Long limit);

    Boolean insertCourseComment(FrontInPutCommentInsertDto courseCommentDto);
}