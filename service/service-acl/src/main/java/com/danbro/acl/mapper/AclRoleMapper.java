package com.danbro.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.acl.entity.AclRole;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * (AclRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public interface AclRoleMapper extends BaseMapper<AclRole> {

    List<AclRole> getRoleListByUserId(String userId);

}