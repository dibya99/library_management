package com.example.MyApp.Exceptions;

import com.example.MyApp.entity.Book;
import com.example.MyApp.entity.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    // Let Spring handle the exception, we just override the status code
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> springHandleNotFound1(BookNotFoundException ex) throws IOException{
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex);
        return buildResponseEntity(apiError);

    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object>springHandleNotFound2(UserNotFoundException ex)throws IOException{
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND,ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Object>springHandleNotFound3(TransactionNotFoundException ex)throws IOException
    {
        ApiError apiError=new ApiError(HttpStatus.NOT_FOUND,ex);
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(BookBorrowedException.class)
        public ResponseEntity<Object>springHandleNotFound4(BookBorrowedException ex)throws IOException
    {
        ApiError apiError=new ApiError(HttpStatus.CONFLICT,ex);
        return buildResponseEntity(apiError);
    }

}