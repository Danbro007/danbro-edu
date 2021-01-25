package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.mapper.EduVideoMapper;
import com.danbro.edu.rpcClient.VodClient;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 课程视频(EduVideo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduVideoService")
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    VodClient vodClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByCourseId(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        List<EduVideo> eduVideos = this.list(queryWrapper);
        ArrayList<String> videoList = new ArrayList<>();
        eduVideos.forEach(e -> videoList.add(e.getVideoSourceId()));
        // 远程调用 vod 服务删除阿里云的视频
        Result result = vodClient.batchDeleteVideo(videoList);
        if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
            boolean success = this.remove(queryWrapper);
            if (!success) {
                throw new EduException(ResultCode.VIDEO_DELETE_FAILURE);
            }
        } else {
            System.out.println(result);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeAliyunVideo(String videoId) {
        EduVideo eduVideo = this.getById(videoId);
        if (eduVideo != null && !StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            Result result = vodClient.deleteVideoByVideoId(eduVideo.getVideoSourceId());
            if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
                eduVideo.setVideoOriginalName("");
                eduVideo.setVideoSourceId("");
                boolean success = this.updateById(eduVideo);
                if (!success) {
                    throw new EduException(ResultCode.VIDEO_DELETE_FAILURE);
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByChapterId(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        List<EduVideo> eduVideos = this.list(queryWrapper);
        ArrayList<String> videoList = new ArrayList<>();
        eduVideos.forEach(e -> videoList.add(e.getVideoSourceId()));
        Result result = vodClient.batchDeleteVideo(videoList);
        if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
            boolean success = this.remove(queryWrapper);
            if (!success) {
                throw new EduException(ResultCode.VIDEO_DELETE_FAILURE);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeByVideoId(String videoId) {
        EduVideo eduVideo = this.getById(videoId);
        Result result = vodClient.deleteVideoByVideoId(eduVideo.getVideoSourceId());
        if (result.getCode().equals(ResultCode.SUCCESS.getCode())) {
            boolean success = this.removeById(videoId);
            if (!success) {
                throw new EduException(ResultCode.VIDEO_DELETE_FAILURE);
            }
        }
    }

    @Override
    public EduVideo insertOrUpdateVideo(EduVideo eduVideo) {
        boolean success = this.saveOrUpdate(eduVideo);
        if (!success) {
            throw new EduException(ResultCode.VIDEO_INSERT_OR_UPDATE_FAILURE);
        }
        return eduVideo;
    }
}