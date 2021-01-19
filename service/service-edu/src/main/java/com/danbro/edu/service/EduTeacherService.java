package com.danbro.edu.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.dto.TeacherTopDto;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.dto.FrontTeacherInfoQueryDto;
import com.danbro.edu.controller.param.QueryTeacherParam;
import com.danbro.edu.controller.vo.TeacherVo;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.enity.OutPutPagingDto;

/**
 * @Classname EduTeacherService
 * @Description TODO
 * @Date 2020/12/14 15:13
 * @Author Danrbo
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 分页查询讲师
     *
     * @param current           当前页
     * @param limit             每页显示的数量
     * @param queryTeacherParam 查询讲师的参数
     * @return 分页结果
     */
    OutPutPagingDto<TeacherVo> pagingFindTeacherByCondition(Integer current, Integer limit, QueryTeacherParam queryTeacherParam);

    /**
     * 查询排名前 limit 的讲师
     *
     * @param limit 依照讲师 level 排名
     * @return 排名前 limit 的讲师列表
     */
    List<TeacherTopDto> getTopTeacherList(String limit);

    /**
     * 分页查询讲师
     *
     * @param current 当前页
     * @param limit   每页显示的数量
     * @return 分页结果
     */
    FrontPagingDto<EduTeacher> pagingFindTeacher(Integer current, Integer limit);

    /**
     * 通过讲师ID查询讲师信息（讲师教的所有课程）
     *
     * @param id 讲师ID
     * @return 讲师信息
     */
    FrontTeacherInfoQueryDto getTeacherInfoById(String id);

    /**
     * 更新讲师信息
     *
     * @param teacher 要更新的讲师信息
     * @return 更新完毕后的讲师信息
     */
    EduTeacher insertOrUpdateTeacher(EduTeacher teacher);

}
