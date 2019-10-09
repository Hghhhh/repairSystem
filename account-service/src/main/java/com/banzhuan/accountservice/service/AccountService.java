package com.banzhuan.accountservice.service;


import com.banzhuan.accountservice.entity.User;

public interface AccountService {
    User getUser(int id);

    User save(User user);
}
