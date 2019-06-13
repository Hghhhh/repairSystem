package com.banzhuan.accountservice.repository;

import com.banzhuan.accountservice.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ResourceDao extends JpaRepository<Resource,Integer>{
    @Modifying
    @Query("update Resource d set " +
            " d.url = CASE WHEN ?2 IS NULL THEN d.url ELSE ?2 END , " +
            " d.description = CASE WHEN ?3 IS NULL THEN d.description ELSE ?3 END " +
            " where d.id = ?1")
    Integer updateResource(int id,String url,String descriptionn);
}
