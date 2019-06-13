package com.banzhuan.accountservice.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tb_role_resource")
@Data
public class Role_Resource {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name="roleId",columnDefinition="INTEGER")
    private String roleId;

    @Column(name="resourceId",columnDefinition="INTEGER")
    private String resourceId;

}
