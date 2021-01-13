package com.danbro.acl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 权限(AclPermission)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@Data
public class AclPermission implements Serializable {
    private static final long serialVersionUID = -56097403994514887L;
    /**
     * 编号
     */
    private String id;
    /**
     * 所属上级
     */
    private String pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型(1:菜单,2:按钮)
     */
    private Integer type;
    /**
     * 权限值
     */
    private String permissionValue;
    /**
     * 访问路径
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 图标
     */
    private String icon;
    /**
     * 状态(0:禁止,1:正常)
     */
    private Boolean status;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;

}