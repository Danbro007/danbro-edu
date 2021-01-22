package com.danbro.cms.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.cms.vo.CrmBannerVo;

/**
 * 首页banner表(CrmBanner)表服务接口
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBannerVo> getAllBannerList();

    CrmBanner insertOrUpdate(CrmBanner crmBanner);

    Boolean deleteBanner(String crmBannerId);

    Boolean updateBanner(CrmBanner crmBanner);

}