package com.example.demo.services;

import com.example.demo.entites.Orders;
import com.example.demo.repository.OrdersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ordersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDAO ordersDAO;

    @Override
    public Orders create(Orders orders) {
        return ordersDAO.save(orders);
    }
}
