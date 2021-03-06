package com.danbro.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.vo.TeacherVo;
import com.danbro.enity.EduTeacher;

/**
 * 讲师(EduTeacher)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    TeacherVo getTeacherInfoById(String id);

}