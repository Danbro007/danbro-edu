package com.danbro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 讲师(EduTeacher)实体类
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Data
public class EduTeacher implements Serializable {
    private static final long serialVersionUID = -47902043022957890L;
    /**
     * 讲师ID
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;
    /**
     * 讲师姓名
     */
    private String name;
    /**
     * 讲师简介
     */
    private String intro;
    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;
    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Object level;
    /**
     * 讲师头像
     */
    private String avatar;
    /**
     * 排序
     */
    private Object sort;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Object isDeleted;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;

}