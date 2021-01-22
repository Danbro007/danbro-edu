package com.danbro.acl.controller.param;

import com.danbro.acl.entity.AclRole;
import com.danbro.impl.ParamConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

/**
 * @Classname QueryRoleParam
 * @Description TODO
 * @Date 2021/1/22 12:38
 * @Author Danrbo
 */
@Data
public class QueryRoleParam implements ParamConvert<AclRole> {
    @ApiModelProperty("角色名")
    private String roleName;

    @Override
    public AclRole convertTo() {
        AclRole aclRole = new AclRole();
        BeanUtils.copyProperties(this, aclRole);
        return aclRole;
    }
}
