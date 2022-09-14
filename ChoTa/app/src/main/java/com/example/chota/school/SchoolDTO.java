package com.example.chota.school;

public class SchoolDTO {
    String school_category, school_date, school_title, school_context;
    int school_img_id;

    public SchoolDTO(String school_category, String school_date, String school_title, String school_context) {
        this.school_category = school_category;
        this.school_date = school_date;
        this.school_title = school_title;
        this.school_context = school_context;
        this.school_img_id = 0;
    }

    public SchoolDTO(String school_category, String school_date, String school_title, String school_context, int school_img_id) {
        this.school_category = school_category;
        this.school_date = school_date;
        this.school_title = school_title;
        this.school_context = school_context;
        this.school_img_id = school_img_id;
    }

    public String getSchool_category() {
        return school_category;
    }

    public void setSchool_category(String school_category) {
        this.school_category = school_category;
    }

    public String getSchool_date() {
        return school_date;
    }

    public void setSchool_date(String school_date) {
        this.school_date = school_date;
    }

    public String getSchool_title() {
        return school_title;
    }

    public void setSchool_title(String school_title) {
        this.school_title = school_title;
    }

    public String getSchool_context() {
        return school_context;
    }

    public void setSchool_context(String school_context) {
        this.school_context = school_context;
    }

    public int getSchool_img_id() {
        return school_img_id;
    }

    public void setSchool_img_id(int school_img_id) {
        this.school_img_id = school_img_id;
    }
}
