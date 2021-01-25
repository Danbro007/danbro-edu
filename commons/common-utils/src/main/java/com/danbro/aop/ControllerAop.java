package com.danbro.aop;

import java.io.IOException;
import javax.validation.ConstraintViolationException;
import com.danbro.enums.ErrorResult;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.exceptions.RpcClientException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/**
 * @author Danrbo
 * @Classname ControllerAop
 * @Description TODO 处理 Controller 的异常并且打上日志
 * @Date 2021/1/22 16:52
 */
@Slf4j
public abstract class ControllerAop {
    protected abstract void targetMethod();

    @Around("targetMethod()")
    public Object handleControllerMethod(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        try {
            Object result = point.proceed();
            long elapsedTime = System.currentTimeMillis() -start;
            // 操作用时记录
            log.info("【{}】 use time:{}", point.getSignature(), elapsedTime);
            return result;
        } catch (Throwable e) {
            return handleException(point, e);
        }
    }

    /**
     * 处理异常
     *
     * @param point 切点
     * @param e     异常
     * @return 结果对象
     */
    private ErrorResult handleException(ProceedingJoinPoint point, Throwable e) {
        ErrorResult result = this.createResult();
        // 参数校验异常
        if (e instanceof ConstraintViolationException) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(e.getLocalizedMessage());
        }
        // 普通异常
        else if (e instanceof EduException) {
            result.setMessage(e.getLocalizedMessage());
            result.setCode(((EduException) e).getCode());
        }
        // rpc 异常
        else if (e instanceof RpcClientException) {
            result.setMessage(ResultCode.RPC_SERVICE_TIME_OUT.getMessage());
            result.setCode(ResultCode.RPC_SERVICE_TIME_OUT.getCode());
        }
        // 文件IO异常
        else if (e instanceof IOException) {
            result.setCode(ResultCode.FILE_IO_EXCEPTION.getCode());
            result.setMessage(ResultCode.FILE_IO_EXCEPTION.getMessage());
        }
        // 未知的异常
        else {
            log.error("{} error {}", point.getSignature(), e);
            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMessage(e.toString());
            result.setCode(ResultCode.UNKNOWN_EXCEPTION.getCode());
        }

        return result;
    }

    /**
     * 创建结果对象
     *
     * @return 结果对象
     */
    protected abstract ErrorResult createResult();
}
