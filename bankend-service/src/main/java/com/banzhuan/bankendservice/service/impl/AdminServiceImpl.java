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
    public Boolean login(String account, String password) {
        Admin admin = adminDao.findAdminByAccount(account);
        if(admin==null){
            return false;
        }else{
            //验证密码
            return PasswordUtil.verify(password,admin.getPassword());
        }
    }

    @Override
    public Admin insertAdmin(Admin admin) {
        //加盐加密
        admin.setPassword(PasswordUtil.generate(admin.getPassword()));
        return adminDao.save(admin);
    }
}
