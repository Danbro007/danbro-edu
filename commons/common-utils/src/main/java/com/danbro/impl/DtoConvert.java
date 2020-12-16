package com.danbro.impl;

/**
 * @Classname DtoConvert
 * @Description TODO Dto 与 Entity 互相转换接口
 * @Date 2020/12/15 14:07
 * @Author Danrbo
 */
public interface DtoConvert<S,T> {
    /**
     *  Entity 转换成 Dto
     * @param t Entity
     * @return Dto
     */
    S convertFrom(T t);

    /**
     * Dto 转换成 Entity
     * @return Entity
     */
    T convertTo();



}
