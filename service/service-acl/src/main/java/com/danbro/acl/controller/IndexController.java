package com.danbro.acl.controller;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.danbro.acl.service.IndexService;
import com.danbro.enums.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Result<Map<String, Object>> info() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.ofSuccess(userInfo);
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("menu")
    public Result<List<JSONObject>> getMenu() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.ofSuccess(permissionList);
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ofSuccess();
    }

}
