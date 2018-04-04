# Host: localhost  (Version: 5.5.15)
# Date: 2018-03-22 18:00:06
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "userinfo"
#

DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userinfo_id` varchar(50) NOT NULL DEFAULT '' COMMENT '客户id uuid',
  `userinfo_name` varchar(50) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `userinfo_age` int(11) DEFAULT NULL COMMENT '客户年龄',
  `userinfo_sex` int(3) NOT NULL DEFAULT '3' COMMENT '客户性别 1 男 2 女 3 未设置',
  `userinfo_address` varchar(200) NOT NULL DEFAULT '' COMMENT '所在地址',
  `userinfo_cname` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `userinfo_ctype` int(11) NOT NULL DEFAULT '0' COMMENT '公司类型',
  `userinfo_statement` int(11) NOT NULL DEFAULT '0' COMMENT '潜在用户状态',
  `userinfo_attribute` int(11) NOT NULL DEFAULT '0' COMMENT '客户属性 签约 未签约等',
  `userinfo_job` varchar(50) NOT NULL DEFAULT '' COMMENT '客户职业',
  `userinfo_salary` int(11) DEFAULT NULL COMMENT '客户年薪范围',
  `userinfo_from` int(11) NOT NULL DEFAULT '0' COMMENT '潜在客户来源',
  `userinfo_phone` varchar(50) DEFAULT NULL COMMENT '客户联系方式',
  `userinfo_level` int(11) DEFAULT NULL COMMENT '客户等级',
  `userinfo_date` varchar(50) DEFAULT NULL COMMENT '下次联系日期',
  `userinfo_desc` varchar(255) DEFAULT NULL COMMENT '客户描述',
  `userinfo_isdelete` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除  0 删除 1 潜在用户 2 正式用户',
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "userinfo"
#

INSERT INTO `userinfo` VALUES ('1','张三',11,3,'',NULL,0,0,0,'',NULL,0,NULL,NULL,NULL,NULL,1),('2','张三',18,3,'',NULL,0,0,0,'',NULL,0,NULL,NULL,NULL,NULL,1);
