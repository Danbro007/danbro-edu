package com.danbro.acl.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Danrbo
 * @Classname UserInfoVo
 * @Description TODO
 * @Date 2021/1/25 14:47
 */
@Data
@Accessors(chain = true)
public class UserInfoVo implements Serializable {
    private static final long serialVersionUID = 1851969707529595217L;
    private String name;
    private String avatar;
    private List<String> roles;
    private List<String> permissionValueList;
}
