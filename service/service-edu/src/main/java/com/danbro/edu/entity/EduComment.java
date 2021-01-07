package com.danbro.edu.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 评论(EduComment)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Data
public class EduComment implements Serializable {
    private static final long serialVersionUID = 997361826107512349L;
    /**
     * 讲师ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 讲师id
     */
    private String teacherId;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 会员头像
     */
    private String avatar;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic()
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