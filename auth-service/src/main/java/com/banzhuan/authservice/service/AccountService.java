package com.banzhuan.authservice.service;

import com.banzhuan.authservice.dto.Result;
import com.banzhuan.authservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ACCOUNT-SERVICE",fallback = DataFallBack.class)
public interface AccountService {

    @GetMapping("/users/{openId}")
    Result<User> getUser(@PathVariable("openId") String openId);

}
