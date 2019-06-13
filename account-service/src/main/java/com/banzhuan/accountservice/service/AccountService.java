package com.banzhuan.accountservice.service;


import com.banzhuan.accountservice.entity.User;

public interface AccountService {
    User getUser(String openId);

    User save(User user);

    Integer updateUserInfo(String number,String name,Integer dormitoryId,String roleName,String openId);

    Integer updateTelPhone(String telPhone,String openId);

}
