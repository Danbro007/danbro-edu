package com.danbro.msm.dto;

import com.danbro.anotation.IsMobile;
import lombok.Data;

/**
 * @Classname PhoneNumDto
 * @Description TODO
 * @Date 2021/1/5 15:17
 * @Author Danrbo
 */
@Data
public class PhoneNumDto {

    @IsMobile
    private String mobile;
}
