package com.sylg.bs.user.bean;

import java.io.Serializable;
/**
 * 
 * ClassName: User 
 * @Description: 用户实体类
 * @author yx
 * @date 2018年3月21日
 */
public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userinfo_id;//客户id uuid
	private String userinfo_name;//客户姓名
	private Integer userinfo_age;//客户年龄
	private String userinfo_sex;//客户性别 1 男 2 女 3 未设置
	private String userinfo_address;//所在地址
	private String userinfo_cname;//公司名称
	private String userinfo_ctype;//公司类型
	private Integer userinfo_statement;//潜在用户状态
	private String userinfo_attribute;//客户属性 签约 未签约 
	private String userinfo_job;//客户职业
	private Integer userinfo_salary;//客户年薪范围
	private Integer userinfo_from;//潜在客户来源
	private String userinfo_phone;//客户联系方式
	private Integer userinfo_level;//客户等级
	private String userinfo_data;//下次联系日期
	private String userinfo_desc;//客户描述
	private Integer userinfo_isdelete;//是否删除 0 删除 1 潜在用户 2 正式用户
	
	
	public UserInfo() {
		super();
	}
	public final String getUserinfo_id() {
		return userinfo_id;
	}
	public final void setUserinfo_id(String userinfo_id) {
		this.userinfo_id = userinfo_id;
	}
	public final String getUserinfo_name() {
		return userinfo_name;
	}
	public final void setUserinfo_name(String userinfo_name) {
		this.userinfo_name = userinfo_name;
	}
	public final Integer getUserinfo_age() {
		return userinfo_age;
	}
	public final void setUserinfo_age(Integer userinfo_age) {
		this.userinfo_age = userinfo_age;
	}
	public final String getUserinfo_sex() {
		return userinfo_sex;
	}
	public final void setUserinfo_sex(String userinfo_sex) {
		this.userinfo_sex = userinfo_sex;
	}
	public final String getUserinfo_address() {
		return userinfo_address;
	}
	public final void setUserinfo_address(String userinfo_address) {
		this.userinfo_address = userinfo_address;
	}
	public final String getUserinfo_cname() {
		return userinfo_cname;
	}
	public final void setUserinfo_cname(String userinfo_cname) {
		this.userinfo_cname = userinfo_cname;
	}
	public final String getUserinfo_ctype() {
		return userinfo_ctype;
	}
	public final void setUserinfo_ctype(String userinfo_ctype) {
		this.userinfo_ctype = userinfo_ctype;
	}
	public final Integer getUserinfo_statement() {
		return userinfo_statement;
	}
	public final void setUserinfo_statement(Integer userinfo_statement) {
		this.userinfo_statement = userinfo_statement;
	}
	public final String getUserinfo_attribute() {
		return userinfo_attribute;
	}
	public final void setUserinfo_attribute(String userinfo_attribute) {
		this.userinfo_attribute = userinfo_attribute;
	}
	public final String getUserinfo_job() {
		return userinfo_job;
	}
	public final void setUserinfo_job(String userinfo_job) {
		this.userinfo_job = userinfo_job;
	}
	public final Integer getUserinfo_salary() {
		return userinfo_salary;
	}
	public final void setUserinfo_salary(Integer userinfo_salary) {
		this.userinfo_salary = userinfo_salary;
	}
	public final Integer getUserinfo_from() {
		return userinfo_from;
	}
	public final void setUserinfo_from(Integer userinfo_from) {
		this.userinfo_from = userinfo_from;
	}
	public final String getUserinfo_phone() {
		return userinfo_phone;
	}
	public final void setUserinfo_phone(String userinfo_phone) {
		this.userinfo_phone = userinfo_phone;
	}
	public final Integer getUserinfo_level() {
		return userinfo_level;
	}
	public final void setUserinfo_level(Integer userinfo_level) {
		this.userinfo_level = userinfo_level;
	}
	public final String getUserinfo_data() {
		return userinfo_data;
	}
	public final void setUserinfo_data(String userinfo_data) {
		this.userinfo_data = userinfo_data;
	}
	public final String getUserinfo_desc() {
		return userinfo_desc;
	}
	public final void setUserinfo_desc(String userinfo_desc) {
		this.userinfo_desc = userinfo_desc;
	}
	public final Integer getUserinfo_isdelete() {
		return userinfo_isdelete;
	}
	public final void setUserinfo_isdelete(Integer userinfo_isdelete) {
		this.userinfo_isdelete = userinfo_isdelete;
	}
	@Override
	public String toString() {
		return "User [userinfo_id=" + userinfo_id + ", userinfo_name="
				+ userinfo_name + ", userinfo_age=" + userinfo_age
				+ ", userinfo_sex=" + userinfo_sex + ", userinfo_address="
				+ userinfo_address + ", userinfo_cname=" + userinfo_cname
				+ ", userinfo_ctype=" + userinfo_ctype
				+ ", userinfo_statement=" + userinfo_statement
				+ ", userinfo_attribute=" + userinfo_attribute
				+ ", userinfo_job=" + userinfo_job + ", userinfo_salary="
				+ userinfo_salary + ", userinfo_from=" + userinfo_from
				+ ", userinfo_phone=" + userinfo_phone + ", userinfo_level="
				+ userinfo_level + ", userinfo_data=" + userinfo_data
				+ ", userinfo_desc=" + userinfo_desc + ", userinfo_isdelete="
				+ userinfo_isdelete + "]";
	}
	

}
