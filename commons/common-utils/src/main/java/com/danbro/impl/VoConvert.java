package com.danbro.impl;

/**
 * @Classname VoConvert
 * @Description TODO entity 转换成 vo
 * @Date 2021/1/19 14:24
 * @Author Danrbo
 */

public interface VoConvert<S, T> {
    /**
     * Entity 转换成 Vo
     *
     * @param t Entity
     * @return Dto
     */
    S convertFrom(T t);
}
