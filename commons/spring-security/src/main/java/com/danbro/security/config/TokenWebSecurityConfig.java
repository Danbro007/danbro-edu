package com.danbro.security.config;

import com.danbro.security.filter.TokenAuthenticationFilter;
import com.danbro.security.filter.TokenLoginFilter;
import com.danbro.security.security.DefaultPasswordEncoder;
import com.danbro.security.security.TokenLogoutHandler;
import com.danbro.security.security.TokenManager;
import com.danbro.security.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Classname TokenWebSecurityConfig
 * @Description TODO  token安全配置类
 * @Date 2021/1/14 11:00
 * @Author Danrbo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private TokenManager tokenManager;
    private DefaultPasswordEncoder defaultPasswordEncoder;
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 配置设置
     *
     * @param http Http安全配置
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                // 设置统一的未授权响应方式
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                // 关闭csrf
                .and().csrf().disable()
                //被保护的资源都需要授权才能访问
                .authorizeRequests()
                .anyRequest().authenticated()
                // 设置用户登录的url
                .and().logout().logoutUrl("/admin/acl/index/logout")
                // 设置用户登出的处理器
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()
                // 设置过滤器，这个过滤器负责用户身份验证和身份验证成功的鉴权操作
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                // 设置过滤器，主要是拦截 admin 角色发送的请求，然后获取 admin 的权限
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }

    /**
     * 设置密码编码器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    /**
     * 配置哪些请求不拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        );
    }
}