package com.danbro.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.dto.UcenterMemberInfoDto;
import com.danbro.enity.TOrder;
import com.danbro.vo.CourseVo;

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
     * @param courseVo 课程信息
     * @return 订单对象
     */
    TOrder insertOrder(UcenterMemberInfoDto userInfo, CourseVo courseVo);

    /**
     * 通过订单编号查询订单信息
     * @param orderNo 订单编号
     * @return 订单信息
     */
    TOrder getOrderByOrderNo(String orderNo);

    TOrder getOrderByUserIdAndCourseId(String userId,String courseId);

}