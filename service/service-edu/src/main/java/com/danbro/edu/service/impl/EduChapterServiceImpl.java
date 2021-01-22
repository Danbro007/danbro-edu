package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.mapper.EduChapterMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        this.save(eduChapter);
        return eduChapter;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeChapterByChapterId(String chapterId) {
        eduVideoService.removeByChapterId(chapterId);
        this.removeById(chapterId);
    }

    @Override
    public void removeChapterAndVideoByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        this.remove(queryWrapper);
    }

    @Override
    public EduChapter insertOrUpdateChapter(EduChapter eduChapter) {
        this.saveOrUpdate(eduChapter);
        return eduChapter;
    }
}