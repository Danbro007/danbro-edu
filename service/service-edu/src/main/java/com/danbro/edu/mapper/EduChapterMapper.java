package com.danbro.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.entity.EduChapter;

import java.util.List;

/**
 * @Classname EduChapterMapper
 * @Description TODO
 * @Date 2020/12/20 20:05
 * @Author Danrbo
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    /**
     * 通过课程 ID 找到所有的章节及小节
     *
     * @param courseId 课程ID
     * @return 所有的章节
     */
    List<ChapterVo> findChapterByCourseId(String courseId);
}
