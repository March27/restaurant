package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.Order;

public interface OrderServiceImpl {

	List<Order> find();

	Order findById(int parseInt);

	void update(Order order);

	List<Order> findMonth();

	List<Order> findWeek();

	List<Order> findDay();

}
