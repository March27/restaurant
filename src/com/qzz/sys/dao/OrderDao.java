package com.qzz.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qzz.sys.bean.Food;
import com.qzz.sys.bean.Order;
import com.qzz.sys.bean.OrderDetail;
import com.qzz.sys.bean.User;
import com.qzz.sys.util.JDBCUtil;

public class OrderDao implements OrderDaoImpl {

	@Override
	public List<Order> find() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select tb_order.*,username from tb_order left join user on tb_order.userId=user.id ";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		
		resultSet = preparedStatement.executeQuery();
		
		List<Order> orders = new ArrayList<>();
		
		while(resultSet.next()) {
			Order order = new Order();
			order.setDisabled(resultSet.getInt("disabled"));
			order.setId(resultSet.getInt("id"));
			order.setOrderCode(resultSet.getString("order_code"));
			order.setOrderTime(resultSet.getTimestamp("order_time"));
			order.setPayTime(resultSet.getString("pay_time"));
			order.setStatus(resultSet.getInt("status"));
			order.setTotalPrice(resultSet.getDouble("totalprice"));
			order.setDisabled(resultSet.getInt("disabled"));
			
			User user = new User();
			user.setName(resultSet.getString("username"));
			
			order.setUser(user);
			orders.add(order);
	
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
	
		return null;
	}

	@Override
	public Order findById(int id) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from tb_order where id=?";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		
		
		
		while(resultSet.next()) {
			Order order = new Order();
			order.setDisabled(resultSet.getInt("disabled"));
			order.setId(resultSet.getInt("id"));
			order.setOrderCode(resultSet.getString("order_code"));
			order.setOrderTime(resultSet.getTimestamp("order_time"));
			order.setPayTime(resultSet.getString("pay_time"));
			order.setStatus(resultSet.getInt("status"));
			order.setTotalPrice(resultSet.getDouble("totalprice"));
			order.setDisabled(resultSet.getInt("disabled"));

			return order;
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public void update(Order order) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "update tb_order set totalprice=?,status=?,update_time=NOW(),disabled=? where id=?";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setDouble(1, order.getTotalPrice());
		preparedStatement.setInt(2, order.getStatus());
		preparedStatement.setInt(3, order.getDisabled());
		preparedStatement.setInt(4, order.getId());
		int resultSetInt = preparedStatement.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		
	}

	@Override
	public List<Order> findMonth() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select foodname,sum(buyNum)count,DATE_FORMAT(pay_time,'%Y-%m')month  from order_detail " + 
				"right join food on food.id=order_detail.food_id " + 
				"left join tb_order on tb_order.id=order_detail.order_id where tb_order.status=1" + 
				" GROUP BY foodname,month ORDER BY month,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("month"));
	
			Food food = new Food();
			food.setFoodName(resultSet.getString("foodname"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public List<Order> findWeek() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select foodname,sum(buyNum)count,DATE_FORMAT(pay_time,'%Y-%m µÚ%uÖÜ')week  from order_detail " + 
				"right join food on food.id=order_detail.food_id " + 
				"left join tb_order on tb_order.id=order_detail.order_id where tb_order.status=1 " + 
				"GROUP BY foodname,week ORDER BY week,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("week"));
	
			Food food = new Food();
			food.setFoodName(resultSet.getString("foodname"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public List<Order> findDay() {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select foodname,sum(buyNum)count,DATE_FORMAT(pay_time,'%Y-%m-%d')day  from order_detail " + 
				"right join food on food.id=order_detail.food_id " + 
				"left join tb_order on tb_order.id=order_detail.order_id where tb_order.status=1 " + 
				"GROUP BY foodname,day ORDER BY day,count desc";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		
		
		List<Order> orders = new ArrayList<>();
		while(resultSet.next()) {
			Order order = new Order();
			order.setPayTime(resultSet.getString("day"));
	
			Food food = new Food();
			food.setFoodName(resultSet.getString("foodname"));
			
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(resultSet.getInt("count"));

			order.setFood(food);
			order.setOrderDetail(orderDetail);
			orders.add(order);
			
		}
		return orders;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

}
