/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16-log : Database - vim
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_frends` */

CREATE TABLE `t_frends` (
  `user_id` bigint(20) NOT NULL,
  `frend_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`frend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_frends` */

/*Table structure for table `t_group` */

CREATE TABLE `t_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) DEFAULT NULL,
  `group_desc` varchar(200) DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_group` */

/*Table structure for table `t_request_user` */

CREATE TABLE `t_request_user` (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` bigint(20) DEFAULT NULL COMMENT '发起请求的USER',
  `from_status` int(11) DEFAULT NULL COMMENT '消息状态,0:未读，1：已读',
  `to_user` bigint(20) DEFAULT NULL COMMENT '接收请求的USER',
  `to_status` int(11) DEFAULT NULL COMMENT '消息状态,0:未读，1：已读',
  `r_result` int(11) DEFAULT NULL COMMENT '请求结果，0：未通过，2：通过',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_request_user` */

insert  into `t_request_user`(`r_id`,`from_user`,`from_status`,`to_user`,`to_status`,`r_result`,`create_time`) values (1,1,1,2,0,NULL,'2017-08-09 23:58:33');

/*Table structure for table `t_user` */

CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`username`,`password`) values (1,'zdc','123');
insert  into `t_user`(`user_id`,`username`,`password`) values (2,'jxp','123');

/*Table structure for table `t_user_group` */

CREATE TABLE `t_user_group` (
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_group` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
