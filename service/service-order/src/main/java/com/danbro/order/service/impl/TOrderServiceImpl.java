package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enity.TOrder;
import com.danbro.order.mapper.TOrderMapper;
import com.danbro.order.service.TOrderService;
import com.danbro.order.service.TPayLogService;
import com.danbro.order.typeEnum.OrderStatus;
import com.danbro.order.typeEnum.PayType;
import com.danbro.order.utils.OrderNoUtils;
import com.danbro.vo.CourseVo;
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
    public TOrder insertOrder(UcenterMemberInfoDto userInfo, CourseVo courseInfo) {
        TOrder tOrder = TOrder.builder().
                courseId(courseInfo.getId()).
                orderNo(OrderNoUtils.getOrderNo()).
                courseTitle(courseInfo.getTitle()).
                courseCover(courseInfo.getCover()).
                teacherName(courseInfo.getTeacher().getName()).
                memberId(userInfo.getId()).
                nickname(userInfo.getNickname()).
                mobile(userInfo.getMobile()).
                totalFee(courseInfo.getPrice()).
                payType(PayType.WECHAT.getType()).
                status(OrderStatus.UN_PAID.getStatus()).build();
        this.save(tOrder);
        return tOrder;
    }

    @Override
    public TOrder getOrderByOrderNo(String orderNo) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        return this.getOne(queryWrapper);
    }

    @Override
    public TOrder getOrderByUserIdAndCourseId(String userId, String courseId) {
        QueryWrapper<TOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", userId);
        queryWrapper.eq("course_id", courseId);
        return this.getOne(queryWrapper);
    }
}