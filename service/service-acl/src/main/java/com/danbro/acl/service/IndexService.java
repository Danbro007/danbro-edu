package com.danbro.acl.service;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.danbro.acl.vo.UserInfoVo;

public interface IndexService {

    /**
     * 根据用户名获取用户登录信息
     *
     * @param username 用户名
     * @return 用户登录信息
     */
    UserInfoVo getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    List<JSONObject> getMenu(String username);

}
