package com.example.chota.board;

public class Board2DTO {
    String tv_title1, tv_title2, tv_id, tv_speak, tv_scrap;
    int image;

    public Board2DTO(String tv_title1, String tv_title2, String tv_id, String tv_speak, String tv_scrap, int image) {
        this.tv_title1 = tv_title1;
        this.tv_title2 = tv_title2;
        this.tv_id = tv_id;
        this.tv_speak = tv_speak;
        this.tv_scrap = tv_scrap;
        this.image = image;
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

    public void setTv_title2(String tv_title) {
        this.tv_title2 = tv_title2;
    }

    public String getTv_id() {
        return tv_id;
    }

    public void setTv_id(String tv_id) {
        this.tv_id = tv_id;
    }

    public String getTv_speak() {
        return tv_speak;
    }

    public void setTv_speak(String tv_speak) {
        this.tv_speak = tv_speak;
    }

    public String getTv_scrap() {
        return tv_scrap;
    }

    public void setTv_scrap(String tv_scrap) {
        this.tv_scrap = tv_scrap;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
