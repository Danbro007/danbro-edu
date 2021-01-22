package com.danbro.enity;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 会员表(UcenterMember)实体类
 *
 * @author makejava
 * @since 2021-01-05 20:29:56
 */
@Data
public class UcenterMember implements Serializable {
    private static final long serialVersionUID = -48152768958243281L;
    /**
     * 会员id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别 1 女，2 男
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户签名
     */
    private String sign;
    /**
     * 是否禁用 1（true）已禁用，  0（false）未禁用
     */
    private Boolean isDisabled;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    private Boolean isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}