package com.banzhuan.accountservice.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@DynamicInsert
@DynamicUpdate
@Data //lomback注解，自动生成setter和getter
public class User {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    //微信登陆后获得的唯一标识
    @Column(name="openId")
    private String openId;

    //这里每个用户只有一个角色
    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    @Column(name="number")
    private String number;

    @Column(name="name")
    private String name;

    @Column(name="telphone",columnDefinition="CHAR")
    private String telphone;

    @Column(name="dormitoryId")
    private Integer dormitoryId;

}
