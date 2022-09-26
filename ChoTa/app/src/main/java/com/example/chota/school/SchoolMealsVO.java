package com.example.chota.school;

public class SchoolMealsVO {
	
	String office_code, school_id, school_name;
	String dish_date, dish_name, or_info, cal_info, ntr_info;
	
	
	/*
    office_code	ATPT_OFCDC_SC_CODE	     STRING(필수)	    시도교육청코드
    school_id	SD_SCHUL_CODE	     STRING(필수)	    표준학교코드
    school_name	SCHUL_NM	    학교명
    dish_date	MLSV_YMD	     STRING(선택)	    급식일자
    dish_name	DDISH_NM	    요리명
    or_info		ORPLC_INFO	    원산지정보
    cal_info	CAL_INFO	    칼로리정보
    ntr_info	NTR_INFO	    영양정보
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
	public String getDish_date() {
		return dish_date;
	}
	public void setDish_date(String dish_date) {
		this.dish_date = dish_date;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public String getOr_info() {
		return or_info;
	}
	public void setOr_info(String or_info) {
		this.or_info = or_info;
	}
	public String getCal_info() {
		return cal_info;
	}
	public void setCal_info(String cal_info) {
		this.cal_info = cal_info;
	}
	public String getNtr_info() {
		return ntr_info;
	}
	public void setNtr_info(String ntr_info) {
		this.ntr_info = ntr_info;
	}
	
}
