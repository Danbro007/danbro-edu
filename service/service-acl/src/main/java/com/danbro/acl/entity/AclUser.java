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
 * 用户表(AclUser)实体类
 *
 * @author makejava
 * @since 2021-01-13 14:18:15
 */
@Data
@Accessors(chain = true)
public class AclUser implements Serializable {
    private static final long serialVersionUID = -40664230232660608L;
    /**
     * 会员id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 微信openid
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String salt;
    /**
     * 用户签名
     */
    private String token;
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