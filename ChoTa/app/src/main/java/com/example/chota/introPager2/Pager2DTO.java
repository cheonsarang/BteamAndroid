package com.example.chota.introPager2;

public class Pager2DTO {
    private int imgv_pager;
    private String tv_title1, tv_title2;


    public Pager2DTO(int imgv_pager, String tv_title1, String tv_title2) {
        this.imgv_pager = imgv_pager;
        this.tv_title1 = tv_title1;
        this.tv_title2 = tv_title2;
    }

    public int getImgv_pager() {
        return imgv_pager;
    }

    public void setImgv_pager(int imgv_pager) {
        this.imgv_pager = imgv_pager;
    }

    public String getTv_title1() {
        return tv_title1;
    }

    public void setTv_title1(String tv_title1) {
        this.tv_title1 = tv_title1;
    }

    public String getTv_title2() {
        return tv_title2;
    }

    public void setTv_title2(String tv_title2) {
        this.tv_title2 = tv_title2;
    }
}
