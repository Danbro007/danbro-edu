package com.danbro.impl;

/**
 * @Classname DtoConver
 * @Description TODO
 * @Date 2020/12/15 14:07
 * @Author Danrbo
 */
public interface DtoConvert<S,T> {
    /**
     * Entity 转换成 Dto
     * @param t
     * @return
     */
    S convertFrom(T t);

    /**
     * Dto 转换成 Entity
     * @return Entity
     */
    T convertTo();



}
