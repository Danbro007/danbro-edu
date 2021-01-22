package com.danbro.acl.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.controller.param.QueryRoleParam;
import com.danbro.acl.controller.vo.RoleVo;
import com.danbro.acl.controller.vo.TreeNodePermissionVo;
import com.danbro.acl.entity.AclPermission;
import com.danbro.acl.entity.AclRole;
import com.danbro.acl.entity.AclRolePermission;
import com.danbro.acl.entity.AclUserRole;
import com.danbro.acl.mapper.AclRoleMapper;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.service.AclRolePermissionService;
import com.danbro.acl.service.AclRoleService;
import com.danbro.acl.service.AclUserRoleService;
import com.danbro.acl.utils.PermissionUtils;
import com.danbro.enity.OutPutPagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    public AclRole getRoleInfoById(String roleId) {
        return this.getById(roleId);

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
    public AclRole insertOrUpdate(AclRole role) {
        this.saveOrUpdate(role);
        return role;
    }

    @Override
    public OutPutPagingDto<RoleVo> pagingGetRoleListByCondition(Integer page, Integer limit, QueryRoleParam roleParam) {
        Page<AclRole> rolePage = new Page<>(page, limit);
        QueryWrapper<AclRole> queryWrapper = new QueryWrapper<>();
        // 查询条件是角色名
        if (roleParam != null && !StringUtils.isEmpty(roleParam.getRoleName())) {
            queryWrapper.like("role_name", roleParam.getRoleName());
        }
        queryWrapper.orderByDesc("gmt_create");
        this.page(rolePage, queryWrapper);
        ArrayList<RoleVo> list = new ArrayList<>();
        rolePage.getRecords().forEach(e -> list.add(new RoleVo().convertFrom(e)));
        return new OutPutPagingDto<RoleVo>()
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
    public List<TreeNodePermissionVo> getRolePermission(String roleId) {
        List<AclPermission> allPermission = permissionService.list();
        List<AclPermission> rolePermission = permissionService.getRolePermission(roleId);
        allPermission.forEach(m -> {
            rolePermission.forEach(n -> {
                if (m.getPid().equals(n.getId())) {
                    m.setIsSelected(true);
                }
            });
        });
        return PermissionUtils.buildPermissionTree(allPermission);
    }
}