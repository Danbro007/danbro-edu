package com.danbro.acl.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色权限(AclRolePermission)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Data
@Accessors(chain = true)
public class AclRolePermission implements Serializable {
    private static final long serialVersionUID = 967579754123205024L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String roleId;

    private String permissionId;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
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

}