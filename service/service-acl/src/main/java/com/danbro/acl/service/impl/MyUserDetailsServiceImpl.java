package com.danbro.acl.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.acl.entity.AclUser;
import com.danbro.acl.service.AclUserService;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.security.entity.SecurityUser;
import com.danbro.security.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Classname MyUserDetailsService
 * @Description TODO 负责 SpringSecurity的用户登录的逻辑判断
 * @Date 2021/1/14 11:23
 * @Author Danrbo
 */
@Service("userDetailService")
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AclUserService userService;

    @Autowired
    AclPermissionServiceImpl permissionService;

    /**
     * 1、先对用户身份进行验证
     * 2、验证成功再鉴权
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<AclUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AclUser user = userService.getOne(queryWrapper);
        if (user == null) {
            throw new EduException(ResultCode.USER_NOT_EXIST);
        }
        User currentUser = new User();
        BeanUtils.copyProperties(user, currentUser);
        SecurityUser securityUser = new SecurityUser(currentUser);
        List<String> permissionValueList = permissionService.getPermissionValueByUserId(user.getId());
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}
