package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.order.dto.OutPutOrderInsertDto;
import com.danbro.order.entity.TOrder;
import com.danbro.order.entity.TPayLog;
import com.danbro.order.mapper.TOrderMapper;
import com.danbro.order.service.TOrderService;
import com.danbro.order.service.TPayLogService;
import com.danbro.order.typeEnum.OrderStatus;
import com.danbro.order.typeEnum.PayType;
import com.danbro.order.typeEnum.TradeState;
import com.danbro.order.utils.OrderNoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单(TOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
@Service("tOrderService")
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private TPayLogService tPayLogService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OutPutOrderInsertDto insertOrder(UcenterMemberInfoDto userInfo, EduCourseBasicInfoDto courseInfo) {
        TOrder tOrder = TOrder.builder().
                courseId(courseInfo.getId()).
                orderNo(OrderNoUtils.getOrderNo()).
                courseTitle(courseInfo.getTitle()).
                courseCover(courseInfo.getCover()).
                teacherName(courseInfo.getTeacherName()).
                memberId(userInfo.getId()).
                nickname(userInfo.getNickname()).
                mobile(userInfo.getMobile()).
                totalFee(courseInfo.getPrice()).
                payType(PayType.WECHAT.getType()).
                status(OrderStatus.UN_PAID.getStatus()).build();
        this.save(tOrder);
        TPayLog log = TPayLog.builder()
                .id(tOrder.getId())
                .payType(tOrder.getPayType())
                .orderNo(tOrder.getOrderNo())
                .totalFee(tOrder.getTotalFee())
                .tradeState(TradeState.SUCCESS.getStatus())
                .build();
        tPayLogService.save(log);
        OutPutOrderInsertDto outPutOrderInsertDto = new OutPutOrderInsertDto();
        BeanUtils.copyProperties(tOrder, outPutOrderInsertDto);
        return outPutOrderInsertDto;
    }
}