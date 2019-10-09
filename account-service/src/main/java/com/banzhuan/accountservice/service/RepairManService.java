package com.banzhuan.accountservice.service;

import com.banzhuan.accountservice.entity.RepairMan;

import java.util.List;

public interface RepairManService {

    List<RepairMan> findAll();

    RepairMan findByNumber(String number);

    RepairMan save(RepairMan repairMan);

    void delete(String number);
}
