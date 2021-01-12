package com.danbro.statistic.typeEnum;

/**
 * @Classname QueryStatisticsEnum
 * @Description TODO 数据统计类型枚举类
 * @Date 2021/1/12 14:40
 * @Author Danrbo
 */
public enum QueryStatisticsEnum {
    /**
     * 数据统计类型枚举类
     */
    LOGIN("login_num"),
    REGISTER("register_num"),
    VIDEO_VIEW("video_view_num"),
    COURSE("course_num");

    private String type;

    QueryStatisticsEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
