/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.26-log : Database - repair_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`repair_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `repair_system`;

/*Table structure for table `tb_address` */

DROP TABLE IF EXISTS `tb_address`;

CREATE TABLE `tb_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(20) NOT NULL COMMENT '地址',
  `type` int(1) NOT NULL COMMENT '0代表宿舍，1代表教室',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_address` */

insert  into `tb_address`(`id`,`location`,`type`) values (1,'123',1);

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0普通管理员；1超级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`account`,`password`,`state`) values (1,'admin','13178b04019003898373415412302948c46cb56285d5999d',0);

/*Table structure for table `tb_comment` */

DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repairId` int(11) NOT NULL,
  `start` int(11) NOT NULL DEFAULT '5',
  `content` varchar(200) DEFAULT '该用户没有填写评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_comment` */

/*Table structure for table `tb_repair` */

DROP TABLE IF EXISTS `tb_repair`;

CREATE TABLE `tb_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applicantId` int(11) NOT NULL COMMENT '报修人id',
  `applicantName` varchar(50) NOT NULL COMMENT '报修人姓名',
  `telphone` char(11) NOT NULL COMMENT '手机号',
  `addressId` int(11) NOT NULL COMMENT '报修地址id',
  `address` varchar(100) NOT NULL COMMENT '报修地址',
  `reason` varchar(200) NOT NULL COMMENT '报修理由',
  `appointmentTime` int(11) DEFAULT NULL COMMENT '预约时间',
  `repairmanId` varchar(50) DEFAULT NULL COMMENT '修理人id',
  `repairTime` int(11) DEFAULT NULL COMMENT '报修时间',
  `repairedTime` int(11) DEFAULT NULL COMMENT '报修完成时间',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0表示待修理，1表示正在修理，2表示修理完成,3表示无法修理',
  `pictures` varchar(512) DEFAULT NULL COMMENT '报修图片，用逗号隔开',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_repair` */

/*Table structure for table `tb_repairman` */

DROP TABLE IF EXISTS `tb_repairman`;

CREATE TABLE `tb_repairman` (
  `number` varchar(30) NOT NULL,
  `telphone` char(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `addressIds` varchar(512) DEFAULT NULL COMMENT '负责的宿舍楼id',
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_repairman` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `number` varchar(20) DEFAULT NULL COMMENT '学号或职工号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `telphone` char(11) DEFAULT NULL COMMENT '电话',
  `dormitoryId` int(11) DEFAULT NULL COMMENT '宿舍楼Id',
  `dormitoryNum` int(11) DEFAULT NULL COMMENT '宿舍号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`number`,`name`,`telphone`,`dormitoryId`,`dormitoryNum`) values (1,'111111','zhangsan','12345678901',111,NULL),(2,NULL,NULL,NULL,0,NULL),(3,'111111','zhangsan',NULL,111,NULL),(4,'123','xiaoming',NULL,1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
