package com.danbro.order.entity;

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
 * 支付日志表(TPayLog)实体类
 *
 * @author makejava
 * @since 2021-01-08 13:51:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TPayLog implements Serializable {

    private static final long serialVersionUID = 8650101845509384024L;

    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付完成时间
     */
    private Date payTime;
    /**
     * 支付金额（分）
     */
    private BigDecimal totalFee;
    /**
     * 交易流水号
     */
    private String transactionId;
    /**
     * 交易状态
     */
    private String tradeState;
    /**
     * 支付类型（1：微信 2：支付宝）
     */
    private Integer payType;
    /**
     * 其他属性
     */
    private String attr;
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