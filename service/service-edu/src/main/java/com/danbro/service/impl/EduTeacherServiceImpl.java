package com.danbro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.EduTeacherQueryDto;
import com.danbro.entity.EduTeacher;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.mapper.EduTeacherMapper;
import com.danbro.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Classname EduTeacherServiceImpl
 * @Description TODO EduTeacherService 实现类
 * @Date 2020/12/14 15:12
 * @Author Danrbo
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Result pagingFindTeacherByCondition(Integer current, Integer limit, EduTeacherQueryDto eduTeacherQueryDto) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        Date end = eduTeacherQueryDto.getEnd();
        Date start = eduTeacherQueryDto.getStart();
        Integer level = eduTeacherQueryDto.getLevel();
        String name = eduTeacherQueryDto.getName();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(start)) {
            queryWrapper.gt("gmt_create", start);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.lt("gmt_create", end);
        }
        // 按照 gmt_create 排序
        queryWrapper.orderByDesc("gmt_create");
        this.page(eduTeacherPage, queryWrapper);
        if (eduTeacherPage.getTotal() > 0) {
            return Result.successOf(ResultCode.SUCCESS).
                    setDataChain("total", eduTeacherPage.getTotal()).
                    setDataChain("rows", eduTeacherPage.getRecords());
        }
        throw new MyCustomException(ResultCode.MATCH_CONDITION_TEACHER_NOT_FOUND);
    }
}