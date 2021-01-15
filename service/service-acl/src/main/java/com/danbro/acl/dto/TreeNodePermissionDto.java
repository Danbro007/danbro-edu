package com.danbro.acl.dto;

import java.util.List;
import lombok.Data;

/**
 * @Classname TreePermissionDto
 * @Description TODO 父级菜单的权限
 * @Date 2021/1/13 14:30
 * @Author Danrbo
 */
@Data
public class TreeNodePermissionDto {
    private String id;
    private String pid;
    private String name;
    private Integer type;
    private String permissionValue;
    private String path;
    private String component;
    private String icon;
    private Boolean status;
    private Integer level;
    private Boolean isSelected;
    private List<TreeNodePermissionDto> children;
}
