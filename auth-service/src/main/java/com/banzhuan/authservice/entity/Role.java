package com.banzhuan.authservice.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Role {
    private int id;

    private String name;

    /**
     * 角色拥有权限的资源集合
     */
    private Set<Resource> resources  = new HashSet<>();

}
