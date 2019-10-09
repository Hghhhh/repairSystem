package com.banzhuan.repairservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private int repairId;

    private int start;

    private String content;
}
