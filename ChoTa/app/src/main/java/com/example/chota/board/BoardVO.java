package com.example.chota.board;

import java.io.Serializable;
import java.util.List;

public class BoardVO implements Serializable {
    private	String title, content, writer, writedate, member_grp, board_name, file_path;
    private int id, no, readcnt, read_heart, board_group_id, comment_cnt, scrap_cnt, file_cnt;
    private List<BoardFileVO> fileInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getMember_grp() {
        return member_grp;
    }

    public void setMember_grp(String member_grp) {
        this.member_grp = member_grp;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getReadcnt() {
        return readcnt;
    }

    public void setReadcnt(int readcnt) {
        this.readcnt = readcnt;
    }

    public int getRead_heart() {
        return read_heart;
    }

    public void setRead_heart(int read_heart) {
        this.read_heart = read_heart;
    }

    public int getBoard_group_id() {
        return board_group_id;
    }
    public void setBoard_group_id(int board_group_id) {
        this.board_group_id = board_group_id;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public int getScrap_cnt() {
        return scrap_cnt;
    }

    public void setScrap_cnt(int scrap_cnt) {
        this.scrap_cnt = scrap_cnt;
    }


    public int getFile_cnt() {
        return file_cnt;
    }

    public void setFile_cnt(int file_cnt) {
        this.file_cnt = file_cnt;
    }

    public List<BoardFileVO> getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(List<BoardFileVO> fileInfo) {
        this.fileInfo = fileInfo;
    }


    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
