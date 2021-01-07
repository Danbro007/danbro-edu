package com.danbro.edu.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Classname FrontCourseDetailInfoVideoDto
 * @Description TODO
 * @Date 2021/1/7 14:25
 * @Author Danrbo
 */
@Data
public class FrontCourseDetailInfoVideoDto {
    private String videoId;
    private Boolean videoIsFree;
    private Long videoDuration;
    private Long videoPlayCount;
    private Long videoSize;
    private String videoTitle;
    private String videoOriginalName;
    private String videoSourceId;
}
