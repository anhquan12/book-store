package com.example.demo.repository;

import com.example.demo.entites.Ordersdetail;
import com.example.demo.entites.OrdersdetailId;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDetailDAO extends CrudRepository<Ordersdetail, OrdersdetailId> {

}
