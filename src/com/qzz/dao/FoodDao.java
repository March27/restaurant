
package com.qzz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qzz.bean.Food;
import com.qzz.util.JDBCUtil;

public class FoodDao implements FoodDaoImpl {

	@Override
	//根据菜系id来查询未删除的菜品
	public List<Food> findByFoodTypeId(Integer foodTypeId) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "select * from food where disabled = 0 and foodtype_id=? ";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setInt(1, foodTypeId);
//			 preparedStatement.setInt(2, (pageNo-1)*pageSize);
//			 preparedStatement.setInt(3, pageSize);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<Food> foods = new ArrayList<>();//将查询出来的信息进行封装
			while(resultSet.next()) {//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
				Food food = new Food();
				//根据列名获取列值，是什么类型的就get什么类型
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));

				foods.add(food);//添加到集合里去
//				System.out.println(foodTypes);
			}
			return foods;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	//通过菜品id来查询
	public Food findByFoodId(Integer foodid) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "select * from food where id=?";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setInt(1, foodid);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 
			//List<Food> foods = new ArrayList<>();//将查询出来的信息进行封装
			while(resultSet.next()) {//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
				Food food = new Food();
				//根据列名获取列值，是什么类型的就get什么类型
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));

				//foods.add(food);//添加到集合里去
//				System.out.println(foodTypes);
				return food;
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public int getFoodCount(int foodid) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "SELECT COUNT(*) as total FROM food where disabled = 0 and foodtype_id=?";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			preparedStatement.setInt(1, foodid);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 resultSet.next();
			 int n = resultSet.getInt("total");
			
			return n;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return 0;
	}

}
