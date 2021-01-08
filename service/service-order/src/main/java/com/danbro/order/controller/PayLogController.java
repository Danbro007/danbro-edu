package com.danbro.order.controller;

import com.danbro.order.entity.TPayLog;
import com.danbro.order.service.TPayLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 支付日志表(TPayLog)表控制层
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
@RestController
@RequestMapping("tPayLog")
public class PayLogController {
    /**
     * 服务对象
     */
    @Resource
    private TPayLogService tPayLogService;


}