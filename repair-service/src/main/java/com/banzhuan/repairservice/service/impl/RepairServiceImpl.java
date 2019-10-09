package com.banzhuan.repairservice.service.impl;

import com.banzhuan.repairservice.dao.RepairDao;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService{

    @Autowired
    private RepairDao repairDao;

    @Override
    public Repair addRepair(Repair repair) {
        return repairDao.save(repair);
    }

    @Override
    public List<Repair> getRepairByApplicantId(Integer applicantId) {
        return repairDao.findByApplicantId(applicantId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        repairDao.deleteById(id);
    }

    @Override
    public List<Repair> getRepairByRepairmanId(Integer repairmanId) {
        return repairDao.findByRepairmanId(repairmanId);
    }

    @Override
    public List<Repair> getAllRepair() {
        return repairDao.findAll();
    }

    /*
    查找待维修的维修单
     */
    @Override
    public List<Repair> getRepairByAddressId(List<Integer> addressIds) {
        return repairDao.findByStateAndAddressIdIn(0,addressIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer repairManGetRepair(Integer repairId, Integer repairmanId) {
        return repairDao.repairManGetRepair(repairId,repairmanId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer finshRepair(Integer repairId) {
        return repairDao.finishRepair(repairId);
    }
}
