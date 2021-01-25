package com.danbro.enums;

/**
 * @author Danrbo
 * @Classname ErrorResult
 * @Description TODO
 * @Date 2021/1/25 11:16
 */
public interface ErrorResult {
    /**
     * 设置返回的消息
     */
    void setMessage(String message);

    /**
     * 设置状态码
     */
    void setCode(Integer code);
}
