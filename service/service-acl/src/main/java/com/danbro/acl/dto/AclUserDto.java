package com.danbro.acl.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.entity.AclUser;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AclUserDto implements Serializable, DtoConvert<AclUserDto, AclUser> {
    private static final long serialVersionUID = -8208280582437170591L;

    private String id;
    /**
     * 微信openid
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String salt;
    /**
     * 用户签名
     */
    private String token;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;


    @Override
    public AclUserDto convertFrom(AclUser aclUser) {
        AclUserDto.AclUserDtoConvertor convertor = new AclUserDto.AclUserDtoConvertor();
        return convertor.doBackward(aclUser);
    }

    @Override
    public AclUser convertTo() {
        AclUserDto.AclUserDtoConvertor convertor = new AclUserDto.AclUserDtoConvertor();
        return convertor.doForward(this);
    }


    private static class AclUserDtoConvertor extends Converter<AclUserDto, AclUser> {
        @Override
        protected AclUser doForward(AclUserDto aclUserDto) {
            AclUser aclUser = new AclUser();
            BeanUtils.copyProperties(aclUserDto, aclUser);
            return aclUser;
        }

        @Override
        protected AclUserDto doBackward(AclUser aclUser) {
            AclUserDto aclUserDto = new AclUserDto();
            BeanUtils.copyProperties(aclUser, aclUserDto);
            return aclUserDto;
        }
    }
}
