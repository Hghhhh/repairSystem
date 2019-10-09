package com.banzhuan.accountservice.controller;

import com.banzhuan.accountservice.dto.Result;
import com.banzhuan.accountservice.entity.RepairMan;
import com.banzhuan.accountservice.service.RepairManService;
import com.banzhuan.accountservice.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-service")
public class RepairManController {

    @Autowired
    private RepairManService repairManService;

    @GetMapping("repairmans")
    public Result<List<RepairMan>> getAll(){
        return Result.success(repairManService.findAll());
    }

    @GetMapping("repairman/{number}")
    public Result<RepairMan> getById(@PathVariable("number") String number){
        return Result.success(repairManService.findByNumber(number));
    }

    @PostMapping("repairman")
    public Result<RepairMan> update(@RequestParam String repairMan){
        RepairMan repairMan1 = JacksonUtil.json2pojo(repairMan,RepairMan.class);
        return Result.success(repairManService.save(repairMan1));
    }

    @DeleteMapping("repairman/{number}")
    public Result<Void> delete(@PathVariable("number") String number){
        repairManService.delete(number);
        return Result.success(null);
    }
}
