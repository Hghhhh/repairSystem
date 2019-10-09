package com.banzhuan.accountservice.repository;

import com.banzhuan.accountservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AccountDao extends JpaRepository<User, Integer> {

    User findById(int id);
}
