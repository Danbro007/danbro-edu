package com.danbro.edu.service.impl;

import java.util.ArrayList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.FrontCourseCommentDto;
import com.danbro.edu.dto.FrontInPutCommentInsertDto;
import com.danbro.edu.dto.FrontPagingDto;
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
    public FrontPagingDto<FrontCourseCommentDto> pagingGetCourseComment(String courseId, Long current, Long limit) {
        Page<EduComment> page = new Page<>(current, limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.orderByDesc("gmt_create");
        this.page(page, queryWrapper);
        ArrayList<FrontCourseCommentDto> items = new ArrayList<>();
        page.getRecords().forEach(e -> {
            FrontCourseCommentDto commentDto = new FrontCourseCommentDto();
            BeanUtils.copyProperties(e, commentDto);
            items.add(commentDto);
        });
        return new FrontPagingDto<FrontCourseCommentDto>().
                setCurrent(page.getCurrent()).
                setPages(page.getPages()).
                setTotal(page.getTotal()).
                setSize(page.getSize()).
                setItems(items).
                setHasNext(page.hasNext()).
                setHasPrevious(page.hasPrevious());
    }

    @Override
    public Boolean insertCourseComment(FrontInPutCommentInsertDto courseCommentDto) {
        EduComment eduComment = new EduComment();
        BeanUtils.copyProperties(courseCommentDto, eduComment);
        return this.save(eduComment);
    }
}