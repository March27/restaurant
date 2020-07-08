package com.qzz.sys.dao;

import java.util.List;

import com.qzz.sys.bean.Order;

public interface OrderDaoImpl {

	List<Order> find();

	Order findById(int id);

	void update(Order order);

	List<Order> findMonth();

	List<Order> findWeek();

	List<Order> findDay();

}
