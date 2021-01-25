package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enity.TOrder;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.order.mapper.TOrderMapper;
import com.danbro.order.service.TOrderService;
import com.danbro.order.service.TPayLogService;
import com.danbro.order.typeEnum.OrderStatus;
import com.danbro.order.typeEnum.PayType;
import com.danbro.order.utils.OrderNoUtils;
import com.danbro.vo.CourseVo;
import com.danbro.vo.MemberVo;
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
    public TOrder insertOrder(MemberVo memberVo, CourseVo courseInfo) {
        TOrder tOrder = buildOrder(memberVo, courseInfo);
        boolean success = this.save(tOrder);
        if (!success) {
            throw new EduException(ResultCode.ORDER_INSERT_FAILURE);
        }
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

    /**
     * 创建订单对象
     *
     * @param memberVo   会员信息
     * @param courseInfo 课程信息
     * @return 订单对象
     */
    private TOrder buildOrder(MemberVo memberVo, CourseVo courseInfo) {
        return TOrder.builder().
                courseId(courseInfo.getId()).
                orderNo(OrderNoUtils.getOrderNo()).
                courseTitle(courseInfo.getTitle()).
                courseCover(courseInfo.getCover()).
                teacherName(courseInfo.getTeacher().getName()).
                memberId(memberVo.getId()).
                nickname(memberVo.getNickname()).
                mobile(memberVo.getMobile()).
                totalFee(courseInfo.getPrice()).
                payType(PayType.WECHAT.getType()).
                status(OrderStatus.UN_PAID.getStatus()).build();
    }
}