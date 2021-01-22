package com.danbro.acl.controller.vo;

import com.danbro.acl.entity.AclRole;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname AclRoleDto
 * @Description TODO
 * @Date 2021/1/14 14:31
 * @Author Danrbo
 */
@Data
@ApiModel("返回的角色信息")
@Accessors(chain = true)
public class RoleVo implements Serializable, VoConvert<RoleVo, AclRole> {
    private static final long serialVersionUID = -6619828152106306811L;

    @ApiModelProperty("角色ID")
    private String id;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("角色备注")
    private String remark;

    @ApiModelProperty("角色创建时间")
    private Date gmtCreate;
    @ApiModelProperty("角色更新时间")
    private Date gmtModified;

    @Override
    public RoleVo convertFrom(AclRole aclRole) {
        BeanUtils.copyProperties(aclRole, this);
        return this;
    }
}
