package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.InPutEduChapterInsertDto;
import com.danbro.edu.dto.OutPutEduChapterDto;
import com.danbro.edu.entity.EduChapter;

import java.util.List;

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
    List<OutPutEduChapterDto> findAllByCourseId(String courseId);

    Boolean insert(InPutEduChapterInsertDto inPutEduChapterInsertDto);

    boolean removeChapterAndVideo(String chapterId);

    boolean removeByCourseId(String id);
}