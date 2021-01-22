package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.vo.CourseCommentVo;
import com.danbro.edu.entity.EduComment;

/**
 * 评论(EduComment)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCommentService extends IService<EduComment> {
    /**
     * 返回课程的评论
     * @param courseId 课程ID
     * @param current 当前页
     * @param limit 每页显示的评论数
     * @return 分页评论
     */
    FrontPagingDto<CourseCommentVo> pagingGetCourseComment(String courseId, Long current, Long limit);

    /**
     * 添加或修改课程评论
     * @param comment 添加会修改的评论
     * @return 添加或修改完毕后的评论
     */
    CourseCommentVo insertOrUpdateCourseComment(EduComment comment);
}