package com.danbro.edu.service.impl;

import java.util.ArrayList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.FrontCourseCommentDto;
import com.danbro.edu.dto.FrontCourseCommentPagingDto;
import com.danbro.edu.dto.FrontInsertCourseCommentDto;
import com.danbro.edu.entity.EduComment;
import com.danbro.edu.mapper.EduCommentMapper;
import com.danbro.edu.service.EduCommentService;
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
    public FrontCourseCommentPagingDto pagingGetCourseComment(String courseId, Long current, Long limit) {
        Page<EduComment> page = new Page<>(current, limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        this.page(page, queryWrapper);
        ArrayList<FrontCourseCommentDto> items = new ArrayList<>();
        page.getRecords().forEach(e -> {
            FrontCourseCommentDto commentDto = new FrontCourseCommentDto();
            BeanUtils.copyProperties(e, commentDto);
            items.add(commentDto);
        });
        return FrontCourseCommentPagingDto.builder().
                current(page.getCurrent()).
                pages(page.getPages()).
                total(page.getTotal()).
                size(page.getSize()).
                items(items).
                hasNext(page.hasNext()).
                hasPrevious(page.hasPrevious()).build();
    }

    @Override
    public Boolean insertCourseComment(FrontInsertCourseCommentDto courseCommentDto) {
        EduComment eduComment = new EduComment();
        BeanUtils.copyProperties(courseCommentDto, eduComment);
        return this.save(eduComment);
    }
}