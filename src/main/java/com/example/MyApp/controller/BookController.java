package com.example.MyApp.controller;
import com.example.MyApp.DTO.BookDTO;
import com.example.MyApp.Exceptions.BookNotFoundException;
import com.example.MyApp.entity.Book;
import com.example.MyApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
    public class BookController {

        private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        @Autowired
        BookRepository repo;

        @GetMapping("/books")
        public List<BookDTO> getAllBooks() {
            List<BookDTO> booklist=new ArrayList<>();
            List <Book> list;
            list=repo.findAll();
            if(list.isEmpty())
            {
                LOGGER.severe("No books in repo");
                throw new BookNotFoundException(0);
            }
            for(Book book:list)
            {
                BookDTO b=new BookDTO(book.getBook_id(),book.getTitle(),book.getAuthor(),book.getSubject(),
                                book.getIsbn(),book.isStatus(),book.isAvail());
                booklist.add(b);

            }
            return booklist;
        }

        @GetMapping("/books/{book_id}")
        public BookDTO get_book_by_id(@PathVariable int book_id)
        {
            List<Book>booklist=repo.findAll();
            for(Book book:booklist)
            {
                if(book.getBook_id()==book_id)
                {
                    BookDTO b=new BookDTO(book.getBook_id(),book.getTitle(),book.getAuthor(),book.getSubject(),
                            book.getIsbn(),book.isStatus(),book.isAvail());
                    return b;
                }
            }
            LOGGER.severe("Book not found");
            throw new BookNotFoundException(book_id);

        }

        @DeleteMapping("/books")
        public String delete_all_books()
        {
            List<Book>booklist=repo.findAll();
            if(booklist.size()==0)
            {
                LOGGER.severe("Not a single book in library registered");
                throw new BookNotFoundException(0);
            }
            for(Book book:booklist)
            {
                book.setAvail(false);
                repo.save(book);
            }
            return "ALL Books status changed to deleted";
        }

        @DeleteMapping("/books/{book_id}")
        public String delete_book_by_id(@PathVariable int book_id)
        {
            List <Book> booklist=repo.findAll();
            for(Book book:booklist)
            {
                if(book.getBook_id()==book_id) {
                    book.setAvail(false);
                    repo.save(book);
                    return "Status changed to deleted";

                }
            }
            LOGGER.severe("Book requested not found");
            throw new BookNotFoundException(book_id);
        }



        @PostMapping("/books")
        public boolean writeBook(@RequestBody Book book) {
            repo.save(book);
            return true;
        }

        @PostMapping("/books/{book_id}")
        public boolean write_a_book(@PathVariable int book_id)
        {
            List<Book> booklist=repo.findAll();
            if(booklist.isEmpty())
            {
                LOGGER.severe("Not a single book in library registered");
                throw new BookNotFoundException(0);
            }
            Optional<Book> b=repo.findById(book_id);
            if(b.isPresent())
            {
                Book book=b.get();
                book.setAvail(true);
                repo.save(book);
                return true;
            }
            throw new BookNotFoundException(book_id);
        }



    }

