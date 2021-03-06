package com.danbro.edu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.controller.vo.FirstSubjectVo;
import com.danbro.edu.controller.vo.SecondSubjectVo;
import com.danbro.edu.entity.EduSubject;
import com.danbro.edu.excel.SubjectData;
import com.danbro.edu.excel.SubjectExcelListener;
import com.danbro.edu.mapper.EduSubjectMapper;
import com.danbro.edu.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程科目(EduSubject)表服务实现类
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubject(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
    }

    @Override
    public List<FirstSubjectVo> getAllSubject() {
        ArrayList<FirstSubjectVo> subjects = new ArrayList<>();
        QueryWrapper<EduSubject> firstSubjectWrapper = new QueryWrapper<>();
        firstSubjectWrapper.eq("parent_id", "0");
        // 先找到一级课程
        List<EduSubject> firstSubjectList = this.list(firstSubjectWrapper);
        if (firstSubjectList != null && firstSubjectList.size() > 0) {
            firstSubjectList.forEach(e -> subjects.add(new FirstSubjectVo().convertFrom(e)));
        }
        // 再找二级课程
        QueryWrapper<EduSubject> secondSubjectWrapper = new QueryWrapper<>();
        secondSubjectWrapper.ne("parent_id", "0");
        List<EduSubject> secondSubjectList = list(secondSubjectWrapper);
        if (secondSubjectList != null && secondSubjectList.size() > 0) {
            subjects.forEach(m -> secondSubjectList.forEach(n -> {
                if (m.getId().equals(n.getParentId())) {
                    m.getChildren().add(new SecondSubjectVo().convertFrom(n));
                }
            }));
        }
        return subjects;
    }
}