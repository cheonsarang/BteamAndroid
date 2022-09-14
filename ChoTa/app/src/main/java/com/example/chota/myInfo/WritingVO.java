package com.example.chota.myInfo;

public class WritingVO {
    private String board_name, writer, writedate, board_content;
    private int read_heart, comment_cnt;


    public WritingVO(String board_name, String writer, String writedate, String board_content, int read_heart, int comment_cnt) {
        this.board_name = board_name;
        this.writer = writer;
        this.writedate = writedate;
        this.board_content = board_content;
        this.read_heart = read_heart;
        this.comment_cnt = comment_cnt;
    }


    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
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

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public int getRead_heart() {
        return read_heart;
    }

    public void setRead_heart(int read_heart) {
        this.read_heart = read_heart;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }
}
