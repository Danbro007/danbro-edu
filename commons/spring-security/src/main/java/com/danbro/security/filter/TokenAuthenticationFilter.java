package com.danbro.security.filter;


import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.security.security.TokenManager;
import com.danbro.utils.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Classname TokenAuthenticationFilter
 * @Description TODO  客户端访问资源的过滤器
 * @Date 2021/1/14 11:00
 * @Author Danrbo
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private static final String ADMIN = "admin";

    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 内部拦截器：
     * 1、把请求路径带有 admin 的拦截下来
     * 2、获取 admin 的权限
     * 3、把权限放入 SecurityContextHolder 来管理
     *
     * @param req   请求
     * @param res   响应
     * @param chain 拦截器执行链
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        logger.info("=================" + req.getRequestURI());
        if (!req.getRequestURI().contains(ADMIN)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(req);
        } catch (Exception e) {
            ResponseUtil.out(res, Result.ofFail(ResultCode.FAILURE));
        }

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, Result.ofFail(ResultCode.FAILURE));
        }
        chain.doFilter(req, res);
    }

    /**
     * 1、获取 token，然后通过 token 获取用户名
     * 2、通过获取的用户名到 redis 获取对应的权限列表
     * 3、把用户名、token和权限封装成 UsernamePasswordAuthenticationToken 对象返回
     *
     * @param request 请求
     * @return 用户 token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        if (token != null && !"".equals(token.trim())) {
            String userName = tokenManager.getUserFromToken(token);

            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(userName);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            assert permissionValueList != null;
            for (String permissionValue : permissionValueList) {
                if (StringUtils.isEmpty(permissionValue)) {
                    continue;
                }
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(authority);
            }

            if (!StringUtils.isEmpty(userName)) {
                return new UsernamePasswordAuthenticationToken(userName, token, authorities);
            }
            return null;
        }
        return null;
    }
}