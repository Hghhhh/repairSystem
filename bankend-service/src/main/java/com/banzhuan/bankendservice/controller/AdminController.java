package com.banzhuan.bankendservice.controller;

import com.banzhuan.bankendservice.dto.CodeMsg;
import com.banzhuan.bankendservice.dto.Result;
import com.banzhuan.bankendservice.entity.Admin;
import com.banzhuan.bankendservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/backend-service")
@RefreshScope
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminLogin")
    public Result<Admin> login(@RequestParam String account, @RequestParam String password){
        Admin admin = adminService.login(account,password);
        if(admin != null){
            return Result.success(admin);
        }else{
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }

    @PostMapping("/adminAccountSave")
    public Result<Admin> saveAdminAccount(Integer adminId,@RequestParam String account, @RequestParam String password){
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setAccount(account);
        admin.setPassword(password);
        return Result.success(adminService.saveAdmin(admin));
    }
}
