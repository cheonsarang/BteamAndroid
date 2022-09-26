package com.example.chota.education;

import java.io.Serializable;

public class AcademyVO implements Serializable {
	private String office_code;			//atpt_ofcdc_sc_code
	private String office_name;			//atpt_ofcdc_sc_nm
	private String academy_name;		//aca_nm
	private String status;				//reg_sttus_nm
	private String field;				//가르치는거 psnby_thcc_cntnt 
	private String addr;				// 주소fa_rdnma
	private String detail_addr;			//상세주소fa_rdnda
	private int heart_aca;				//찜
	private String lat, lng;
	private String userid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public int getHeart_aca() {
		return heart_aca;
	}
	public void setHeart_aca(int heart_aca) {
		this.heart_aca = heart_aca;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public String getAcademy_name() {
		return academy_name;
	}
	public void setAcademy_name(String academy_name) {
		this.academy_name = academy_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDetail_addr() {
		return detail_addr;
	}
	public void setDetail_addr(String detail_addr) {
		this.detail_addr = detail_addr;
	}
	
	
	
}

