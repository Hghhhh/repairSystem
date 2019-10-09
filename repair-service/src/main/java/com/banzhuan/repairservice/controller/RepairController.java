package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.CodeMsg;
import com.banzhuan.repairservice.dto.Result;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import com.banzhuan.repairservice.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repair-service")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @PostMapping("/repair")
    public Result<Repair> addRepair(@RequestBody String repair){
        Repair repair1 = JacksonUtil.json2pojo(repair,Repair.class);
        return Result.success(repairService.addRepair(repair1));
    }

    @GetMapping("/userRepair/{applicantId}")
    public Result<List<Repair>> getRepairByApplicantId(@PathVariable(value = "applicantId") Integer applicantId){
        return Result.success(repairService.getRepairByApplicantId(applicantId));
    }

    @GetMapping("/repairManRepair/{repairmanId}")
    public Result<List<Repair>> getRepairByRepairmanId(@RequestParam @PathVariable(value = "repairmanId") Integer repairmanId){
        return Result.success(repairService.getRepairByRepairmanId(repairmanId));
    }

    @GetMapping("/allRepairs")
    public Result<List<Repair>> getAllRepair(){
        return Result.success(repairService.getAllRepair());
    }

    @GetMapping("/addressRepairs/{addressIds}")
    public  Result<List<Repair>> getAllRepairByAddressId(@PathVariable(value = "addressIds") String addressIds) {
        String[] addressIds1 = addressIds.split(",");
        List<Integer> addressIds2 = new ArrayList<>();
        for(int i=0;i<addressIds1.length;i++){
            addressIds2.add(Integer.valueOf(addressIds1[i]));
        }
        return Result.success(repairService.getRepairByAddressId(addressIds2));
    }

    @PostMapping("/doRepair")
    public  Result<Integer> repairManGetRepair(@RequestParam  Integer repairId,@RequestParam Integer repairmanId) {
        if(repairService.repairManGetRepair(repairId,repairmanId)==0){
            return  Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
        return Result.success(1);
    }


    @PutMapping("/finishRepair")
    public  Result<Integer> repairManfinishRepair(@RequestParam  Integer repairId) {
        if(repairService.finshRepair(repairId)==0){
            return  Result.error(CodeMsg.DOUBLE_DO_ERROR);
        }
        return Result.success(1);
    }

}
