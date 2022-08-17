package com.project.member.db;

public class MemberDTO {

	private int u_no = 0;
	private String u_name=null;
	private String u_id=null;
	private String u_nick=null;
	private String u_pw=null;
	private String u_email=null;
	private String u_phone=null;
	private String u_gender=null;
	private int u_point=0;
	private String u_status = null; //0 현재, 1 1년휴면,2 삭제예정, 3 삭제
	private int u_div = 0; //0 관리자,1 개인,2 사업자
	//private String rtime = null;

	
	public int getU_no() {
		return u_no;
	}
	public void setU_no(int u_no) {
		this.u_no = u_no;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_gender() {
		return u_gender;
	}
	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}
	public int getU_point() {
		return u_point;
	}
	public void setU_point(int u_point) {
		this.u_point = u_point;
	}
	public String getU_status() {
		return u_status;
	}
	public void setU_status(String u_status) {
		this.u_status = u_status;
	}
	public int getU_div() {
		return u_div;
	}
	public void setU_div(int u_div) {
		this.u_div = u_div;
	}
	
	
}
