package com.example.demo.repository;

import com.example.demo.entites.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersDAO extends CrudRepository<Orders , Integer> {
}
