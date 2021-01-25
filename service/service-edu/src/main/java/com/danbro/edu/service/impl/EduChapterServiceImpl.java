package com.danbro.edu.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.mapper.EduChapterMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 课程(EduChapter)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduChapterService")
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> findAllByCourseId(String courseId) {
        return this.baseMapper.findChapterByCourseId(courseId);
    }

    @Override
    public EduChapter insert(EduChapter eduChapter) {
        boolean success = this.save(eduChapter);
        if (!success) {
            throw new EduException(ResultCode.CHAPTER_INSERT_OR_UPDATE_FAILURE);
        }
        return eduChapter;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeChapterByChapterId(String chapterId) {
        // 删除章节
        eduVideoService.removeByChapterId(chapterId);
        // 删除课程
        boolean success = this.removeById(chapterId);
        if (!success) {
            throw new EduException(ResultCode.CHAPTER_DELETE_FAILURE);
        }
    }

    @Override
    public void removeChapterAndVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        boolean success = this.remove(queryWrapper);
        if (!success) {
            throw new EduException(ResultCode.CHAPTER_DELETE_FAILURE);
        }
    }

    @Override
    public EduChapter insertOrUpdateChapter(EduChapter eduChapter) {
        boolean success = this.saveOrUpdate(eduChapter);
        if (!success) {
            throw new EduException(ResultCode.CHAPTER_INSERT_OR_UPDATE_FAILURE);
        }
        return eduChapter;
    }
}