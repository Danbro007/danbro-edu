package com.danbro.acl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 权限(AclPermission)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:14
 */
@Data
public class AclPermission implements Serializable {
    private static final long serialVersionUID = -5496532551957302781L;
    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    @TableLogic
    private Boolean isDeleted;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @TableField(exist = false)
    private Boolean isSelected;

}