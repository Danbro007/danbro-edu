package com.danbro.edu.service;

import java.io.IOException;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.controller.vo.FirstSubjectVo;
import com.danbro.edu.entity.EduSubject;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程科目(EduSubject)表服务接口
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */
public interface EduSubjectService extends IService<EduSubject> {

    void insert(MultipartFile file) throws IOException;

    List<FirstSubjectVo> getAllSubject();
}