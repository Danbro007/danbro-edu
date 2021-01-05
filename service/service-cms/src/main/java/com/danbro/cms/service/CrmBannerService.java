package com.danbro.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.enums.Result;

import java.util.List;

/**
 * 首页banner表(CrmBanner)表服务接口
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBannerList();

    Boolean insertBanner(CrmBanner crmBanner);

    Boolean deleteBanner(String crmBannerId);

    Boolean updateBanner(CrmBanner crmBanner);

}