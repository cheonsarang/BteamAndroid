package com.example.chota.board;

public class CommentDTO {
    String comment_id, comment_time, comment_content, comment_heart;//heart는 널 일수 있음

    //댓글
    public CommentDTO(String comment_id, String comment_time, String comment_content, String comment_heart) {
        this.comment_id = comment_id;
        this.comment_time = comment_time;
        this.comment_content = comment_content;
        this.comment_heart = comment_heart;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_heart() {
        return comment_heart;
    }

    public void setComment_heart(String comment_heart) {
        this.comment_heart = comment_heart;
    }
}
