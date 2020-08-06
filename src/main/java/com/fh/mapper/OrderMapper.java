package com.fh.mapper;

import com.fh.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    List<Order> findOrder(Integer id);
}
