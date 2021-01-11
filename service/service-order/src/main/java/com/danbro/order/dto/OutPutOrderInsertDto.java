package com.danbro.order.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname OutPutOrderInsertDto
 * @Description TODO
 * @Date 2021/1/11 11:29
 * @Author Danrbo
 */
@Data
public class OutPutOrderInsertDto {

    private String id;
    private String orderNo;
    private String courseId;
    private String courseTitle;
    private String courseCover;
    private String teacherName;
    private String memberId;
    private String nickname;
    private String mobile;
    private BigDecimal totalFee;
    private Integer payType;
    private Boolean status;
}
