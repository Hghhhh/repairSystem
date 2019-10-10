package com.banzhuan.bankendservice.controller;


import com.banzhuan.bankendservice.dto.Result;
import com.banzhuan.bankendservice.entity.Address;
import com.banzhuan.bankendservice.service.AddressService;
import com.banzhuan.bankendservice.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend-service/address")
@RefreshScope
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public Result<List<Address>> getAddress(){
        return Result.success(addressService.getAllAddress());
    }

    @PostMapping
    public Result<Integer> insertAddress(@RequestBody String address){
        Address address1 = JacksonUtil.json2pojo(address,Address.class);
        addressService.insertAddress(address1);
        return Result.success(1);
    }

}
