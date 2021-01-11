package com.danbro.enity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 订单(TOrder)实体类
 *
 * @author makejava
 * @since 2021-01-08 13:51:10
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TOrder implements Serializable {
    private static final long serialVersionUID = 162845033241560135L;
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseTitle;
    /**
     * 课程封面
     */
    private String courseCover;
    /**
     * 讲师名称
     */
    private String teacherName;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 会员手机
     */
    private String mobile;
    /**
     * 订单金额（分）
     */
    private BigDecimal totalFee;
    /**
     * 支付类型（1：微信 2：支付宝）
     */
    private Integer payType;
    /**
     * 订单状态（0：未支付 1：已支付）
     */
    private Boolean status;
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