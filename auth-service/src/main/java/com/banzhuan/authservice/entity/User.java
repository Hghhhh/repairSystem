package com.banzhuan.authservice.entity;

import lombok.Data;


@Data
public class User {


    private int id;

    private String openId;

    private Role role;

    private String number;

    private String name;

    private String telphone;

    private Integer dormitoryId;



}
