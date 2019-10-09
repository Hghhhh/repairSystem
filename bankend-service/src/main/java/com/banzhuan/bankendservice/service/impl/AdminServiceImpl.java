package com.banzhuan.bankendservice.service.impl;


import com.banzhuan.bankendservice.dao.AdminDao;
import com.banzhuan.bankendservice.entity.Admin;
import com.banzhuan.bankendservice.service.AdminService;
import com.banzhuan.bankendservice.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin login(String account, String password) {
        Admin admin = adminDao.findAdminByAccount(account);
        if(admin==null || !PasswordUtil.verify(password,admin.getPassword())){
            return null;
        }else{
            admin.setPassword(null);
            return admin;
        }
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        //加盐加密
        admin.setPassword(PasswordUtil.generate(admin.getPassword()));
        return adminDao.save(admin);
    }
}
