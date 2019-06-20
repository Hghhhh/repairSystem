/*
SQLyog Enterprise Trial - MySQL GUI v7.11 
MySQL - 5.7.26-log : Database - repair_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`account`,`password`) values (1,'admin','13178b04019003898373415412302948c46cb56285d5999d');

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
  `addressId` int(11) NOT NULL COMMENT '报修地址',
  `reason` varchar(200) NOT NULL,
  `appointmentTime` timestamp NULL DEFAULT NULL COMMENT '预约时间',
  `repairTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报修时间',
  `repairedTime` timestamp NULL DEFAULT NULL COMMENT '报修完成时间',
  `applicantId` int(11) NOT NULL COMMENT '报修人id',
  `repairmanId` int(11) DEFAULT NULL COMMENT '修理人id',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '0表示待修理，1表示正在修理，2表示修理完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `tb_repair` */

insert  into `tb_repair`(`id`,`addressId`,`reason`,`appointmentTime`,`repairTime`,`repairedTime`,`applicantId`,`repairmanId`,`state`) values (2,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,0,0),(3,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,1,0),(4,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,0,0),(5,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,0,0),(6,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(7,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(8,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(9,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(10,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(11,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(12,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,1,1),(13,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,NULL,0),(14,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,5,2),(15,1,'fun12131232k','2019-08-11 00:00:00',NULL,NULL,123,2,1);

/*Table structure for table `tb_repairman` */

DROP TABLE IF EXISTS `tb_repairman`;

CREATE TABLE `tb_repairman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(30) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `telphone` char(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_repairman` */

/*Table structure for table `tb_repairman_address` */

DROP TABLE IF EXISTS `tb_repairman_address`;

CREATE TABLE `tb_repairman_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `repairmanId` int(11) NOT NULL,
  `addressId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_repairman_address` */

/*Table structure for table `tb_resource` */

DROP TABLE IF EXISTS `tb_resource`;

CREATE TABLE `tb_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(50) NOT NULL COMMENT '资源url',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tb_resource` */

insert  into `tb_resource`(`id`,`url`,`description`) values (3,'/repair','报修'),(4,'/address','地址管理'),(5,'/users/*','用户信息管理');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`) values (1,'student'),(2,'teacher'),(3,'repairman'),(4,'tourists'),(5,'admin111');

/*Table structure for table `tb_role_resource` */

DROP TABLE IF EXISTS `tb_role_resource`;

CREATE TABLE `tb_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_resource` */

insert  into `tb_role_resource`(`id`,`roleId`,`resourceId`) values (1,1,1),(2,2,3),(3,1,4);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `openId` varchar(40) NOT NULL COMMENT '微信登录得到的用户唯一标识',
  `number` varchar(20) DEFAULT NULL COMMENT '学号或职工号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `telphone` char(11) DEFAULT NULL,
  `dormitoryId` int(11) DEFAULT NULL COMMENT '宿舍Id',
  `roleId` int(11) DEFAULT NULL COMMENT '角色Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `OPENID` (`openId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`openId`,`number`,`name`,`telphone`,`dormitoryId`,`roleId`) values (1,'123','111111','zhangsan','12345678901',111,1),(2,'12333',NULL,NULL,NULL,0,3),(3,'1234','111111','zhangsan',NULL,111,1),(4,'111','123','xiaoming',NULL,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
