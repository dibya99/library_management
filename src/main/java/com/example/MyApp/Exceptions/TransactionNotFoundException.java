package com.example.MyApp.Exceptions;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(int id) {
        super("Transaction id not found : " + id);
    }
}
