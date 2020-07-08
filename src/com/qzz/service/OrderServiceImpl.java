package com.qzz.service;

import java.util.List;
import java.util.Map;

import com.qzz.bean.Order;

public interface OrderServiceImpl {

	void order(Map<Integer, Integer> shopCar, String total,Integer userId);

	List<Order> findDetails(Integer userId);

//Í¨¹ý¶©µ¥id
	Order findById(int parseInt);

	void pay(Order order);

	void deleteOrder(Order order);





	
	

}
