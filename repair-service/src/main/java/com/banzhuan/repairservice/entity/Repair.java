package com.banzhuan.repairservice.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_repair")
@Data
@DynamicInsert
@DynamicUpdate
public class Repair {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String reason;

    //预约时间
    private Integer appointmentTime;

    //报修时间
    private Integer repairTime;

    //报修完成时间
    private Integer repairedTime;

    //报修人的id
    private String applicantId;

    //维修人的number
    private String repairmanId;

    //0表示待修理，1表示正在修理，2表示修理完成，3无法报修
    @Column(name = "state",columnDefinition = "TINYINT")
    private Integer state;

    //报修人姓名
    private String applicantName;

    //报修人电话
    @Column(name = "telphone",columnDefinition = "CHAR")
    private String telphone;

    //报修地址
    private String address;

    //图片
    private String pictures;

    //报修地址id
    private Integer addressId;

}
