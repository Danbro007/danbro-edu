package com.danbro.edu.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.entity.EduChapter;

/**
 * 课程(EduChapter)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 通过 courseId 找到相关的课程大纲
     *
     * @param courseId 课程Id
     * @return 课程大纲
     */
    List<ChapterVo> findAllByCourseId(String courseId);

    /**
     * 添加章节
     *
     * @param chapter
     * @return 新添加的章节对象
     */
    EduChapter insert(EduChapter chapter);

    /**
     * 通过章节ID删除章节及其小节
     *
     * @param chapterId 章节ID
     */
    void removeChapterByChapterId(String chapterId);

    /**
     * 通过课程ID 删除相关的章节和小节
     *
     * @param courseId 课程ID
     */
    void removeChapterAndVideoByCourseId(String courseId);

    /**
     * 添加或者更新章节信息
     *
     * @param eduChapter 要更新的章节
     * @return 更新完毕后的航姐
     */
    EduChapter insertOrUpdateChapter(EduChapter eduChapter);
}