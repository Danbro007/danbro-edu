package com.danbro.cms.controller.user;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.cms.service.CrmBannerService;
import com.danbro.cms.vo.CrmBannerVo;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserCrmBannerController
 * @Description TODO
 * @Date 2021/1/4 19:40
 * @Author Danrbo
 */

@RequestMapping("cms/banner")
@RestController
public class UserCrmBannerController {
    @Resource
    private CrmBannerService crmBannerService;

    @ApiOperation("获取所有的 banner")
    @GetMapping("list")
    public Result<List<CrmBannerVo>> getAllBannerList() {
        return Result.ofSuccess(crmBannerService.getAllBannerList());
    }
}
