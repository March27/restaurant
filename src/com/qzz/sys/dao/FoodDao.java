package com.qzz.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qzz.sys.bean.Food;
import com.qzz.sys.bean.FoodType;
import com.qzz.sys.util.JDBCUtil;

public class FoodDao implements FoodDaoImpl {

	@Override
	public List<Food> find(String keyword, String foodTypeId) {
			Connection connection =null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet =null;
		try {
			connection = JDBCUtil.getConnection();
			
			StringBuffer sql =new StringBuffer();
			sql.append("select food.*,foodtype.foodtype_name from food left join foodtype on food.foodtype_id=foodtype.id where 1=1");
//			sql.append("select * from food where 1=1")
			if(keyword!=null && !keyword.equals("")) {//模糊查询
				sql.append(" and foodname LIKE '%"+keyword+"%'");
			}
			if(foodTypeId!=null && !foodTypeId.equals("")) {
				sql.append(" and foodtype_id="+foodTypeId);
			}
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql.toString());
			
			resultSet = preparedStatement.executeQuery();
			
			List<Food> foods = new ArrayList<>();
			while(resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));
				
				FoodType foodType = new FoodType();
				foodType.setTypeName(resultSet.getString("foodtype_name"));
				food.setFoodType(foodType);
				
				foods.add(food);
			}
			System.out.println(foods);
			return foods;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		
		
		return null;
	}

	@Override
	public Food findById(int id) {
			Connection connection =null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet =null;
		try {
			connection = JDBCUtil.getConnection();
			
			String sql = "select * from food where id = ?";
			System.out.println(sql);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			
			
			while(resultSet.next()) {
				Food food = new Food();
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));
				return food;
		
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		
			return null;
		}

	@Override
		public void update(Food food) {
				Connection connection =null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet =null;
			try {
				connection = JDBCUtil.getConnection();
				
				String sql = "update food set foodname=?,foodtype_id=?,price=?,remark=?,img=?,update_time=NOW(),disabled=? where id=?";
				System.out.println(sql);
				
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, food.getFoodName());
				preparedStatement.setInt(2, food.getFoodTypeId());
				preparedStatement.setDouble(3, food.getPrice());
				preparedStatement.setString(4, food.getRemark());
				preparedStatement.setString(5,food.getImg());
				preparedStatement.setInt(6, food.getDisabled());
				preparedStatement.setInt(7,food.getId());
				
				int resultSetInt = preparedStatement.executeUpdate();
				
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				JDBCUtil.close(resultSet, preparedStatement, connection);
			}
		
	}

	@Override
	public Food findByFoodName(String foodName) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from food where foodname = ?";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, foodName);
		
		resultSet = preparedStatement.executeQuery();
		
		
		
		while(resultSet.next()) {
			Food food = new Food();
			food.setId(resultSet.getInt("id"));
			food.setCreateDate(resultSet.getTimestamp("create_time"));
			food.setUpdateDate(resultSet.getTimestamp("update_time"));
			food.setDisabled(resultSet.getInt("disabled"));
			food.setFoodName(resultSet.getString("foodname"));
			food.setFoodTypeId(resultSet.getInt("foodtype_id"));
			food.setImg(resultSet.getString("img"));
			food.setPrice(resultSet.getDouble("price"));
			food.setRemark(resultSet.getString("remark"));
			return food;
	
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public void save(Food food) {
		// TODO 自动生成的方法存根
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "insert into food(foodname,foodtype_id,price,remark,img,create_time) values(?,?,?,?,?,NOW())";
		System.out.println(sql);
		
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, food.getFoodName());
		preparedStatement.setInt(2, food.getFoodTypeId());
		preparedStatement.setDouble(3, food.getPrice());
		preparedStatement.setString(4, food.getRemark());
		preparedStatement.setString(5,food.getImg());
		
		
		int resultSetInt = preparedStatement.executeUpdate();
		
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
	}

}
