package com.danbro.acl.controller.param;

import com.danbro.acl.entity.AclUser;
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
import java.io.Serializable;
import java.util.Date;

/**
 * @Classname AclUserDto
 * @Description TODO
 * @Date 2021/1/14 15:46
 * @Author Danrbo
 */
@Data
@ApiModel("添加或者更新后台用户的参数模型")
@Accessors(chain = true)
public class UserParam implements ParamConvert<AclUser> {
    @IsAssignID(message = "修改用户时ID必须存在并且合法！", groups = {Update.class})
    @Null(message = "创建用户时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("后台用户ID")
    private String id;

    @NotBlank(message = "用户名必须存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("后台用户名")
    private String username;

    @NotBlank(message = "用户昵称必须存在！", groups = {Insert.class, Update.class})
    @ApiModelProperty("后台用户的昵称")
    private String nickName;

    @Override
    public AclUser convertTo() {
        AclUser aclUser = new AclUser();
        BeanUtils.copyProperties(this, aclUser);
        return aclUser;
    }
}
