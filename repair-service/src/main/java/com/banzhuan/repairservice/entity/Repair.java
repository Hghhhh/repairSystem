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

    private int addressId;

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
    private String applicantId;

    //维修人的openId
    private String repairmanId;

    //0表示待修理，1表示正在修理，2表示修理完成
    private int state;
}
