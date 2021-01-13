package com.danbro.cms.controller.admin;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.cms.dto.CrmBannerInsertDto;
import com.danbro.cms.dto.CrmBannerUpdateDto;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.cms.service.CrmBannerService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 首页banner表(CrmBanner)表控制层
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
@RestController

@RequestMapping("edu/")
public class AdminCrmBannerController {
    /**
     * 服务对象
     */
    @Resource
    private CrmBannerService crmBannerService;

    @ApiOperation("获取所有的 banner")
    @GetMapping("banner/list")
    public Result<List<CrmBanner>> getAllBannerList() {
        List<CrmBanner> list = crmBannerService.getAllBannerList();
        return Result.ofSuccess(list);
    }

    @ApiOperation("添加banner")
    @PostMapping("banner")
    public Result insertBanner(@RequestBody CrmBannerInsertDto crmBannerInsertDto) {
        CrmBanner crmBanner = new CrmBanner();
        BeanUtils.copyProperties(crmBannerInsertDto, crmBanner);
        boolean b = crmBannerService.insertBanner(crmBanner);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.INSERT_BANNER_FAILURE);
    }

    @ApiOperation("删除 banner")
    @DeleteMapping("banner/{bannerId}")
    public Result deleteBanner(@PathVariable String bannerId) {
        boolean b = crmBannerService.deleteBanner(bannerId);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_BANNER_FAILURE);
    }

    @ApiOperation("修改 banner")
    @PutMapping("banner")
    public Result deleteBanner(@RequestBody CrmBannerUpdateDto crmBannerUpdateDto) {
        CrmBanner crmBanner = new CrmBanner();
        BeanUtils.copyProperties(crmBannerUpdateDto, crmBanner);
        boolean b = crmBannerService.updateBanner(crmBanner);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.DELETE_BANNER_FAILURE);
    }

}