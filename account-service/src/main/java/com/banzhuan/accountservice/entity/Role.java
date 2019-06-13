package com.banzhuan.accountservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_role")
@Data
public class Role {


    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    /**
     * 角色拥有权限的资源集合
     */
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name = "tb_role_resource",joinColumns = @JoinColumn(name = "roleId"),inverseJoinColumns = @JoinColumn(name = "resourceId"))
    private Set<Resource> resources  = new HashSet<>();

}
