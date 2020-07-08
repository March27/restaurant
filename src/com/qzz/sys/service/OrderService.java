package com.qzz.sys.service;

import java.util.List;

import com.qzz.sys.bean.Order;
import com.qzz.sys.dao.OrderDao;
import com.qzz.sys.dao.OrderDaoImpl;

public class OrderService implements OrderServiceImpl {

	OrderDaoImpl orderDao = new OrderDao();
	@Override
	public List<Order> find() {
		// TODO 自动生成的方法存根
		return orderDao.find();
	}
	@Override
	public Order findById(int id) {
		// TODO 自动生成的方法存根
		return orderDao.findById(id);
	}
	@Override
	public void update(Order order) {
		// TODO 自动生成的方法存根
		orderDao.update(order);
	}
	@Override
	public List<Order> findMonth() {
		// TODO 自动生成的方法存根
		return orderDao.findMonth();
	}
	@Override
	public List<Order> findWeek() {
		// TODO 自动生成的方法存根
		return orderDao.findWeek();
	}
	@Override
	public List<Order> findDay() {
		// TODO 自动生成的方法存根
		return orderDao.findDay();
	}

}
