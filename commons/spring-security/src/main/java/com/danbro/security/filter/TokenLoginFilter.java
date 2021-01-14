package com.danbro.security.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.security.entity.SecurityUser;
import com.danbro.security.entity.User;
import com.danbro.security.security.TokenManager;
import com.danbro.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * <p>
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        // 登录认证匹配器,指定登录接口。
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    /**
     * 用户身份验证操作：
     * 1、先获取用户信息
     * 2、通过用户信息对它进行鉴权，鉴权成功则会返回  UsernamePasswordAuthenticationToken 对象。
     *
     * @param req 请求
     * @param res 响应
     * @return UsernamePasswordAuthenticationToken
     * @throws AuthenticationException 身份认证异常
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 身份认证成功后的操作：
     * 1、先使用 tokenManager 创建 token
     * 2、把用户名当做 key，权限列表为 value 存入 redis
     * 3、返回给客户端 token
     *
     * @param req   请求
     * @param res   响应
     * @param chain 过滤执行链
     * @param auth  权限
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
        HashMap<String, String> map = new HashMap<>();
        map.put("token",token);
        ResponseUtil.out(res, Result.ofSuccess(map));
    }

    /**
     * 登录失败的操作
     * 1、直接返回失败的响应
     *
     * @param request  请求
     * @param response 响应
     * @param e        权限的异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        ResponseUtil.out(response, Result.ofFail(ResultCode.FAILURE));
    }
}
