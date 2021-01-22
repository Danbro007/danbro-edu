package com.danbro.edu.rpcClient;

import com.danbro.enums.Result;
import com.danbro.vo.MemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author liweimo
 */
@Component
@FeignClient(name = "service-user-center", fallback = UCenterUserFallBack.class)
public interface UCenterUserClient {
    /**
     * 远程调用 center-user 服务通过会员ID获取会员的信息
     *
     * @param memberId 会员ID
     * @return 会员信息
     */
    @GetMapping("user/info/{memberId}")
    Result<MemberVo> getMemberInfoByMemberId(@PathVariable String memberId);
}
