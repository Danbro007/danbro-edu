package com.danbro.cms.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsAssignID;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname CrmBannerParam
 * @Description TODO
 * @Date 2021/1/4 15:39
 * @Author Danrbo
 */
@Data
@ApiModel("Banner 参数")
public class CrmBannerParam implements ParamConvert<CrmBanner> {
    @IsAssignID(message = "修改Banner时ID必须存在！", groups = {Update.class})
    @Null(message = "创建Banner时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("Banner ID")
    private String id;

    @ApiModelProperty("Banner 标题")
    private String title;

    @NotEmpty(message = "图片路径不存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("图片路径")
    private String imageUrl;

    @NotEmpty(message = "图片链接不存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("图片链接")
    private String linkUrl;

    @NotNull(message = "Banner 排序不存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("Banner 排序")
    private Integer sort;

    @Override
    public CrmBanner convertTo() {
        CrmBanner crmBanner = new CrmBanner();
        BeanUtils.copyProperties(this, crmBanner);
        return crmBanner;
    }
}
