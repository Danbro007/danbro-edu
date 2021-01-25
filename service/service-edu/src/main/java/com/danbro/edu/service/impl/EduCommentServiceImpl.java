package com.danbro.edu.service.impl;

import java.util.ArrayList;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.controller.vo.CourseCommentVo;
import com.danbro.edu.controller.param.CourseCommentParam;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.entity.EduComment;
import com.danbro.edu.mapper.EduCommentMapper;
import com.danbro.edu.service.EduCommentService;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 评论(EduComment)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCommentService")
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public FrontPagingDto<CourseCommentVo> pagingGetCourseComment(String courseId, Long current, Long limit) {
        Page<EduComment> page = new Page<>(current, limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByDesc("gmt_create");
        this.page(page, queryWrapper);
        ArrayList<CourseCommentVo> items = new ArrayList<>();
        page.getRecords().forEach(e -> items.add(new CourseCommentVo().convertFrom(e)));
        return new FrontPagingDto<CourseCommentVo>().
                setCurrent(page.getCurrent()).
                setPages(page.getPages()).
                setTotal(page.getTotal()).
                setSize(page.getSize()).
                setItems(items).
                setHasNext(page.hasNext()).
                setHasPrevious(page.hasPrevious());
    }

    @Override
    public EduComment insertOrUpdateCourseComment(EduComment comment) {
        boolean success = this.save(comment);
        if (!success) {
            throw new EduException(ResultCode.COMMENT_INSERT_OR_UPDATE_FAILURE);
        }
        return comment;
    }
}