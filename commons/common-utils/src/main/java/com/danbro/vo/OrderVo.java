package com.danbro.vo;

import java.math.BigDecimal;
import com.danbro.enity.TOrder;
import com.danbro.impl.VoConvert;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname OutPutOrderInsertDto
 * @Description TODO
 * @Date 2021/1/11 11:29
 * @Author Danrbo
 */
@Data
public class OrderVo implements VoConvert<OrderVo, TOrder> {

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

    @Override
    public OrderVo convertFrom(TOrder tOrder) {
        BeanUtils.copyProperties(tOrder, this);
        return this;
    }
}