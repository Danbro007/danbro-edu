package com.danbro.msm.param;

import com.danbro.anotation.IsMobile;
import lombok.Data;

/**
 * @Classname PhoneNumDto
 * @Description TODO
 * @Date 2021/1/5 15:17
 * @Author Danrbo
 */
@Data
public class PhoneNumParam {

    @IsMobile
    private String mobile;
}
