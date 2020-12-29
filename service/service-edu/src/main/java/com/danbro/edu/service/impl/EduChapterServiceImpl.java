package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.EduChapterDeleteInPutDto;
import com.danbro.edu.dto.EduChapterInsertInPutDto;
import com.danbro.edu.dto.EduChapterOutPutDto;
import com.danbro.edu.dto.EduVideoOutPutDto;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.mapper.EduChapterMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<EduChapterOutPutDto> findAllByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = this.list(chapterQueryWrapper);
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);
        ArrayList<EduChapterOutPutDto> eduChapterOutPutDtoList = new ArrayList<>();
        chapterList.forEach(m -> {
            EduChapterOutPutDto outPutDto = new EduChapterOutPutDto()
                    .setLabel(m.getTitle())
                    .setId(m.getId())
                    .setSort(m.getSort());
            eduVideoList.forEach(n -> {
                if (n.getChapterId().equals(m.getId())) {
                    EduVideoOutPutDto videoOutPutDto = new EduVideoOutPutDto()
                            .setId(n.getId())
                            .setLabel(n.getTitle())
                            .setSort(n.getSort())
                            .setIsFree(n.getIsFree());
                    outPutDto.getChildren().add(videoOutPutDto);
                }
            });
            eduChapterOutPutDtoList.add(outPutDto);
        });
        return eduChapterOutPutDtoList;
    }

    @Override
    public Boolean insert(EduChapterInsertInPutDto eduChapterInsertInPutDto) {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(eduChapterInsertInPutDto, eduChapter);
        return this.save(eduChapter);
    }

    @Override
    public boolean removeChapterAndVideo(EduChapterDeleteInPutDto eduChapterDeleteInPutDto) {
        return this.removeById(eduChapterDeleteInPutDto.getId()) &&
                eduVideoService.removeByIds(eduChapterDeleteInPutDto.getChildren());
    }
}