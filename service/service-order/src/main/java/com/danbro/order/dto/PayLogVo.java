package com.danbro.order.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.danbro.impl.VoConvert;
import com.danbro.order.entity.TPayLog;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname PayLogDto
 * @Description TODO
 * @Date 2021/1/11 21:29
 * @Created by Administrator
 */
@Data
public class PayLogVo implements VoConvert<PayLogVo, TPayLog> {
    private String id;

    private String orderNo;

    private Date payTime;

    private BigDecimal totalFee;

    private String transactionId;

    private String tradeState;

    private Integer payType;

    private String attr;

    @Override
    public PayLogVo convertFrom(TPayLog payLog) {
        BeanUtils.copyProperties(payLog, this);
        return this;
    }

}
