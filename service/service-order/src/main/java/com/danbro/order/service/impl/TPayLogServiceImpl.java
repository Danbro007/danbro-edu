package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.order.entity.TPayLog;
import com.danbro.order.mapper.TPayLogMapper;
import com.danbro.order.service.TPayLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付日志表(TPayLog)表服务实现类
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
@Service("tPayLogService")
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {
}