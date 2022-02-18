package com.web.home.order.model;

import java.util.Date;

public class CartVO {	
	private int pro_num;
	private String pro_title;	
	private int pro_price;
	private int cart_cnt;
	private String pro_image;
	
	
	
	public CartVO() {
		
	}

	public CartVO(int pro_num, String pro_title, int pro_price, int cart_cnt, String pro_image) {
		super();
		this.pro_num = pro_num;
		this.pro_title = pro_title;
		this.pro_price = pro_price;
		this.cart_cnt = cart_cnt;
		this.pro_image = pro_image;
	}

	public int getPro_num() {
		return pro_num;
	}
	
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	
	public String getPro_title() {
		return pro_title;
	}
	
	public void setPro_title(String pro_title) {
		this.pro_title = pro_title;
	}
	
	public int getPro_price() {
		return pro_price;
	}
	
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	
	public int getCart_cnt() {
		return cart_cnt;
	}
	
	public void setCart_cnt(int cart_cnt) {
		this.cart_cnt = cart_cnt;
	}
	
	public String getPro_image() {
		return pro_image;
	}
	
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}

	@Override
	public String toString() {
		return "CartVO [pro_num=" + pro_num + ", pro_title=" + pro_title + ", pro_price=" + pro_price + ", cart_cnt="
				+ cart_cnt + ", pro_image=" + pro_image + "]";
	}
		
}
	
	