package com.danbro.edu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.edu.entity.EduSubject;
import com.danbro.edu.service.EduSubjectService;
import org.springframework.util.StringUtils;

/**
 * @Classname SubjectExcelListener
 * @Description TODO 课程上传的监听器
 * @Date 2020/12/17 21:29
 * @Author Danrbo
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;
    private final static String FIRST_SUBJECT_PARENT_ID = "0";

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    /**
     * 先判断数据库里有没有相同的课程分类，没的话再存入数据库。
     *
     * @param subjectData     从 Excel 读取的数据
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        String firstSubjectName = subjectData.getFirstSubject();
        EduSubject firstSubject = isSubjectExist(firstSubjectName, eduSubjectService, FIRST_SUBJECT_PARENT_ID);
        if (firstSubject == null) {
            firstSubject = new EduSubject();
            firstSubject.setParentId("0").setTitle(firstSubjectName);
            eduSubjectService.save(firstSubject);
        }
        String secondSubjectName = subjectData.getSecondSubject();
        EduSubject secondSubject = isSubjectExist(secondSubjectName, eduSubjectService, firstSubject.getId());
        if (secondSubject == null) {
            secondSubject = new EduSubject();
            secondSubject.setParentId(firstSubject.getId()).setTitle(secondSubjectName);
            eduSubjectService.save(secondSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    private EduSubject isSubjectExist(String subjectName, EduSubjectService eduSubjectService, String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId).eq("title", subjectName);
        return eduSubjectService.getOne(queryWrapper);
    }
}
