package com.danbro.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.cms.mapper.CrmBannerMapper;
import com.danbro.cms.service.CrmBannerService;
import org.springframework.stereotype.Service;

/**
 * 首页banner表(CrmBanner)表服务实现类
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
@Service("crmBannerService")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
}