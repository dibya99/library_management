package com.example.MyApp.DTO;

public class BookDTO {
    private int book_id;
    private String title;
    private String author;
    private String subject;
    private String isbn;
    private boolean status;
    private boolean avail;

    public BookDTO(int book_id, String title, String author, String subject, String isbn, boolean status, boolean avail) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.subject = subject;
        this.isbn = isbn;
        this.status = status;
        this.avail = avail;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAvail() {
        return avail;
    }

    public void setAvail(boolean avail) {
        this.avail = avail;
    }
}
