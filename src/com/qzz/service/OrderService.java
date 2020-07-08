package com.qzz.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.qzz.bean.Order;
import com.qzz.bean.OrderDetail;
import com.qzz.dao.OrderDao;
import com.qzz.dao.OrderDaoImpl;

public class OrderService implements OrderServiceImpl {

	private OrderDaoImpl orderDao = new OrderDao();

	@Override
//	购物车(foodid,buynum)
	public void order(Map<Integer, Integer> shopCar, String total,Integer userId) {
		Order order = new Order();
		
		//订单编码
		StringBuffer orderCode = new StringBuffer();
		orderCode.append("QZZ-");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		orderCode.append(dateFormat.format(new Date()));
//		orderCode.append(UUID.randomUUID().toString());
		orderCode.append(userId);
		
		order.setOrderCode(orderCode.toString());
		order.setTotalPrice(Double.valueOf(total));
		
		orderDao.order(order,shopCar,userId);
	}

	@Override
	public List<Order> findDetails(Integer orderId) {
		
		//查询所有未删除的未付款 userId的订单
		List<Order> orders = orderDao.findDetails(orderId);
		
		//遍历订单,根据订单id查询其对应的订单明细
		if(orders != null && orders.size()>0){
			for (Order order : orders) {
				List<OrderDetail> details = orderDao.findByOrderId(order.getId());
				if(details != null && details.size()>0) {
					order.setOrderDetail(details);
				}
			}
		}
		System.out.println(orders);
		return orders;
	}


	@Override
	public Order findById(int id) {
		
		return orderDao.findById(id);
	}

	@Override
	public void pay(Order order) {
		// TODO 自动生成的方法存根
		 orderDao.pay(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
		
	}




	


}
