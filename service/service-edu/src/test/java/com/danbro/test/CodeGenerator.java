package com.danbro.test;

import com.danbro.edu.dto.EduCoursePublishDto;
import com.danbro.edu.mapper.EduCourseMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @since 2018/12/13
 */
public class CodeGenerator {

    @Autowired
    EduCourseMapper eduCourseMapper;
    @Test
    public void run() {
        EduCoursePublishDto courseInfoForPublish = eduCourseMapper.getCourseInfoForPublish("1342070405946433538");
        System.out.println(courseInfoForPublish);
    }
}
