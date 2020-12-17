package com.danbro.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduSubject;
import com.danbro.edu.excel.SubjectData;
import com.danbro.edu.excel.SubjectExcelListener;
import com.danbro.edu.mapper.EduSubjectMapper;
import com.danbro.edu.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 课程科目(EduSubject)表服务实现类
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void insert(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();


    }
}