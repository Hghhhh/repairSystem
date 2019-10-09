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

    public User getUser(int id) {
        return accountDao.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User save(User user) {
        return accountDao.save(user);
    }


}
