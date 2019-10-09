package com.banzhuan.bankendservice.service;

import com.banzhuan.bankendservice.entity.Admin;

public interface AdminService {
    Admin login(String account, String password);

    Admin saveAdmin(Admin admin);

}
