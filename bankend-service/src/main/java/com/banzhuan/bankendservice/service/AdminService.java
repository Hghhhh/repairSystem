package com.banzhuan.bankendservice.service;

import com.banzhuan.bankendservice.entity.Admin;

public interface AdminService {
    Boolean login(String account, String password);

    Admin insertAdmin(Admin admin);



}
