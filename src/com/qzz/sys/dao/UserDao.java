package com.qzz.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qzz.sys.bean.User;
import com.qzz.sys.util.JDBCUtil;


public class UserDao implements UserDaoImpl {

	@Override
	public User findByLoginNameAndPass(String name, String password) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql语句
			String sql = "select * from user where username=? and password = ? ";
			System.out.println(sql);
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
		//4.执行语句,返回影响函数
			 resultSet = preparedStatement.executeQuery();
//			 List<User> users = new ArrayList<>();
			 //指针下移一位，因为将表头信息也查出来了
			 while(resultSet.next()) {
				 User user = new User();
				 
				 user.setId(resultSet.getInt("id"));
				 user.setName(resultSet.getString("username"));
				 user.setPassword(resultSet.getString("password"));
				 user.setPhone(resultSet.getString("phone"));
//				 users.add(user);
				 return user;
			 }
//			 return users;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}
	
	

}
