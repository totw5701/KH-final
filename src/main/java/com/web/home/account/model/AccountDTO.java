package com.web.home.account.model;

import java.sql.Date;

public class AccountDTO {
	private int user_num;
	private String user_id;
	private String user_name;
	private String user_pwd;
	private String user_email;
	private String user_phone;
	private int user_point;
	private Date user_date;
	private String user_authkey;
	private String user_address;
	
	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public AccountDTO() {
		
	}

	public AccountDTO(String user_id,String user_pwd) {
		this.user_id = user_id;
		this.user_pwd = user_pwd;
	}
	
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	public Date getUser_date() {
		return user_date;
	}
	public void setUser_date(Date user_date) {
		this.user_date = user_date;
	}

	public String getUser_authkey() {
		return user_authkey;
	}

	public void setUser_authkey(String user_authkey) {
		this.user_authkey = user_authkey;
	}
	

}
