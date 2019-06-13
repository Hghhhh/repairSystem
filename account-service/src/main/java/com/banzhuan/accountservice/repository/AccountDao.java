package com.banzhuan.accountservice.repository;

import com.banzhuan.accountservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AccountDao extends JpaRepository<User, String> {

    User findByOpenId(String openId);


    //如果是教师或者是维修人员，dormitoryId填0即可
    @Modifying
    @Query(value = "update tb_user u, tb_role r set u.number=?1,u.name=?2,u.dormitoryId=?3,u.roleId=r.id where u.openId=?5 and r.name=?4",
            nativeQuery = true)
    Integer updateUserInfo(String number,String name,Integer dormitoryId,String roleName,String openId);

    @Modifying
    @Query(value = "update tb_user u set u.telphone=?1 where u.openId=?2",
            nativeQuery = true)
    Integer updateTelPhone(String telPhone,String openId);



}
