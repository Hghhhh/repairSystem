package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.CodeMsg;
import com.banzhuan.repairservice.dto.Result;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping("/user")
    public Result<Integer> addRepair(@RequestBody Repair repair){
        return Result.success(repairService.addRepair(repair));
    }


    @DeleteMapping("/user")
    public Result<Integer> deleteRepair(@RequestParam Integer repairId){
        if(repairService.deleteById(repairId)==0){
            return  Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
        return Result.success(1);
    }

    @GetMapping("/user/{applicantId}")
    public Result<List<Repair>> getRepairByApplicantId(@PathVariable(value = "applicantId") Integer applicantId){
        return Result.success(repairService.getRepairByApplicantId(applicantId));
    }


    @GetMapping("/backend/{repairmanId}")
    public Result<List<Repair>> getRepairByRepairmanId(@RequestParam @PathVariable(value = "repairmanId") Integer repairmanId){
        return Result.success(repairService.getRepairByRepairmanId(repairmanId));
    }

    @GetMapping("/backend")
    public Result<List<Repair>> getAllRepair(){
        return Result.success(repairService.getAllRepair());
    }

    @PostMapping("/repairman")
    public  Result<List<Repair>> getAllRepairByAddressId(@RequestBody List<Integer> addressIds) {
        return Result.success(repairService.getRepairByAddressId(addressIds));
    }

    @PutMapping("/repairman/doRepair")
    public  Result<Integer> repairManGetRepair(@RequestParam  Integer repairId,@RequestParam Integer repairmanId) {
        if(repairService.repairManGetRepair(repairId,repairmanId)==0){
            return  Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
        return Result.success(1);
    }


    @PutMapping("/repairman/finishRepair")
    public  Result<Integer> repairManfinishRepair(@RequestParam  Integer repairId,@RequestParam Integer repairmanId) {
        if(repairService.reapairManFinshRepair(repairId,repairmanId)==0){
            return  Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
        return Result.success(1);
    }


}
