package com.example.demo.service;

import com.example.demo.entity.Book;

public interface BookService {

    public Iterable<Book> findAll();
    public Book find(int id);
}
