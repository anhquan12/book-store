package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer>, CrudRepository<Book ,Integer> {

    Book find(int id);

    Page<Book> findBooksByPrice(int price, Pageable pageable);

    Page<Book> findBooksByStatus(int status, Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

}
