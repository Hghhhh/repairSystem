package com.banzhuan.repairservice.service;

import com.banzhuan.repairservice.dto.RepairStaticDto;
import com.banzhuan.repairservice.entity.Repair;

import java.util.List;


public interface RepairService {

    Repair addRepair(Repair repair);

    List<Repair> getRepairByApplicantId(String applicantId);

    void deleteById(Integer id);

    List<Repair> getRepairByRepairmanId(String repairmanId);

    List<Repair> getAllRepair();

    List<Repair> getRepairByAddressId(List<Integer> addressIds);

    Integer  repairManGetRepair(Integer repairId,String repairmanId);

    Integer finshRepair(Integer repairId);

    List<Repair> findByStateAndRepairmanIdAndAddressId(Integer state,String repairmanId,Integer addressId);

    List<RepairStaticDto> findByAddressIdAndAppointmentTimeBetween(Integer addressId, int beginT, int endT);
}
