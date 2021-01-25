package com.danbro.cms.controller.admin;

import java.util.List;
import javax.annotation.Resource;

import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.ValidParam;
import com.danbro.cms.dto.CrmBannerParam;
import com.danbro.cms.service.CrmBannerService;
import com.danbro.cms.vo.CrmBannerVo;
import com.danbro.enums.Result;
import com.danbro.impl.Insert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 首页banner表(CrmBanner)表控制层
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
@RestController
@Validated
@RequestMapping("edu/banner")
public class AdminCrmBannerController {
    /**
     * 服务对象
     */
    @Resource
    private CrmBannerService crmBannerService;

    @ApiOperation("获取所有的 Banner")
    @GetMapping("list")
    public Result<List<CrmBannerVo>> getAllBannerList() {
        return Result.ofSuccess(crmBannerService.getAllBannerList());
    }

    @ValidParam
    @ApiOperation("添加 Banner")
    @PostMapping("")
    public Result<CrmBannerVo> insertBanner(@Validated(Insert.class) @RequestBody CrmBannerParam crmBannerParam) {
        return Result.ofSuccess(new CrmBannerVo().convertFrom(crmBannerService.insertOrUpdate(crmBannerParam.convertTo())));
    }

    @ApiOperation("删除 banner")
    @DeleteMapping("{bannerId}")
    public Result deleteBanner(@IsAssignID(message = "Banner ID非法！") @PathVariable String bannerId) {
        crmBannerService.deleteBannerById(bannerId);
        return Result.ofSuccess();
    }

    @ApiOperation("修改 banner")
    @PutMapping("")
    public Result<CrmBannerVo> deleteBanner(@Validated(Update.class) @RequestBody CrmBannerParam crmBannerParam) {
        return Result.ofSuccess(new CrmBannerVo().convertFrom(crmBannerService.insertOrUpdate(crmBannerParam.convertTo())));
    }
}