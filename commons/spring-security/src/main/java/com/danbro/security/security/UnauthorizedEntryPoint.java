package com.danbro.security.security;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname UnauthorizedEntryPoint
 * @Description TODO  未授权的统一处理方式
 * @Date 2021/1/14 11:00
 * @Author Danrbo
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    /**
     * 未授权的统一返回错误响应
     *
     * @param request       请求
     * @param response      响应
     * @param authException 权限异常
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ResponseUtil.out(response, Result.ofFail(ResultCode.FAILURE));
    }
}
