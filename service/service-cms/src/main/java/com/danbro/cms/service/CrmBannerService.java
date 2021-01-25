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
    /**
     * 显示所有的 Banner
     *
     * @return Banner 列表
     */
    List<CrmBannerVo> getAllBannerList();

    /**
     * 添加或者修改 Banner
     *
     * @param crmBanner 要添加或者修改的 Banner
     * @return 添加或者修改完毕后的 Banner
     */
    CrmBanner insertOrUpdate(CrmBanner crmBanner);

    /**
     * 通过ID删除Banner
     *
     * @param crmBannerId BannerID
     */
    void deleteBannerById(String crmBannerId);

}