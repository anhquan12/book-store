package com.example.demo.entites;
// Generated Oct 29, 2018 8:46:38 PM by Hibernate Tools 5.2.11.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "book", catalog = "book_ecommerce")
public class Book implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int bookId;
    private String title;
    private String imgUrl;
    private String author;
    private String publisher;
    private String category;
    private BigDecimal price;
    private int quantity;
    private long createdTime;
    private int status; // 1. Đang bán.  -1. Đã thanh toán.  0. Đã xoá.
    private String description;

    public Book() {
        this.status = 1;
    }

    public Book(String title, String imgUrl, String author, String publisher, String category,  BigDecimal price, int quantity, String description) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.createdTime = System.currentTimeMillis();
        this.status = 1;
        this.description = description;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCreatedTime() {
        return createdTime;
    }

//    public void setCreatedTime(long createdTime) {
//        this.createdTime = createdTime;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
