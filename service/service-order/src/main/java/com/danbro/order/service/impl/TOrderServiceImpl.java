package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.order.entity.TOrder;
import com.danbro.order.mapper.TOrderMapper;
import com.danbro.order.service.TOrderService;
import org.springframework.stereotype.Service;

/**
 * 订单(TOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
@Service("tOrderService")
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Override
    public Boolean insertOrder(UcenterMemberInfoDto userInfo, EduCourseBasicInfoDto courseInfo) {
        TOrder tOrder = TOrder.builder().
                courseId(courseInfo.getId()).
                courseTitle(courseInfo.getTitle()).
                courseCover(courseInfo.getCover()).
                teacherName(courseInfo.getTeacherName()).
                memberId(userInfo.getId()).
                nickname(userInfo.getNickname()).
                mobile(userInfo.getMobile()).
                totalFee(courseInfo.getPrice()).
                payType(1).
                status(false).build();
        return this.save(tOrder);
    }
}