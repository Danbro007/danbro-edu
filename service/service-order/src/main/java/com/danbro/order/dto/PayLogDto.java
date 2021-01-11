package com.danbro.order.dto;

import java.math.BigDecimal;
import java.util.Date;
import com.danbro.impl.DtoConvert;
import com.danbro.order.entity.TPayLog;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname PayLogDto
 * @Description TODO
 * @Date 2021/1/11 21:29
 * @Created by Administrator
 */
@Data
public class PayLogDto implements DtoConvert<PayLogDto, TPayLog> {
    private String id;

    private String orderNo;

    private Date payTime;

    private BigDecimal totalFee;

    private String transactionId;

    private String tradeState;

    private Integer payType;

    private String attr;

    @Override
    public PayLogDto convertFrom(TPayLog payLog) {
        TPayLogDtoConvertor convertor = new TPayLogDtoConvertor();
        return convertor.doBackward(payLog);
    }

    @Override
    public TPayLog convertTo() {
        TPayLogDtoConvertor convertor = new TPayLogDtoConvertor();
        return convertor.doForward(this);
    }

    private static class TPayLogDtoConvertor extends Converter<PayLogDto, TPayLog> {
        @Override
        protected TPayLog doForward(PayLogDto payLogDto) {
            TPayLog payLog = new TPayLog();
            BeanUtils.copyProperties(payLogDto, payLog);
            return payLog;
        }

        @Override
        protected PayLogDto doBackward(TPayLog payLog) {
            PayLogDto payLogDto = new PayLogDto();
            BeanUtils.copyProperties(payLog, payLogDto);
            return payLogDto;
        }
    }
}
