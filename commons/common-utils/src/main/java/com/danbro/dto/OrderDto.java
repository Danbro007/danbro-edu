package com.danbro.dto;

import java.math.BigDecimal;
import com.danbro.enity.TOrder;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname OutPutOrderInsertDto
 * @Description TODO
 * @Date 2021/1/11 11:29
 * @Author Danrbo
 */
@Data
public class OrderDto implements DtoConvert<OrderDto, TOrder> {

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
    public OrderDto convertFrom(TOrder tOrder) {
        TOrderDtoConvertor convertor = new TOrderDtoConvertor();
        return convertor.doBackward(tOrder);
    }

    @Override
    public TOrder convertTo() {
        TOrderDtoConvertor convertor = new TOrderDtoConvertor();
        return convertor.doForward(this);
    }

    private static class TOrderDtoConvertor extends Converter<OrderDto, TOrder> {
        @Override
        protected TOrder doForward(OrderDto orderDto) {
            TOrder order = new TOrder();
            BeanUtils.copyProperties(orderDto, order);
            return order;
        }

        @Override
        protected OrderDto doBackward(TOrder tOrder) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(tOrder, orderDto);
            return orderDto;
        }
    }
}
