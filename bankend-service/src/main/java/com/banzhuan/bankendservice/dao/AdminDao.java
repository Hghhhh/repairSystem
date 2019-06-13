package com.banzhuan.bankendservice.dao;


import com.banzhuan.bankendservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,Integer>{

    Admin findAdminByAccount(String account);
}
