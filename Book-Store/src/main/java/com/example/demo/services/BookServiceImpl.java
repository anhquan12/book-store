package com.example.demo.services;

import com.example.demo.entites.Book;
import com.example.demo.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Iterable<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book find(int id) {
        return bookDAO.findById(id).orElse(null);
    }
}
