package com.danbro.acl.controller.vo;

import com.danbro.acl.entity.AclUser;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname AclUserDto
 * @Description TODO
 * @Date 2021/1/14 15:46
 * @Author Danrbo
 */
@Data
@ApiModel("返回的用户信息")
@Accessors(chain = true)
public class UserVo implements Serializable, VoConvert<UserVo, AclUser> {
    private static final long serialVersionUID = -8208280582437170591L;

    @ApiModelProperty("后台用户ID")
    private String id;

    @ApiModelProperty("后台用户名")
    private String username;

    @ApiModelProperty("后台用户的昵称")
    private String nickName;

    @ApiModelProperty("创建后台用户的时间")
    private Date gmtCreate;

    @ApiModelProperty("修改后台用户的时间")
    private Date gmtModified;


    @Override
    public UserVo convertFrom(AclUser aclUser) {
        BeanUtils.copyProperties(aclUser, this);
        return this;
    }

}
