package com.banzhuan.accountservice.service.impl;

import com.banzhuan.accountservice.entity.RepairMan;
import com.banzhuan.accountservice.repository.RepairManDao;
import com.banzhuan.accountservice.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairManServiceImpl  implements RepairManService {
    @Autowired
    private RepairManDao repairManDao;

    @Override
    public List<RepairMan> findAll() {
        return repairManDao.findAll();
    }

    @Override
    public RepairMan findByNumber(String number) {
        Optional<RepairMan> res =  repairManDao.findById(number);
        return res.orElse(null);
    }

    @Override
    public RepairMan save(RepairMan repairMan) {
        return repairManDao.save(repairMan);
    }

    @Override
    public void delete(String number) {
        repairManDao.deleteById(number);
    }
}
