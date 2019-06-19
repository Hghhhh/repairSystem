package com.banzhuan.bankendservice.service;

import com.banzhuan.bankendservice.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="AUTH-SERVICE",fallback = DataFallBack.class)
public interface AuthService {
    @PostMapping("/auth/apply-tokenByAdmin")
    public Result<String> applyTokenForAdmin(@RequestParam(name = "clientKey") String clientKey, @RequestParam(name = "mix") String mix, @RequestParam(name = "timeStamp") String timeStamp, @RequestParam(name = "digest") String digest);

}
