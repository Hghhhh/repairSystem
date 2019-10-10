package com.banzhuan.repairservice.dao;

import com.banzhuan.repairservice.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepairDao extends JpaRepository<Repair,Integer> {

    List<Repair> findByApplicantId(String applicantId);

    List<Repair> findByRepairmanId(String repairmanId);

    List<Repair> findByStateAndAddressIdIn(Integer state,List<Integer> addressId);

    List<Repair> findByStateAndRepairmanIdAndAddressId(Integer state,String repairmanId,Integer addressId);

    @Modifying
    @Query(value = "update tb_repair r set r.repairmanId=?2,r.state=1,r.repairTime=unix_timestamp(now()) where r.id=?1",
            nativeQuery = true)
    Integer repairManGetRepair(Integer id,String repairmanId);


    @Modifying
    @Query(value = "update tb_repair r set r.state=2,repairedTime=unix_timestamp(now()) where r.id=?1",
            nativeQuery = true)
    Integer finishRepair(Integer id);


    List<Repair> findByAddressIdAndAppointmentTimeBetween(Integer addressId,int beginT,int endTime);



}
