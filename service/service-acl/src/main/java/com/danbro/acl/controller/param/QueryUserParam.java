package com.danbro.acl.controller.param;

import com.danbro.acl.entity.AclRole;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.entity.AclUserRole;
import com.danbro.impl.ParamConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname QueryRoleParam
 * @Description TODO
 * @Date 2021/1/22 12:38
 * @Author Danrbo
 */
@Data
public class QueryUserParam implements ParamConvert<AclUser> {
    @ApiModelProperty("用户名")
    private String username;

    @Override
    public AclUser convertTo() {
        AclUser aclUser = new AclUser();
        BeanUtils.copyProperties(this, aclUser);
        return aclUser;
    }
}
