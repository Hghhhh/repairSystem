package com.banzhuan.accountservice.service;


import com.banzhuan.accountservice.entity.User;

public interface AccountService {
    User getUser(String number);

    User save(User user);
}
