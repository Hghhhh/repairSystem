package com.banzhuan.accountservice.service.impl;

import com.banzhuan.accountservice.entity.User;
import com.banzhuan.accountservice.repository.AccountDao;
import com.banzhuan.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public User getUser(String openId) {
        return accountDao.findByOpenId(openId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User save(User user) {
        return accountDao.save(user);
    }

    @Transactional
    @Override
    public Integer updateUserInfo(@RequestParam String number,@RequestParam String name,@RequestParam Integer dormitoryId,@RequestParam String roleName,@RequestParam String openId) {
        return accountDao.updateUserInfo( number, name, dormitoryId, roleName, openId);
    }

    @Transactional
    @Override
    public Integer updateTelPhone(String telPhone, String openId) {
        return accountDao.updateTelPhone(telPhone,openId);
    }


}
