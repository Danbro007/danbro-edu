package com.danbro.test;

import com.danbro.edu.controller.vo.ChapterVo;
import com.danbro.edu.service.EduChapterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {
    @Autowired
    EduChapterService eduChapterService;

    @Test
    public void test() {
        List<ChapterVo> allByCourseId = eduChapterService.findAllByCourseId("18");
        System.out.println(allByCourseId);
    }
}
