package com.banzhuan.repairservice.controller;

import com.banzhuan.repairservice.dto.CodeMsg;
import com.banzhuan.repairservice.dto.RepairStaticDto;
import com.banzhuan.repairservice.dto.Result;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import com.banzhuan.repairservice.util.JacksonUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repair-service")
public class RepairController {

    @Autowired
    private RepairService repairService;

    private static final String beginOfYear = "-01-01 00:00:00";

    private static final String endOfYear = "-12-31 24:00:00";

    @PostMapping("/repair")
    public Result<Repair> addRepair(@RequestBody String repair){
        Repair repair1 = JacksonUtil.json2pojo(repair,Repair.class);
        repair1.setAppointmentTime((int) (System.currentTimeMillis()/1000));
        return Result.success(repairService.addRepair(repair1));
    }

    @GetMapping("/userRepair/{applicantId}")
    public Result<List<Repair>> getRepairByApplicantId(@PathVariable(value = "applicantId") Integer applicantId){
        return Result.success(repairService.getRepairByApplicantId(applicantId));
    }

    @GetMapping("/repairManRepair/{repairmanId}")
    public Result<List<Repair>> getRepairByRepairmanId(@PathVariable(value = "repairmanId") String repairmanId){
        return Result.success(repairService.getRepairByRepairmanId(repairmanId));
    }

    @GetMapping("/allRepairs")
    public Result<List<Repair>> getAllRepair(Integer state,String repairmanId,Integer addressId){
        return Result.success(repairService.findByStateAndRepairmanIdAndAddressId(state,repairmanId,addressId));
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

    @GetMapping("/repairStatic")
    public Result<List<RepairStaticDto>> repairStatic(@RequestParam int year,Integer addressId){
        if(year>9999){
            return Result.error(CodeMsg.PARAM_ERROR);
        }
        int beginT = (int) (Timestamp.valueOf(year+beginOfYear).getTime()/1000);
        int endT = (int) (Timestamp.valueOf(year+endOfYear).getTime()/1000);
        return Result.success(repairService.findByAddressIdAndAppointmentTimeBetween(addressId,beginT,endT));
    }


}
