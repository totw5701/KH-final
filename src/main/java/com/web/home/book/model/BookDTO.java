package com.web.home.book.model;

import java.sql.Date;

public class BookDTO {
	private int pro_num;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPro_date() {
		return pro_date;
	}
	public void setPro_date(String pro_date) {
		this.pro_date = pro_date;
	}
	public String getPro_publisher() {
		return pro_publisher;
	}
	public void setPro_publisher(String pro_publisher) {
		this.pro_publisher = pro_publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPro_contry() {
		return pro_contry;
	}
	public void setPro_contry(String pro_contry) {
		this.pro_contry = pro_contry;
	}
	public String getPro_contents() {
		return pro_contents;
	}
	public void setPro_contents(String pro_contents) {
		this.pro_contents = pro_contents;
	}
	public String getPro_image() {
		return pro_image;
	}
	public void setPro_image(String pro_image) {
		this.pro_image = pro_image;
	}
	public String getPro_sales() {
		return pro_sales;
	}
	public void setPro_sales(String pro_sales) {
		this.pro_sales = pro_sales;
	}
	
	public BookDTO(int pro_num, String pro_title, String pro_writer, String pro_publisher, String pro_date,
			int pro_price, String pro_category, String pro_contents, String pro_image) {
		super();
		this.pro_num = pro_num;
		this.pro_title = pro_title;
		this.writer = pro_writer;
		this.pro_publisher = pro_publisher;
		this.pro_date = pro_date;
		this.price = pro_price;
		this.pro_category = pro_category;
		this.pro_contents = pro_contents;
		this.pro_image = pro_image;
	}
	public BookDTO() {
		
	}
	private String pro_title;
	private String writer;
	private String  pro_date;
	private String pro_publisher;
	private int price;
	private String pro_contry;
	private String pro_contents;
	private String pro_image;
	private String pro_sales;
	private String pro_category;
}
