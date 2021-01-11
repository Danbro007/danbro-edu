package com.danbro.order.dto;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname WeChatPayReturnDto
 * @Description TODO
 * @Date 2021/1/11 18:03
 * @Created by Administrator
 */
@AllArgsConstructor
@Accessors(chain = true)
@Data
@NoArgsConstructor
@Builder
public class WeChatPayReturnDto {
    @Alias("out_trade_no")
    private String outTradeNo;
    private String courseId;
    private String totalFee;
    private String resultCode;
    private String codeUrl;
}
