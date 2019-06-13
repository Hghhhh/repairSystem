package com.banzhuan.accountservice.service.impl;

import com.banzhuan.accountservice.entity.Role;
import com.banzhuan.accountservice.repository.RoleDao;
import com.banzhuan.accountservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRole() {
        return roleDao.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }
}
