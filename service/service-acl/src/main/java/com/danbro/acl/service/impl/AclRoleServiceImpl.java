package com.danbro.acl.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.dto.AclRoleDto;
import com.danbro.acl.dto.TreeNodePermissionDto;
import com.danbro.acl.entity.AclRole;
import com.danbro.acl.entity.AclRolePermission;
import com.danbro.acl.entity.AclUserRole;
import com.danbro.acl.mapper.AclRoleMapper;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.service.AclRolePermissionService;
import com.danbro.acl.service.AclRoleService;
import com.danbro.acl.service.AclUserRoleService;
import com.danbro.enity.OutPutPagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (AclRole)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Service("aclRoleService")
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRole> implements AclRoleService {
    @Autowired
    AclRolePermissionService rolePermissionService;

    @Autowired
    AclUserRoleService userRoleService;

    @Autowired
    AclPermissionService permissionService;


    @Override
    public AclRoleDto getRoleInfoById(String id) {
        AclRole aclRole = this.getById(id);
        return AclRoleDto.builder().build().convertFrom(aclRole);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleById(String roleId) {
        this.removeById(roleId);
        QueryWrapper<AclRolePermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.eq("role_id", roleId);
        rolePermissionService.remove(permissionQueryWrapper);
        QueryWrapper<AclUserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("role_id", roleId);
        userRoleService.remove(userRoleQueryWrapper);
    }

    @Override
    public AclRoleDto insertOrUpdate(AclRoleDto aclRoleDto) {
        AclRole aclRole = aclRoleDto.convertTo();
        this.saveOrUpdate(aclRole);
        aclRoleDto.convertFrom(aclRole);
        return aclRoleDto;
    }

    @Override
    public OutPutPagingDto<AclRoleDto> pagingGetRoleListByCondition(Integer page, Integer limit, AclRoleDto queryCondition) {
        Page<AclRole> rolePage = new Page<>(page, limit);
        QueryWrapper<AclRole> queryWrapper = new QueryWrapper<>();
        if (queryCondition != null && !StringUtils.isEmpty(queryCondition.getRoleName())) {
            queryWrapper.like("role_name", queryCondition.getRoleName());
        }
        queryWrapper.orderByDesc("gmt_create");
        this.page(rolePage, queryWrapper);
        ArrayList<AclRoleDto> list = new ArrayList<>();
        rolePage.getRecords().forEach(e -> list.add(new AclRoleDto().convertFrom(e)));
        return new OutPutPagingDto<AclRoleDto>()
                .setRows(list)
                .setTotal(rolePage.getTotal());
    }

    @Override
    public void batchDeleteRole(List<String> roleList) {
        this.removeByIds(roleList);
        QueryWrapper<AclRolePermission> permissionQueryWrapper = new QueryWrapper<>();
        permissionQueryWrapper.in("role_id", roleList);
        rolePermissionService.remove(permissionQueryWrapper);
        QueryWrapper<AclUserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.in("role_id", roleList);
        userRoleService.remove(userRoleQueryWrapper);
    }

    @Override
    public List<AclRole> getRoleListByUserId(String userId) {
        return this.baseMapper.getRoleListByUserId(userId);
    }

    @Override
    public List<TreeNodePermissionDto> getRolePermission(String roleId) {
        return permissionService.getRolePermission(roleId);
    }
}