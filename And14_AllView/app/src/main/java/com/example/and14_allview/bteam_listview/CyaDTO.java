package com.example.and14_allview.bteam_listview;

public class CyaDTO {
    int img_id, user_id_img;
    String title, user_name, count, time;

    public CyaDTO(int img_id, int user_id_img, String title, String user_name, String count, String time) {
        this.img_id = img_id;
        this.user_id_img = user_id_img;
        this.title = title;
        this.user_name = user_name;
        this.count = count;
        this.time = time;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getUser_id_img() {
        return user_id_img;
    }

    public void setUser_id_img(int user_id_img) {
        this.user_id_img = user_id_img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
