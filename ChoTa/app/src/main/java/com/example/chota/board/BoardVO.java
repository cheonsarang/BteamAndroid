package com.example.chota.board;

import java.io.Serializable;

public class BoardVO implements Serializable {// 수정해야함
    String board_title, board_content, member_id, board_date, board_time, read_count, read_heart, comment, scrap;
    int file_id, board_id, grade_class_code, school_id;


    //게시판 홈(M_Board1Fragment)
    public BoardVO(String board_title, String board_content, String read_count, String comment, String read_heart) {
        this.board_title = board_title;
        this.board_content = board_content;
        this.read_count = read_count;
        this.comment = comment;
        this.read_heart = read_heart;
    }

    //목록화면 Board2Fragment
    public BoardVO(String board_title, String board_content, String member_id, String board_time, String read_count, String read_heart, String comment, String scrap, int file_id) {
        this.board_title = board_title;
        this.board_content = board_content;
        this.member_id = member_id;
        this.board_time = board_time;
        this.read_count = read_count;
        this.read_heart = read_heart;
        this.comment = comment;
        this.scrap = scrap;
        this.file_id = file_id;
    }

    //글 상세보기 Board3Activity(파일은 있을수 있고 없을수 있다)
    public BoardVO(String board_title, String board_content, String member_id, String board_date, String board_time, String read_count, int file_id) {
        this.board_title = board_title;
        this.board_content = board_content;
        this.member_id = member_id;
        this.board_date = board_date;
        this.board_time = board_time;
        this.read_count = read_count;
        this.file_id = file_id;
    }

    //글 쓰기 WriteActivity(글쓰기)
    public BoardVO(){};

    //학교용으로 들어가게끔
    public int getSchool_id() {
        return school_id;
    }

    //반용으로 들어
    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getGrade_class_code() {
        return grade_class_code;
    }

    public void setGrade_class_code(int grade_class_code) {
        this.grade_class_code = grade_class_code;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getBoard_date() {
        return board_date;
    }

    public void setBoard_date(String board_date) {
        this.board_date = board_date;
    }

    public String getBoard_time() {
        return board_time;
    }

    public void setBoard_time(String board_time) {
        this.board_time = board_time;
    }

    public String getRead_count() {
        return read_count;
    }

    public void setRead_count(String read_count) {
        this.read_count = read_count;
    }

    public String getRead_heart() {
        return read_heart;
    }

    public void setRead_heart(String read_heart) {
        this.read_heart = read_heart;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScrap() {
        return scrap;
    }

    public void setScrap(String scrap) {
        this.scrap = scrap;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }



}
