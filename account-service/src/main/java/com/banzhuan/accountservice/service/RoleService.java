package com.banzhuan.accountservice.service;

import com.banzhuan.accountservice.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRole();

    Role saveRole(Role role);

}
