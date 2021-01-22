package com.danbro.acl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.acl.controller.param.UserParam;
import com.danbro.acl.controller.vo.RoleVo;
import com.danbro.acl.controller.vo.UserVo;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.entity.AclUserRole;
import com.danbro.acl.mapper.AclUserMapper;
import com.danbro.acl.service.AclRoleService;
import com.danbro.acl.service.AclUserRoleService;
import com.danbro.acl.service.AclUserService;
import com.danbro.enity.OutPutPagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 用户表(AclUser)表服务实现类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Service("aclUserService")
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements AclUserService {
    @Autowired
    AclRoleService aclRoleService;
    @Autowired
    AclUserRoleService userRoleService;

    @Override
    public UserVo getUserInfoById(String userId) {
        AclUser aclUser = this.getById(userId);
        return new UserVo().convertFrom(aclUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteUserById(String userId) {
        this.removeById(userId);
        QueryWrapper<AclUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        userRoleService.remove(queryWrapper);
    }

    @Override
    public AclUser insertOrUpdateUser(AclUser aclUser) {
        this.saveOrUpdate(aclUser);
        return aclUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteUser(List<String> userList) {
        this.removeByIds(userList);
        QueryWrapper<AclUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userList);
        userRoleService.remove(queryWrapper);
    }

    @Override
    public OutPutPagingDto<UserVo> pagingGetUserListByCondition(Integer page, Integer limit, UserParam userParam) {
        Page<AclUser> userPage = new Page<>(page, limit);
        QueryWrapper<AclUser> queryWrapper = new QueryWrapper<>();
        if (userParam != null && !StringUtils.isEmpty(userParam.getUsername())) {
            queryWrapper.like("username", userParam.getUsername());
        }
        this.page(userPage, queryWrapper);
        ArrayList<UserVo> list = new ArrayList<>();
        userPage.getRecords().forEach(e -> list.add(new UserVo().convertFrom(e)));
        return new OutPutPagingDto<UserVo>()
                .setTotal(userPage.getTotal())
                .setRows(list);
    }

    @Override
    public Map<String, List<RoleVo>> getUserRoleListByUserId(String userId) {
        List<RoleVo> assignRoles = new ArrayList<>();
        List<RoleVo> allRolesList = new ArrayList<>();
        HashMap<String, List<RoleVo>> map = new HashMap<>();
        aclRoleService.getRoleListByUserId(userId).forEach(e -> assignRoles.add(new RoleVo().convertFrom(e)));
        aclRoleService.list().forEach(e -> allRolesList.add(new RoleVo().convertFrom(e)));
        map.put("assignRoles", assignRoles);
        map.put("allRolesList", allRolesList);
        return map;
    }

    @Override
    public void inertUserRole(String userId, String roleIds) {
        QueryWrapper<AclUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleIds).eq("user_id", userId);
        userRoleService.remove(queryWrapper);
        String[] ids = StrUtil.split(roleIds, ",");
        List<AclUserRole> aclUserRoles = new ArrayList<>();
        for (String id : ids) {
            aclUserRoles.add(AclUserRole.builder().roleId(id).userId(userId).build());
        }
        userRoleService.saveBatch(aclUserRoles);
    }

    @Override
    public AclUser getUserInfoByUsername(String username) {
        QueryWrapper<AclUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }

}