package com.danbro.acl.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.danbro.acl.entity.AclRole;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @Classname AclRoleDto
 * @Description TODO
 * @Date 2021/1/14 14:31
 * @Author Danrbo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AclRoleDto implements DtoConvert<AclRoleDto, AclRole> {
    /**
     * 角色id
     */
    @NotEmpty
    private String id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;

    @Override
    public AclRoleDto convertFrom(AclRole aclRole) {
        AclRoleDtoConvertor convertor = new AclRoleDtoConvertor();
        return convertor.doBackward(aclRole);
    }

    @Override
    public AclRole convertTo() {
        AclRoleDtoConvertor convertor = new AclRoleDtoConvertor();
        return convertor.doForward(this);
    }

    private static class AclRoleDtoConvertor extends Converter<AclRoleDto, AclRole> {
        @Override
        protected AclRole doForward(AclRoleDto aclRoleDto) {
            AclRole AclRole = new AclRole();
            BeanUtils.copyProperties(aclRoleDto, AclRole);
            return AclRole;
        }

        @Override
        protected AclRoleDto doBackward(AclRole aclRole) {
            AclRoleDto AclRoleDto = new AclRoleDto();
            BeanUtils.copyProperties(aclRole, AclRoleDto);
            return AclRoleDto;
        }
    }
}
