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
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0代表宿舍，1代表教室',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_address` */

insert  into `tb_address`(`id`,`location`,`type`) values (1,'西一',1),(2,'西二',1),(3,'西三',1),(4,'西四',0);

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '0普通管理员；1超级管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`account`,`password`,`state`) values (1,'admin','e98a24c95920715c3dd6900aa17d0e04021610398955005e',1),(2,'admin','18249c988f29d2695ac6dd23e3d39334b824551a8818d101',1),(3,'admin','514862e4b16c21bd1351ea9394d09c62017015382350ee06',1),(4,'admin','b52a4db18d5341e20207868226d93e68a991f38c4cd3ef8d',1);

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
  `applicantId` varchar(11) NOT NULL COMMENT '报修人id',
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tb_repair` */

insert  into `tb_repair`(`id`,`applicantId`,`applicantName`,`telphone`,`addressId`,`address`,`reason`,`appointmentTime`,`repairmanId`,`repairTime`,`repairedTime`,`state`,`pictures`) values (16,'13456785636','这是','13456785636',1,'西二547','阳台灯坏了',1570675994,'13456785636',1570677942,1570677241,1,NULL);

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

insert  into `tb_repairman`(`number`,`telphone`,`name`,`addressIds`) values ('12323333331','13456785636','32444324444zs',NULL),('123456','13724125552','hghsg','1');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `telphone` char(11) NOT NULL COMMENT '电话',
  `number` varchar(20) DEFAULT NULL COMMENT '学号或职工号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `dormitoryId` int(11) DEFAULT NULL COMMENT '宿舍楼Id',
  `dormitoryNum` int(11) DEFAULT NULL COMMENT '宿舍号',
  PRIMARY KEY (`telphone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`telphone`,`number`,`name`,`dormitoryId`,`dormitoryNum`) values ('12345678901','111111','zhangsan',111,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
