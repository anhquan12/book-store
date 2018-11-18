package com.example.demo.services;

import com.example.demo.entites.Ordersdetail;
import com.example.demo.repository.OrdersDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ordersDetailService")
@Transactional
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailDAO ordersDetailDAO;

    @Override
    public Ordersdetail create(Ordersdetail ordersDetail) {
        return ordersDetailDAO.save(ordersDetail);
    }
}
