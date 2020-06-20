package com.example.MyApp.Exceptions;

    public class BookNotFoundException extends RuntimeException {

        public BookNotFoundException(int id) {
            super("Book id not found : " + id);
        }
    }


