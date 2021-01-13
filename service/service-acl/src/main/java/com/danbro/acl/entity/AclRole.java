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
 * (AclRole)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Data
public class AclRole implements Serializable {
    private static final long serialVersionUID = 900539652503698590L;
    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 角色名称
     */

    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 备注
     */
    private String remark;
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

}