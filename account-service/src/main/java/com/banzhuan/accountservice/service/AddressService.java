package com.banzhuan.accountservice.service;

import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("backend-service")
public interface AddressService {

    @GetMapping
    Result<List<Address>> getAddress();
}
