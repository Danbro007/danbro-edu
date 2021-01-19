package com.danbro.edu.controller.vo;

import com.danbro.edu.entity.EduVideo;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname VideoVo
 * @Description TODO 返回的小节信息
 * @Date 2021/1/19 14:47
 * @Author Danrbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoVo implements Serializable, VoConvert<VideoVo, EduVideo> {

    private static final long serialVersionUID = 5837111834978084302L;

    @ApiModelProperty("小节ID")
    private String id;

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("章节ID")
    private String chapterId;

    @ApiModelProperty("小节名")
    private String title;

    @ApiModelProperty("阿里云视频ID")
    private String videoSourceId;

    @ApiModelProperty("小节里的视频名")
    private String videoOriginalName;

    @ApiModelProperty("小节排序")
    private Integer sort;

    @ApiModelProperty("视频播放次数")
    private Long playCount;

    @ApiModelProperty("视频是否免费")
    private Boolean isFree;

    @ApiModelProperty("视频长度")
    private Long duration;

    @ApiModelProperty("视频的状态：转码 OR 上传中")
    private String status;

    @ApiModelProperty("视频文件大小")
    private Long size;

    @ApiModelProperty("小节的创建时间")
    private Date gmtCreate;

    @ApiModelProperty("小节的更新时间")
    private Date gmtModified;

    @Override
    public VideoVo convertFrom(EduVideo eduVideo) {
        BeanUtils.copyProperties(eduVideo, this);
        return this;
    }
}
