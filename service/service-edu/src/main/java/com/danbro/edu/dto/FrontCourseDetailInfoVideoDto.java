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
    private String videoTitle;
    private String videoSourceId;

}
