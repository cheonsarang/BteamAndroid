package com.example.chota.school;

public class SchoolpostVO {
	private String category, title, context, writer, writedate, ymd;
	private int schoolpost_id, grade_class_code, school_id, schoolpost_img_id;

	public SchoolpostVO(String category, String title, String context, String writer, String writedate, String ymd, int schoolpost_id, int grade_class_code, int school_id, int schoolpost_img_id) {
		this.category = category;
		this.title = title;
		this.context = context;
		this.writer = writer;
		this.writedate = writedate;
		this.ymd = ymd;
		this.schoolpost_id = schoolpost_id;
		this.grade_class_code = grade_class_code;
		this.school_id = school_id;
		this.schoolpost_img_id = schoolpost_img_id;
	}

	//이미지 아직 안넣었음
	public SchoolpostVO(String category, String title, String context, String writer, String writedate, String ymd, int schoolpost_id, int grade_class_code, int school_id) {
		this.category = category;
		this.title = title;
		this.context = context;
		this.writer = writer;
		this.writedate = writedate;
		this.ymd = ymd;
		this.schoolpost_id = schoolpost_id;
		this.grade_class_code = grade_class_code;
		this.school_id = school_id;
		this.schoolpost_img_id = 0;
	}

	public String getYmd() { return ymd; }
	public void setYmd(String ymd) { this.ymd = ymd; }
	public int getSchoolpost_img_id() {
		return schoolpost_img_id;
	}
	public void setSchoolpost_img_id(int schoolpost_img_id) { this.schoolpost_img_id = schoolpost_img_id; }
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getSchoolpost_id() {
		return schoolpost_id;
	}
	public void setSchoolpost_id(int schoolpost_id) {
		this.schoolpost_id = schoolpost_id;
	}
	public int getGrade_class_code() {
		return grade_class_code;
	}
	public void setGrade_class_code(int grade_class_code) {
		this.grade_class_code = grade_class_code;
	}
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	
	
}
