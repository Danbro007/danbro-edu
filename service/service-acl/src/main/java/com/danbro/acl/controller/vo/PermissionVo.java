package com.danbro.acl.controller.vo;

import com.danbro.acl.entity.AclPermission;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @Classname AclPermissionDto
 * @Description TODO
 * @Date 2021/1/14 14:11
 * @Author Danrbo
 */
@Data
@ApiModel("返回的权限信息")
@Accessors(chain = true)
public class PermissionVo implements Serializable, VoConvert<PermissionVo, AclPermission> {
    private static final long serialVersionUID = 1296578685546521379L;

    @ApiModelProperty("权限ID")
    private String id;

    @ApiModelProperty("父级权限ID")
    private String pid;

    @ApiModelProperty("权限名")
    private String name;

    @ApiModelProperty("权限对应的菜单类型")
    private Integer type;

    @ApiModelProperty("权限值")
    private String permissionValue;

    @ApiModelProperty("权限对应的访问路径")
    private String path;

    @ApiModelProperty("权限对应的组件")
    private String component;

    @ApiModelProperty("权限对应的图标")
    private String icon;

    @ApiModelProperty("权限状态")
    private Boolean status;

    @Override
    public PermissionVo convertFrom(AclPermission aclPermission) {
        BeanUtils.copyProperties(aclPermission, this);
        return this;
    }

}
