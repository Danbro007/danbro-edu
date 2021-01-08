package com.danbro.order.service;

import com.danbro.dto.EduCourseBasicInfoDto;
import com.danbro.dto.UcenterMemberInfoDto;

/**
 * 订单(TOrder)表服务接口
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
public interface TOrderService {
    Boolean insertOrder(UcenterMemberInfoDto userInfo, EduCourseBasicInfoDto courseInfo);

}