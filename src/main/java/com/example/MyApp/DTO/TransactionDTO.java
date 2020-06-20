package com.example.MyApp.DTO;

public class TransactionDTO {

    private int trans_id;
    private String date;
    private String time;
    private String action;
    private int book_id;
    private int user_id;

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public TransactionDTO(int trans_id, String date, String time, String action, int book_id, int user_id) {
        this.trans_id = trans_id;
        this.date = date;
        this.time = time;
        this.action = action;
        this.book_id = book_id;
        this.user_id = user_id;
    }
}
