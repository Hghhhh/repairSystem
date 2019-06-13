package com.banzhuan.bankendservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String account;

    @Column
    private String password;

}
