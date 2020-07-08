package com.qzz.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.qzz.bean.FoodType;
import com.qzz.util.JDBCUtil;

public class FoodTypeDao implements FoodTypeDaoImpl {

	@Override
	public List<FoodType> findAll() {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "select * from foodtype where disabled = 0";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<FoodType> foodTypes = new ArrayList<>();//将查询出来的信息进行封装
			while(resultSet.next()) {//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
				FoodType foodType = new FoodType();
				//根据列名获取列值，是什么类型的就get什么类型
				foodType.setId(resultSet.getInt("id"));
				foodType.setTypeName(resultSet.getString("foodtype_name"));
				foodType.setCreateDate(resultSet.getTimestamp("create_time"));
				foodType.setUpdateDate(resultSet.getTimestamp("update_time"));
				foodType.setDisabled(resultSet.getInt("disabled"));
				
				foodTypes.add(foodType);//添加到集合里去
//				System.out.println(foodTypes);
			}
			return foodTypes;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
		
		
	}

}
