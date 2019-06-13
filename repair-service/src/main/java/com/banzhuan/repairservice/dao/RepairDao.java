package com.banzhuan.repairservice.dao;

import com.banzhuan.repairservice.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairDao extends JpaRepository<Repair,Integer>{
}
