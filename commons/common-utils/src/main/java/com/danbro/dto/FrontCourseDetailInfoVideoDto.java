package com.danbro.dto;

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
    private String videoTitle;
    private String videoSourceId;
    private Boolean isFree;

}
