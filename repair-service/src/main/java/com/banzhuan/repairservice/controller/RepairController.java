package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.Result;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping
    public Result<Integer> addRepair(@RequestBody Repair repair){
        return Result.success(repairService.addRepair(repair));
    }



}
