package com.project.payment.db;

public class PaymentDTO {
	private int p_no = 0;
	private int u_no = 0;
	private int item_no = 0;
	private int room_no = 0;
	private String p_name=null;
	private String p_phone = null;
	private String p_item_name = null;
	private String p_room_name = null;
	private String p_checkin = null;
	private String p_checkout = null;
	private int p_night = 0;
	private String p_fee = null;
	private String p_totalFee = null;
	private int p_point = 0;
	private String p_status = null;
	private String p_wtime = null;
	private String p_item_imgpath = null;
	


	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_item_imgpath() {
		return p_item_imgpath;
	}
	public void setP_item_imgpath(String p_item_imgpath) {
		this.p_item_imgpath = p_item_imgpath;
	}
	public String getP_item_name() {
		return p_item_name;
	}
	public void setP_item_name(String p_item_name) {
		this.p_item_name = p_item_name;
	}
	public String getP_room_name() {
		return p_room_name;
	}
	public void setP_room_name(String p_room_name) {
		this.p_room_name = p_room_name;
	}
	public int getP_night() {
		return p_night;
	}
	public void setP_night(int p_night) {
		this.p_night = p_night;
	}
	
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String getP_phone() {
		return p_phone;
	}
	public void setP_phone(String p_phone) {
		this.p_phone = p_phone;
	}
	public String getP_wtime() {
		return p_wtime;
	}
	public void setP_wtime(String p_wtime) {
		this.p_wtime = p_wtime;
	}
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public int getU_no() {
		return u_no;
	}
	public void setU_no(int u_no) {
		this.u_no = u_no;
	}
	public int getItem_no() {
		return item_no;
	}
	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public String getP_checkin() {
		return p_checkin;
	}
	public void setP_checkin(String p_checkin) {
		this.p_checkin = p_checkin;
	}
	public String getP_checkout() {
		return p_checkout;
	}
	public void setP_checkout(String p_checkout) {
		this.p_checkout = p_checkout;
	}
	public String getP_fee() {
		return p_fee;
	}
	public void setP_fee(String p_fee) {
		this.p_fee = p_fee;
	}
	public String getP_totalFee() {
		return p_totalFee;
	}
	public void setP_totalFee(String p_totalFee) {
		this.p_totalFee = p_totalFee;
	}
	public int getP_point() {
		return p_point;
	}
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	


	
	
	
	
}
