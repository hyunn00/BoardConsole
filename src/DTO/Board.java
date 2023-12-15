package DTO;

import java.util.Date;

public class Board {
    private  int board_no;
    private String title;
    private String writer;
    private String content;
    private Date reg_date;
    private Date upd_date;

    public Board() {}
    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public void setUpd_date(Date upd_date) {
        this.upd_date = upd_date;
    }

    public int getBoard_no() {
        return board_no;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public Date getUpd_date() {
        return upd_date;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board_no=" + board_no +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", reg_date=" + reg_date +
                ", upd_date=" + upd_date +
                '}';
    }
}
