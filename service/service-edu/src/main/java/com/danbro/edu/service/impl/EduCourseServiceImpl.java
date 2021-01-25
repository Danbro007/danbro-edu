package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.vo.FrontCourseDetailInfoVo;
import com.danbro.edu.controller.param.FrontQueryCourseParam;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.param.CourseParam;
import com.danbro.edu.controller.param.QueryCourseParam;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.vo.CourseVo;
import com.danbro.enity.EduCourse;
import com.danbro.enity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduCourseDescriptionService;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.edu.service.EduVideoService;
import com.danbro.edu.utils.SortType;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 课程(EduCourse)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCourseService")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduTeacherService eduTeacherService;

    @Override
    public EduCourse insert(CourseParam courseParam) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseParam, eduCourse);
        boolean courseSuccess = this.save(eduCourse);
        if (!courseSuccess) {
            throw new EduException(ResultCode.COURSE_INSERT_OR_UPDATE_FAILURE);
        }
        eduCourseDescription.
                setDescription(courseParam.getDescription()).
                setId(eduCourse.getId());
        boolean courseDescriptionSuccess = eduCourseDescriptionService.save(eduCourseDescription);
        if (!courseDescriptionSuccess) {
            throw new EduException(ResultCode.COURSE_INSERT_OR_UPDATE_FAILURE);
        }
        return eduCourse;
    }

    @Override
    public CourseVo getCourseBasicInfo(String courseId) {
        return this.baseMapper.getCourseBasicInfoByCourseId(courseId);
    }


    @Override
    public CoursePublishVo getCourseInfoForPublish(String courseId) {
        return baseMapper.getCourseInfoForPublish(courseId);
    }

    @Override
    public OutPutPagingDto<EduCourse> pagingFindByCondition(Integer current, Integer limit, QueryCourseParam conditionDto) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (conditionDto != null) {
            if (!StringUtils.isEmpty(conditionDto.getStatus())) {
                queryWrapper.eq("status", conditionDto.getStatus());
            }
            if (!StringUtils.isEmpty(conditionDto.getTitle())) {
                queryWrapper.like("title", conditionDto.getTitle());
            }
        }
        queryWrapper.orderByDesc("gmt_create");
        return new OutPutPagingDto<EduCourse>()
                .setTotal(page.getTotal())
                .setRows(page.getRecords());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeCourse(String id) {
        eduChapterService.removeChapterAndVideoByCourseId(id);
        boolean success = this.removeById(id);
        if (!success) {
            throw new EduException(ResultCode.COURSE_DELETE_FAILURE);
        }
    }

    @Cacheable(value = "course", key = "'top-course-list'")
    @Override
    public List<CourseVo> getTopCourseList(String limit) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last(String.format("limit %s", limit));
        List<CourseVo> topList = new ArrayList<>();
        this.list(queryWrapper).forEach(e -> topList.add(new CourseVo().convertFrom(e)));
        return topList;
    }

    @Override
    public FrontPagingDto<CourseVo> pagingFindCourseByCondition(Long current, Long limit, FrontQueryCourseParam courseParam) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseParam.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseParam.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseParam.getSubjectId())) {
            queryWrapper.eq("subject_id", courseParam.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseParam.getSortType())) {
            if ((courseParam.getSortType().equals(SortType.SALE_SORT.getType()) ||
                    courseParam.getSortType().equals(SortType.PRICE_SORT.getType()) ||
                    courseParam.getSortType().equals(SortType.CREATE_TIME_SORT.getType()))) {
                queryWrapper.orderByDesc(courseParam.getSortType());
            }
        }
        this.page(page, queryWrapper);
        ArrayList<CourseVo> courseVos = new ArrayList<>();
        page.getRecords().forEach(e -> courseVos.add(new CourseVo().convertFrom(e)));
        return new FrontPagingDto<CourseVo>().
                setPages(page.getPages()).
                setCurrent(page.getCurrent()).
                setSize(page.getSize()).
                setHasPrevious(page.hasPrevious()).
                setHasNext(page.hasNext()).
                setItems(courseVos).
                setTotal(page.getTotal());
    }

    @Override
    public FrontCourseDetailInfoVo getCourseDetailInfo(String courseId) {
        return this.baseMapper.getCourseDetailInfo(courseId);
    }

    @Override
    public EduCourse insertOrUpdateCourse(EduCourse eduCourse) {
        boolean success = this.saveOrUpdate(eduCourse);
        if (!success) {
            throw new EduException(ResultCode.COURSE_INSERT_OR_UPDATE_FAILURE);
        }
        return eduCourse;
    }
}