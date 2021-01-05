package com.danbro.msm.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Classname MsmUtils
 * @Description TODO
 * @Date 2021/1/5 15:57
 * @Author Danrbo
 */
@Data
public class MsmUtils {
    @Value("${aliyun.msm.product}")
    private String product;

    @Value("${aliyun.msm.domain}")
    private String domain;

    @Value("${aliyun.msm.message.code}")
    private String messageCode;

    @Value("${aliyun.msm.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.msm.accessKeySecret}")
    private String accessKeySecret;


}
