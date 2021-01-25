package com.danbro.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.cms.mapper.CrmBannerMapper;
import com.danbro.cms.service.CrmBannerService;
import com.danbro.cms.vo.CrmBannerVo;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    public List<CrmBannerVo> getAllBannerList() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        ArrayList<CrmBannerVo> list = new ArrayList<>();
        List<CrmBanner> crmBannerList = this.list(queryWrapper);
        crmBannerList.forEach(e -> list.add(new CrmBannerVo().convertFrom(e)));
        return list;
    }

    @CachePut(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public CrmBanner insertOrUpdate(CrmBanner crmBanner) {
        boolean success = this.saveOrUpdate(crmBanner);
        if (!success) {
            throw new EduException(ResultCode.BANNER_INSERT_OR_UPDATE_FAILURE);
        }
        return crmBanner;
    }

    @CacheEvict(value = "banner", key = "'selectIndexBannerList'")
    @Override
    public void deleteBannerById(String crmBannerId) {
        boolean success = this.removeById(crmBannerId);
        if (!success) {
            throw new EduException(ResultCode.BANNER_DELETE_FAILURE);
        }
    }
}