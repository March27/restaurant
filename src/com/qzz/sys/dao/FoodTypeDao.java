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

public class FoodTypeDao implements FoodTypeDaoImpl {

	@Override
	public List<FoodType> find(String keyword, String disabled) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
//		String sql = "select * from foodtype where foodtype_name like '%"+keyword+"%' and disabled=?";
		StringBuffer sql  = new StringBuffer("select * from foodtype where 1=1");
		if(keyword!=null && !keyword.equals("")) {
			sql.append(" and foodtype_name like '%" +keyword+ "%'");
			
		}
		if(disabled!=null && !disabled.equals("")) {
			sql.append(" and disabled="+ disabled);
		}
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql.toString());

		resultSet = preparedStatement.executeQuery();
		
		List<FoodType> foodtypes = new ArrayList<>();
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("foodtype_name"));
			foodtype.setCreateDate(resultSet.getTimestamp("create_time"));
			foodtype.setUpdateDate(resultSet.getTimestamp("update_time"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			foodtypes.add(foodtype);
		}
		
		return foodtypes;
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}

		return null;
	}

	@Override
	public FoodType findByFoodName(String foodTypeName) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from foodtype where foodtype_name=?";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, foodTypeName);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("foodtype_name"));
			foodtype.setCreateDate(resultSet.getTimestamp("create_time"));
			foodtype.setUpdateDate(resultSet.getTimestamp("update_time"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			
			return foodtype;
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	
	@Override
	public void save(FoodType foodType2) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "insert into foodtype(foodtype_name,create_time) VALUES(?,NOW())";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, foodType2.getTypeName());
		
		int resultSetInt = preparedStatement.executeUpdate();
		
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		
	}

	@Override
	public FoodType findById(int id) {
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
	try {
		connection = JDBCUtil.getConnection();
		
		String sql = "select * from foodtype where id=?";
		
		System.out.println(sql);
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			FoodType foodtype = new FoodType();
			foodtype.setId(resultSet.getInt("id"));
			foodtype.setTypeName(resultSet.getString("foodtype_name"));
			foodtype.setCreateDate(resultSet.getTimestamp("create_time"));
			foodtype.setUpdateDate(resultSet.getTimestamp("update_time"));
			foodtype.setDisabled(resultSet.getInt("disabled"));
			
			
			return foodtype;
		}
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}finally {
		JDBCUtil.close(resultSet, preparedStatement, connection);
	}
		return null;
	}

	@Override
	public Object update(FoodType foodType2) {
			Connection connection =null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet =null;
		try {
			connection = JDBCUtil.getConnection();
			
			String sql = "update foodtype set foodtype_name = ? , update_time=NOW() , disabled=? where id=?";
			
			System.out.println(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, foodType2.getTypeName());
			preparedStatement.setInt(2, foodType2.getDisabled());
			preparedStatement.setInt(3, foodType2.getId());
			
			int resultSetInt = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
