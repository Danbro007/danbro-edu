package com.danbro.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.danbro.acl.entity.AclRole;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.service.AclPermissionService;
import com.danbro.acl.service.AclRoleService;
import com.danbro.acl.service.AclUserService;
import com.danbro.acl.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private AclUserService userService;

    @Autowired
    private AclRoleService roleService;

    @Autowired
    private AclPermissionService permissionService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户名获取用户登录信息
     * 1、先通过用户名判断是不是 admin 用户
     *
     * @param username
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> result = new HashMap<>(16);

        AclUser user = userService.getUserInfoByUsername(username);
        //根据用户id获取角色
        List<AclRole> roleList = roleService.getRoleListByUserId(user.getId());
        List<String> roleNameList = roleList.stream().map(AclRole::getRoleName).collect(Collectors.toList());
        if (roleNameList.size() == 0) {
            //前端框架必须返回一个角色，否则报错，如果没有角色，返回一个空角色
            roleNameList.add("");
        }

        //根据用户id获取操作权限值
        List<String> permissionValueList = permissionService.getPermissionValueByUserId(user.getId());
        redisTemplate.opsForValue().set(username, permissionValueList);
        result.put("name", user.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roleNameList);
        result.put("permissionValueList", permissionValueList);
        return result;
    }

    /**
     * 根据用户名获取动态菜单
     *
     * @param username 用户名
     * @return 菜单
     */
    @Override
    public List<JSONObject> getMenu(String username) {
        AclUser user = userService.getUserInfoByUsername(username);

        //根据用户id获取用户菜单权限
        return permissionService.getPermissionByUserId(user.getId());
    }


}
