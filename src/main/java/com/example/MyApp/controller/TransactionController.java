package com.example.MyApp.controller;

import com.example.MyApp.DTO.TransactionDTO;
import com.example.MyApp.Exceptions.BookBorrowedException;
import com.example.MyApp.Exceptions.BookNotFoundException;
import com.example.MyApp.Exceptions.TransactionNotFoundException;
import com.example.MyApp.Exceptions.UserNotFoundException;
import com.example.MyApp.entity.Book;
import com.example.MyApp.entity.Transaction;
import com.example.MyApp.entity.User;
import com.example.MyApp.repository.BookRepository;
import com.example.MyApp.repository.TransactionRepository;
import com.example.MyApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class TransactionController {
    @Autowired
    TransactionRepository trans_repo;
    @Autowired
    BookRepository b_repo;
    @Autowired
    UserRepository u_repo;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @GetMapping("/transactions/{user_id}/{book_id}")
    public String addTransaction(@PathVariable int user_id, @PathVariable int book_id, @RequestParam String request)
    {
        System.out.println(request);
        String transaction_purpose="";
        if(request.equals("borrow"))
            transaction_purpose="BORROWED";
        else if(request.equals("return"))
            transaction_purpose="RETURNED";
        Optional<User> user=u_repo.findById(user_id);
        if(user.isEmpty())
        {
            LOGGER.severe("User not found");
            throw new UserNotFoundException(user_id);
        }
        Optional<Book> book=b_repo.findById(book_id);
        if(book.isEmpty())
        {
            LOGGER.severe("Book not found");
            throw new BookNotFoundException(book_id);
        }
        User u=user.get();
        Book b=book.get();
        if(!u.isStatus())
        {
            LOGGER.severe("User not registered right now");
            throw new UserNotFoundException(user_id);
        }
        if(!b.isAvail())
        {
            LOGGER.severe("Book currently out of stock");
            throw new BookNotFoundException(book_id);
        }
        if(request.equals("borrow")) {
            if (!b.isStatus()) {
                LOGGER.severe("Book currently borrowed");
                throw new BookBorrowedException(book_id);

            }

        }
        else if(request.equals("return"))
        {
            if(b.isStatus())
            {
                LOGGER.severe("Book already returned");
                throw new BookBorrowedException(book_id);
            }
        }
        else
            return "No query parameter";
        b.setStatus(!b.isStatus());
        b_repo.save(b);
        Transaction obj=new Transaction();
        obj.setTime(java.time.LocalTime.now().toString());
        obj.setDate(java.time.LocalDate.now().toString());
        obj.setUser(u);
        obj.setBook(b);
        obj.setAction(transaction_purpose);
        trans_repo.save(obj);
        LOGGER.info("Transaction added");
        return "Transaction successfully added";
    }

    @GetMapping("/transactions")
    public List <TransactionDTO> getAllTransactions()
    {
        List<Transaction> transaction_list=trans_repo.findAll();
        List<TransactionDTO> t_list=new ArrayList<>();
        if(transaction_list.isEmpty())
        {
            LOGGER.severe("No transactions found");
            throw new TransactionNotFoundException(0);
        }
        for(Transaction transaction:transaction_list)
        {
            TransactionDTO t=new TransactionDTO(transaction.getTrans_id(),transaction.getDate(),transaction.getTime()
                    ,transaction.getAction(),transaction.getBook().getBook_id(),transaction.getUser().getUser_id());
            t_list.add(t);
        }
        LOGGER.info("Transactions added");
        return t_list;
    }
}
