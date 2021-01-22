package com.danbro.cms.vo;

import java.io.Serializable;
import java.util.Date;
import com.danbro.cms.entity.CrmBanner;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 首页banner表(CrmBanner)实体类
 *
 * @author makejava
 * @since 2021-01-04 15:17:00
 */
@Data
@ApiModel("Banner数据")
public class CrmBannerVo implements Serializable, VoConvert<CrmBannerVo, CrmBanner> {
    private static final long serialVersionUID = 404881373255562815L;
    @ApiModelProperty("Banner ID")
    private String id;

    @ApiModelProperty("Banner 标题")
    private String title;

    @ApiModelProperty("Banner 图片路径")
    private String imageUrl;

    @ApiModelProperty("Banner 链接")
    private String linkUrl;

    @ApiModelProperty("Banner 排序")
    private Integer sort;

    @ApiModelProperty("Banner 创建时间")
    private Date gmtCreate;

    @ApiModelProperty("Banner 修改时间")
    private Date gmtModified;

    @Override
    public CrmBannerVo convertFrom(CrmBanner crmBanner) {
        BeanUtils.copyProperties(crmBanner, this);
        return this;
    }
}