package com.banzhuan.repairservice.service.impl;

import com.banzhuan.repairservice.dao.RepairDao;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl implements RepairService{

    @Autowired
    private RepairDao repairDao;

    @Override
    public Integer addRepair(Repair repair) {
        return repairDao.save(repair).getId();
    }
}
