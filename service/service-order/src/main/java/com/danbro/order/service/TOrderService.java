package com.danbro.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.OrderDto;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enity.TOrder;

/**
 * 订单(TOrder)表服务接口
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
public interface TOrderService extends IService<TOrder> {

    /**
     * 创建订单
     *
     * @param userInfo   用户信息
     * @param courseInfo 课程信息
     * @return 订单对象
     */
    OrderDto insertOrder(UcenterMemberInfoDto userInfo, EduCourseBasicInfoDto courseInfo);

}