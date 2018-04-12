package com.sylg.bs.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sylg.bs.user.bean.UserInfo;
import com.sylg.bs.user.bean.UserInfo2;


@Mapper
/**
 * 
 * ClassName: UserMapper 
 * @Description: 用户mapper映射接口 基于动态代理 不需要实现类
 * @author yx
 * @date 2018年3月21日
 */
public interface UserMapper {
    @Select("SELECT USERINFO_NAME FROM USERINFO WHERE userinfo_id = #{userInfoId}")
    public UserInfo getByName(@Param("userInfoId") String userInfoId);
    @Insert("INSERT INTO USERINFO(userinfo_name,userinfo_age,userinfo_sex,userinfo_address,"
    		+ "userinfo_cname,userinfo_ctype,userinfo_statement,userinfo_attribute,userinfo_job,"
    		+ "userinfo_salary,userinfo_from,userinfo_phone,userinfo_level,userinfo_date,userinfo_desc,userinfo_isdelete) VALUES "
    		+ "(#{userInfoName}, #{userInfoAge},#{userInfoSex}),#{userInfoAddress},#{userInfoCname},"
    		+ "#{userInfoCtype},#{userInfoStatement},#{userInfoAttribute},#{userInfoJob}")
    public int addUserInfo(@Param("userInfoName") String userInfoName, @Param("userInfoAge") Integer userInfoAge,@Param("userInfoSex")Integer userInfoSex);
    
    @Select("SELECT testDate FROM dd ")
    public List<UserInfo2> getByName2();
    @Select("Insert into dd(testDate) values(#{time})")
    public Integer getByName3(@Param("time") String time);
}
