package com.banzhuan.repairservice.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_repair")
@Data
public class Repair {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String reason;

    //预约时间
    @Column(columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentTime;

    //报修时间
    @Column(columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairTime;

    //报修完成时间
    @Column(columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairedTime;

    //报修人的openId
    private Integer applicantId;

    //维修人的openId
    private Integer repairmanId;

    //0表示待修理，1表示正在修理，2表示修理完成，3无法报修
    private int state;

    //报修人姓名
    private String applicantName;

    //报修人电话
    private String telphone;

    //报修地址
    private String address;

    //图片
    private String pictures;

    //报修地址id
    private Integer addressId;

}
