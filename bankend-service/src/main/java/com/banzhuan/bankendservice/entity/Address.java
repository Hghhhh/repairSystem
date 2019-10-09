package com.banzhuan.bankendservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String location;

    @Column
    private int type;

}
