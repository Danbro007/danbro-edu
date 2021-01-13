package com.danbro.acl.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色权限(AclRolePermission)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
public class AclRolePermission implements Serializable {
    private static final long serialVersionUID = 967579754123205024L;

    private String id;

    private String roleId;

    private String permissionId;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Object isDeleted;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public Object getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Object isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}