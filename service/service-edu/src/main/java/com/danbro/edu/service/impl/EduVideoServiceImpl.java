package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.mapper.EduVideoMapper;
import com.danbro.edu.rpcClient.VodClient;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public Boolean removeByCourseId(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public Boolean removeAliyunVideo(String videoId) {
        EduVideo eduVideo = this.getById(videoId);
        if (!StringUtils.isEmpty(eduVideo.getVideoSourceId())) {
            Result result = vodClient.deleteVideoByVideoId(eduVideo.getVideoSourceId());
            if (ResultCode.SUCCESS.getCode().equals(result.getCode())) {
                eduVideo.setVideoOriginalName("");
                eduVideo.setVideoSourceId("");
                this.updateById(eduVideo);
                return true;
            }
        }
        return false;


    }
}