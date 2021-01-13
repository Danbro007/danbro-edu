package com.danbro.edu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 课程(EduCourse)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Data
public class EduCourse implements Serializable {
    /**
     * 课程发布状态：未发布
     */
    public final static String DRAFT = "Draft";
    /**
     * 课程发布状态：已发布
     */
    public final static String NORMAL = "Normal";

    private static final long serialVersionUID = 948581254198357791L;
    /**
     * 课程ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 课程讲师IDa
     */
    private String teacherId;
    /**
     * 课程专业ID
     */
    private String subjectId;
    /**
     * 课程专业父级ID
     */
    private String subjectParentId;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 课程销售价格，设置为0则可免费观看
     */
    private BigDecimal price;
    /**
     * 总课时
     */
    private Integer lessonNum;
    /**
     * 课程封面图片路径
     */
    private String cover;
    /**
     * 销售数量
     */
    private Integer buyCount;
    /**
     * 浏览数量
     */
    private Long viewCount;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    private String status;
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