package com.project.reveiw.db;

public class ReviewDTO {
	private int r_no=0;
	private int u_no = 0;
	private int item_no = 0;
	private int room_no = 0;
	private int r_grade = 0;
	private String r_content  = null;
	private String r_answer = null;
	private int r_indent = 0;
	private String r_wtime = null;
	private String r_imgpath = null;
	
	
	
	
	public String getR_imgpath() {
		return r_imgpath;
	}
	public void setR_imgpath(String r_imgpath) {
		this.r_imgpath = r_imgpath;
	}
	public int getR_no() {
		return r_no;
	}
	public void setR_no(int r_no) {
		this.r_no = r_no;
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
	public int getR_grade() {
		return r_grade;
	}
	public void setR_grade(int r_grade) {
		this.r_grade = r_grade;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getR_answer() {
		return r_answer;
	}
	public void setR_answer(String r_answer) {
		this.r_answer = r_answer;
	}
	public int getR_indent() {
		return r_indent;
	}
	public void setR_indent(int r_indent) {
		this.r_indent = r_indent;
	}
	public String getR_wtime() {
		return r_wtime;
	}
	public void setR_wtime(String r_wtime) {
		this.r_wtime = r_wtime;
	}
	
	
}
