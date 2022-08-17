package com.project.payment.db;

public class PaymentDTO {
	private int p_no = 0;
	private int u_no = 0;
	private int item_no = 0;
	private int room_no = 0;
	private String p_name = null;
	private String p_phone = null;
	private String p_checkin = null;
	private String p_checkout = null;
	private int p_pay = 0;
	private int p_point = 0;
	private String p_wtime = null;
	

	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
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
	public int getP_pay() {
		return p_pay;
	}
	public void setP_pay(int p_pay) {
		this.p_pay = p_pay;
	}
	public int getP_point() {
		return p_point;
	}
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	
	
	
	
	
	
}
