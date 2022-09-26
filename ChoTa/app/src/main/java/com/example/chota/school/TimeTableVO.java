package com.example.chota.school;

public class TimeTableVO {
	
	String office_code, school_id, school_name;
	String year, sem, ymd, grade, class_nm, period, subject;
	
	
	/*
	office_code	ATPT_OFCDC_SC_CODE	     STRING(필수)	    시도교육청코드
    school_id	SD_SCHUL_CODE	     STRING(필수)	    표준학교코드
    school_name	SCHUL_NM	    학교명
    year		AY	     STRING(선택)	    학년도
    sem			SEM	     STRING(선택)	    학기
    grade		GRADE	     STRING(선택)	    학년
    class_nm	CLASS_NM	     STRING(선택)	    반명
    period		PERIO	     STRING(선택)	    교시
    subject		ITRT_CNTNT	    수업내용    
	 */
	
	
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getSem() {
		return sem;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	public String getYmd() { return ymd; }
	public void setYmd(String ymd) { this.ymd = ymd; }
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClass_nm() {
		return class_nm;
	}
	public void setClass_nm(String class_nm) {
		this.class_nm = class_nm;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
