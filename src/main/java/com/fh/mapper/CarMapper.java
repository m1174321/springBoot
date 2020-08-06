package com.fh.mapper;

import com.fh.entity.AddRess;
import com.fh.entity.Order;
import com.fh.entity.OrderDetail;
import com.fh.entity.ProductCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMapper {

    ProductCar findCarIdByProductId(Integer id);

    Integer findProInventory(Integer id);

    Order findOrderById(Integer orderId);

    void updateById(Order order);

    void batchOrderDetail(@Param("list") List<OrderDetail> orderDetailList,@Param("id") Integer id);

    void addOrder(Order order);

    List<AddRess> findAddRess(Integer id);
}
