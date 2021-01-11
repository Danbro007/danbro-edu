package com.danbro.order.dto;

import com.danbro.impl.DtoConvert;
import com.danbro.order.entity.TOrder;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @Classname OutPutOrderInsertDto
 * @Description TODO
 * @Date 2021/1/11 11:29
 * @Author Danrbo
 */
@Data
public class OutPutOrderInsertDto implements DtoConvert<OutPutOrderInsertDto, TOrder> {

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
    public OutPutOrderInsertDto convertFrom(TOrder tOrder) {
        TOrderDtoConvertor convertor = new TOrderDtoConvertor();
        return convertor.doBackward(tOrder);
    }

    @Override
    public TOrder convertTo() {
        TOrderDtoConvertor convertor = new TOrderDtoConvertor();
        return convertor.doForward(this);
    }

    private static class TOrderDtoConvertor extends Converter<OutPutOrderInsertDto, TOrder> {
        @Override
        protected TOrder doForward(OutPutOrderInsertDto outPutOrderInsertDto) {
            TOrder order = new TOrder();
            BeanUtils.copyProperties(outPutOrderInsertDto, order);
            return order;
        }

        @Override
        protected OutPutOrderInsertDto doBackward(TOrder tOrder) {
            OutPutOrderInsertDto outPutOrderInsertDto = new OutPutOrderInsertDto();
            BeanUtils.copyProperties(tOrder, outPutOrderInsertDto);
            return outPutOrderInsertDto;
        }
    }
}
