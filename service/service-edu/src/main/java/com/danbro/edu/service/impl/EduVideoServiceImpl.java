package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduVideo;
import com.danbro.edu.mapper.EduVideoMapper;
import com.danbro.edu.service.EduVideoService;
import org.springframework.stereotype.Service;

/**
 * 课程视频(EduVideo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduVideoService")
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
//    @Autowired
//    VodAliYunUtils vodAliYunUtils;

    @Override
    public boolean removeByCourseId(String id) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        return this.remove(queryWrapper);
    }

//    @Override
//    public boolean removeUploadVideo(String videoSourceId) {
//        try {
//            vodAliYunUtils.deleteVideo(videoSourceId);
//            QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("video_source_id", videoSourceId);
//            return this.remove(queryWrapper);
//        } catch (ClientException e) {
//            throw new MyCustomException(ResultCode.DELETE_VIDEO_FAILURE);
//        }
//    }
}