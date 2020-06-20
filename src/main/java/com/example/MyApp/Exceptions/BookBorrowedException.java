package com.example.MyApp.Exceptions;

public class BookBorrowedException extends RuntimeException{

        public BookBorrowedException(int id) {
            super("Book is currently burrowed : " + id);
        }

}
