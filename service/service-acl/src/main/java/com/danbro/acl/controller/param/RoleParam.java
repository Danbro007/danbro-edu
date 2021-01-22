package com.danbro.acl.controller.param;

import com.danbro.acl.entity.AclRole;
import com.danbro.anotation.IsAssignID;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * @Classname AclRoleDto
 * @Description TODO
 * @Date 2021/1/14 14:31
 * @Author Danrbo
 */
@Data
@ApiModel("添加或者更新的角色信息")
@Accessors(chain = true)
public class RoleParam implements ParamConvert<AclRole> {
    @IsAssignID(message = "修改角色时ID必须存在！", groups = {Update.class})
    @Null(message = "创建角色时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("角色ID")
    private String id;

    @NotBlank(message = "角色名必须存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("角色名")
    private String roleName;

    @NotBlank(message = "角色编码必须存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("角色编码")
    private String roleCode;

    @ApiModelProperty("角色备注")
    private String remark;


    @Override
    public AclRole convertTo() {
        AclRole aclRole = new AclRole();
        BeanUtils.copyProperties(this, aclRole);
        return aclRole;
    }
}
