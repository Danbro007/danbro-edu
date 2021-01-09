package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.InPutEduChapterInsertDto;
import com.danbro.edu.dto.OutPutEduChapterDto;
import com.danbro.edu.dto.OutPutEduVideoDto;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.mapper.EduChapterMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
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
    public List<OutPutEduChapterDto> findAllByCourseId(String courseId) {
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = this.list(chapterQueryWrapper);
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);
        ArrayList<OutPutEduChapterDto> outPutEduChapterDtoList = new ArrayList<>();
        chapterList.forEach(m -> {
            OutPutEduChapterDto outPutDto = new OutPutEduChapterDto()
                    .setLabel(m.getTitle())
                    .setId(m.getId())
                    .setSort(m.getSort());
            eduVideoList.forEach(n -> {
                if (n.getChapterId().equals(m.getId())) {
                    OutPutEduVideoDto videoOutPutDto = new OutPutEduVideoDto();
                    BeanUtils.copyProperties(n, videoOutPutDto);
                    videoOutPutDto.setLabel(n.getTitle());
                    outPutDto.getChildren().add(videoOutPutDto);
                }
            });
            outPutEduChapterDtoList.add(outPutDto);
        });
        return outPutEduChapterDtoList;
    }

    @Override
    public Boolean insert(InPutEduChapterInsertDto inPutEduChapterInsertDto) {
        EduChapter eduChapter = new EduChapter();
        BeanUtils.copyProperties(inPutEduChapterInsertDto, eduChapter);
        return this.save(eduChapter);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterAndVideo(String chapterId) {
        if (eduVideoService.removeByChapterId(chapterId) && this.removeById(chapterId)) {
            return true;
        }
        throw new MyCustomException(ResultCode.DELETE_CHAPTER_FAILURE);
    }

    @Override
    public boolean removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        return this.remove(queryWrapper);
    }
}