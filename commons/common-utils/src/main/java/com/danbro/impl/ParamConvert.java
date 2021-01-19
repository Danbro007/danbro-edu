package com.danbro.impl;

/**
 * @Classname ParamConvert
 * @Description TODO param 转换成 entity
 * @Date 2021/1/19 14:21
 * @Author Danrbo
 */
public interface ParamConvert<T> {
    /**
     * Dto 转换成 Entity
     *
     * @return Entity
     */
    T convertTo();
}
