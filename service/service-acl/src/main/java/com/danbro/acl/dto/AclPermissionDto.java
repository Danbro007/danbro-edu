package com.danbro.acl.dto;

import com.danbro.acl.entity.AclPermission;
import com.danbro.impl.DtoConvert;
import com.google.common.base.Converter;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Classname AclPermissionDto
 * @Description TODO
 * @Date 2021/1/14 14:11
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class AclPermissionDto implements DtoConvert<AclPermissionDto, AclPermission> {
    /**
     * 权限Id
     */
    private String id;
    /**
     * 所属上级
     */
    private String pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型(1:菜单,2:按钮)
     */
    private Integer type;
    /**
     * 权限值
     */
    private String permissionValue;
    /**
     * 访问路径
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 状态(0:禁止,1:正常)
     */
    private Boolean status;

    @Override
    public AclPermissionDto convertFrom(AclPermission aclPermission) {
        AclPermissionDtoConvertor convertor = new AclPermissionDtoConvertor();
        return convertor.doBackward(aclPermission);
    }

    @Override
    public AclPermission convertTo() {
        AclPermissionDtoConvertor convertor = new AclPermissionDtoConvertor();
        return convertor.doForward(this);
    }


    private static class AclPermissionDtoConvertor extends Converter<AclPermissionDto, AclPermission> {
        @Override
        protected AclPermission doForward(AclPermissionDto aclPermissionDto) {
            AclPermission aclPermission = new AclPermission();
            BeanUtils.copyProperties(aclPermissionDto, aclPermission);
            return aclPermission;
        }

        @Override
        protected AclPermissionDto doBackward(AclPermission aclPermission) {
            AclPermissionDto aclPermissionDto = new AclPermissionDto();
            BeanUtils.copyProperties(aclPermission, aclPermissionDto);
            return aclPermissionDto;
        }
    }
}
