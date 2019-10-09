package com.banzhuan.repairservice.service.impl;

import com.banzhuan.repairservice.dao.RepairDao;
import com.banzhuan.repairservice.dto.RepairStaticDto;
import com.banzhuan.repairservice.entity.Repair;
import com.banzhuan.repairservice.service.RepairService;
import com.banzhuan.repairservice.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
    public List<Repair> getRepairByRepairmanId(String repairmanId) {
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

    @Override
    public List<Repair> findByStateAndRepairmanIdAndAddressId(Integer state, String repairmanId, Integer addressId) {
        return repairDao.findByStateAndRepairmanIdAndAddressId(state,repairmanId,addressId);
    }

    @Override
    public List<RepairStaticDto> findByAddressIdAndAppointmentTimeBetween(Integer addressId, int beginT, int endT) {
        List<Repair> repairs =  repairDao.findByAddressIdAndAppointmentTimeBetween(addressId,beginT,endT);
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=1;i<=12;i++){
            map.put(i,0);
        }
        for(Repair repair : repairs){
            int month = TimeUtil.getMonth(repair.getAppointmentTime());
            Integer count = map.get(month);
            if(count==null){
                count = 0;
            }
            count++;
            map.put(month,count);
        }
        List<RepairStaticDto> repairStaticDtos = new ArrayList<>();
        map.entrySet().forEach(v->{
            RepairStaticDto repairStaticDto = new RepairStaticDto();
            repairStaticDto.setCount(v.getValue());
            repairStaticDto.setMonth(v.getKey());
            repairStaticDtos.add(repairStaticDto);
        });

        repairStaticDtos.sort((o1,o2)->{
            return o1.getMonth()-o2.getMonth();
        });
        return repairStaticDtos;
    }


}
