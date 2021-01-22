package com.danbro.acl.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.danbro.acl.service.IndexService;
import com.danbro.enums.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liweimo
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Result<Map<String, Object>> info() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Result.ofSuccess(indexService.getUserInfo(username));
    }

    /**
     * 获取菜单
     *
     * @return 用户能使用的菜单
     */
    @GetMapping("menu")
    public Result<List<JSONObject>> getMenu() {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Result.ofSuccess(indexService.getMenu(username));
    }

    @PostMapping("logout")
    public Result logout() {
        return Result.ofSuccess();
    }

}
