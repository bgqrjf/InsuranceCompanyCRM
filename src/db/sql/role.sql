# Host: localhost  (Version: 5.5.15)
# Date: 2018-03-22 17:59:57
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增逐渐',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '角色id',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色姓名',
  `role_pwd` varchar(255) NOT NULL DEFAULT '' COMMENT '角色密码',
  `is_delete` char(2) NOT NULL DEFAULT '1' COMMENT '逻辑删除字段 0删除 1激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,1,'admin','123','1');
