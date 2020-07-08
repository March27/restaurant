package com.qzz.dao;

import java.util.List;
import java.util.Map;

import com.qzz.bean.Order;
import com.qzz.bean.OrderDetail;

public interface OrderDaoImpl {

	void order(Order order, Map<Integer, Integer> shopCar,Integer userId);

	List<Order> findDetails(Integer orderId);

	List<OrderDetail> findByOrderId(Integer OrderId);

	Order findById(int id);

	void pay(Order order);

	void deleteOrder(Order order);



}
