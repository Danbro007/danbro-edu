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

    /**
     * 获取所有的课程分类（一级分类和二级分类）树形结构
     *
     * @return 课程分类列表
     */
    List<FirstSubjectVo> getAllSubject();

    /**
     * 通过excel表格导入课程
     *
     * @param file excel 文件
     * @throws IOException 文件IO异常
     */
    void importSubject(MultipartFile file) throws IOException;
}