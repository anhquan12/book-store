package com.example.demo.services;

import com.example.demo.entites.Book;

public interface BookService {

    public Iterable<Book> findAll();
    public Book find(int id);
}
