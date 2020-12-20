package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.entity.EduSubject;
import com.danbro.edu.dto.FirstSubjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 课程科目(EduSubject)表服务接口
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */
public interface EduSubjectService extends IService<EduSubject> {

    void insert(MultipartFile file) throws IOException;

    List<FirstSubjectDto> getAllSubject();
}