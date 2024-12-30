/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.27-MariaDB : Database - organicfoodstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`organicfoodstore` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `organicfoodstore`;

/*Table structure for table `categories` */

DROP TABLE IF EXISTS `categories`;

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `categories` */

insert  into `categories`(`category_id`,`category_name`) values 
(6,'dsafdf'),
(5,'asfdfdsdacvdf');

/*Table structure for table `complaints` */

DROP TABLE IF EXISTS `complaints`;

CREATE TABLE `complaints` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(50) DEFAULT NULL,
  `reply` varchar(50) DEFAULT NULL,
  `date_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `complaints` */

insert  into `complaints`(`complaint_id`,`user_id`,`complaint`,`reply`,`date_time`) values 
(1,1,'bad','sadfdsfad','356'),
(2,1,'nnsnd','pendimg','2023-02-03'),
(3,1,'bsnd','pending','2023-02-03');

/*Table structure for table `details` */

DROP TABLE IF EXISTS `details`;

CREATE TABLE `details` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `weight` varchar(100) DEFAULT NULL,
  `height` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `details` */

insert  into `details`(`detail_id`,`request_id`,`age`,`weight`,`height`) values 
(1,3,'25','70','170');

/*Table structure for table `diet` */

DROP TABLE IF EXISTS `diet`;

CREATE TABLE `diet` (
  `diet_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`diet_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `diet` */

insert  into `diet`(`diet_id`,`login_id`,`name`,`place`,`phone`,`email`) values 
(1,5,'achu','pala','7896541230','a@gmail.com');

/*Table structure for table `dietplan` */

DROP TABLE IF EXISTS `dietplan`;

CREATE TABLE `dietplan` (
  `diet_plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_id` int(11) DEFAULT NULL,
  `plan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`diet_plan_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `dietplan` */

insert  into `dietplan`(`diet_plan_id`,`request_id`,`plan`) values 
(1,1,'tyuityurtyutyurtyui');

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `item_name` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `stock` varchar(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `items` */

insert  into `items`(`item_id`,`store_id`,`category_id`,`item_name`,`description`,`image`,`stock`,`price`) values 
(1,1,6,'kguyf','yfufgy','static/uploads/cb50efb6-33c8-4950-b0c4-428ad2090507da2bc5c9f5ef7c1e42ffd69566d87c3b.jpg','12','150'),
(2,1,5,'add','dasd','static/uploads/cde44b55-2c19-47a3-8f76-6a0c3a3fa785rubiks-cube-digital-art-wallpaper.jpg','12','1500'),
(3,1,5,'dfedfa','dasd','static/uploads/56245124-362c-40c2-b38c-b0dd2757ce68d226e5099a6f2e10003580e38ca87c4e.jpg','12','1000'),
(4,3,6,'dfedfa','dasd','static/uploads/a97d3df7-4ab4-4f26-a849-dce83fc22672438463.png','15','180');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `usertype` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'user','user','user'),
(3,'store','store','store'),
(4,'grillcafe','grillcafe','store'),
(5,'achu','achu','diet');

/*Table structure for table `order_child` */

DROP TABLE IF EXISTS `order_child`;

CREATE TABLE `order_child` (
  `order_child_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_master_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `quantity` varchar(50) DEFAULT NULL,
  `amount` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_child_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `order_child` */

insert  into `order_child`(`order_child_id`,`order_master_id`,`item_id`,`quantity`,`amount`) values 
(1,1,1,'1','150'),
(2,2,1,'5','150'),
(3,2,2,'4','1500'),
(4,3,4,'2','180');

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `order_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` varchar(50) DEFAULT NULL,
  `date_time` varchar(50) DEFAULT NULL,
  `status` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`order_master_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `order_master` */

insert  into `order_master`(`order_master_id`,`store_id`,`user_id`,`total`,`date_time`,`status`) values 
(1,1,1,'150','q3e32','paid'),
(2,1,1,'5100','2023-02-02 23:59:52','pending'),
(3,3,1,'360','2023-02-03 00:01:45','Reviewed');

/*Table structure for table `payments` */

DROP TABLE IF EXISTS `payments`;

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_master_id` int(11) DEFAULT NULL,
  `date_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `payments` */

insert  into `payments`(`payment_id`,`order_master_id`,`date_time`) values 
(1,1,'445615'),
(2,3,'2023-02-03');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `order_master_id` int(11) DEFAULT NULL,
  `rate` varchar(150) DEFAULT NULL,
  `review` varchar(150) DEFAULT NULL,
  `date_time` varchar(52) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`order_master_id`,`rate`,`review`,`date_time`) values 
(1,1,1,'3','adsrfeqdfacxfwdf','32454656754'),
(2,1,3,'3.0','not bad','2023-02-03 01:33:41'),
(3,1,3,'3.0','not bad','2023-02-03 01:34:25'),
(4,1,3,'3.0','not bad','2023-02-03 01:35:52'),
(5,1,3,'4.0','Excellent','2023-02-03 01:36:41');

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `diet_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `request` */

insert  into `request`(`request_id`,`diet_id`,`user_id`,`date`,`status`) values 
(1,1,1,'2023-04-05 12:43:16','Accepted'),
(2,1,1,'2023-04-05 13:14:38','pending'),
(3,1,1,'2023-04-05 13:34:09','pending');

/*Table structure for table `stores` */

DROP TABLE IF EXISTS `stores`;

CREATE TABLE `stores` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `store_name` varchar(50) DEFAULT NULL,
  `image` varchar(1000) DEFAULT NULL,
  `building_name` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `pincode` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `stores` */

insert  into `stores`(`store_id`,`login_id`,`store_name`,`image`,`building_name`,`place`,`pincode`,`phone`,`email`,`status`) values 
(1,3,'asdfdf','static/uploads/31b78eb1-a58d-4231-bcc9-cbdce71e9661460083-eyes-dark-748x421.jpg','lkdfhkvfhg','sdfasdfads','794613','7410236589','pthalika8@gmail.com','active'),
(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(3,4,'dfaDFDAF','static/uploads/7f15b108-28d1-4413-8e26-c1d1f0de7a4drubiks-cube-digital-art-wallpaper.jpg','lkdfhkvfhg','DFSASDFASDF','794613','1230456789','dtdc@gmail.com','active');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `house_name` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `pincode` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `users` */

insert  into `users`(`user_id`,`login_id`,`first_name`,`last_name`,`house_name`,`place`,`landmark`,`pincode`,`phone`,`email`) values 
(1,2,'asdffsd','fdsgasd','gsadfgasd','gsfad','dfgasd','fgasd','gasdg','sdfga');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
