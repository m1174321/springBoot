package com.fh.service.impl;

import com.fh.entity.Order;
import com.fh.mapper.OrderMapper;
import com.fh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;



    @Override
    public List<Order> findOrder(Integer id) {
        List<Order> orderList = orderMapper.findOrder(id);
        return orderList;
    }
}
