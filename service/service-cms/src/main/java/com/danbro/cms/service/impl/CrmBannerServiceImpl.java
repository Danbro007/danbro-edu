package com.danbro.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.cms.mapper.CrmBannerMapper;
import com.danbro.cms.service.CrmBannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 首页banner表(CrmBanner)表服务实现类
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
@Service("crmBannerService")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Cacheable(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public List<CrmBanner> getAllBannerList() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        return this.list(queryWrapper);
    }

    @CachePut(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public Boolean insertBanner(CrmBanner crmBanner) {
        return this.save(crmBanner);
    }

    @CacheEvict(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public Boolean deleteBanner(String crmBannerId) {
        return this.removeById(crmBannerId);
    }

    @CachePut(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public Boolean updateBanner(CrmBanner crmBanner) {
        return this.updateById(crmBanner);
    }
}