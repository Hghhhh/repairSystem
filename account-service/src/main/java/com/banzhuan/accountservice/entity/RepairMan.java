package com.banzhuan.accountservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_repairman")
@Data //lomback注解，自动生成setter和getter
public class RepairMan {

    private String number;

    @Column(name="name")
    private String name;

    @Id
    @Column(name="telphone",columnDefinition="CHAR")
    private String telphone;

    private String addressIds;
}
