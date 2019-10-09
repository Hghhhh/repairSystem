package com.banzhuan.bankendservice.controller;


import com.banzhuan.bankendservice.dto.Result;
import com.banzhuan.bankendservice.entity.Address;
import com.banzhuan.bankendservice.service.AddressService;
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
    public Result<Integer> insertAddress(@RequestBody Address address){
        addressService.insertAddress(address);
        return Result.success(1);
    }

}
