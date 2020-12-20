package com.danbro.edu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程视频(EduVideo)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Data
public class EduVideo implements Serializable {
    private static final long serialVersionUID = -78057855804550634L;
    /**
     * 视频ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 章节ID
     */
    private String chapterId;
    /**
     * 节点名称
     */
    private String title;
    /**
     * 云端视频资源
     */
    private String videoSourceId;
    /**
     * 原始文件名称
     */
    private String videoOriginalName;
    /**
     * 排序字段
     */
    private Object sort;
    /**
     * 播放次数
     */
    private Object playCount;
    /**
     * 是否可以试听：0收费 1免费
     */
    private Object isFree;
    /**
     * 视频时长（秒）
     */
    private Object duration;
    /**
     * Empty未上传 Transcoding转码中  Normal正常
     */
    private String status;
    /**
     * 视频源文件大小（字节）
     */
    private Object size;
    /**
     * 乐观锁
     */
    private Object version;
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