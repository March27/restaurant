package com.qzz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qzz.bean.Order;
import com.qzz.bean.User;
import com.qzz.util.JDBCUtil;

public class UserDao implements UserDaoImpl {

	@Override
	public User findByLoginNameAndPass(String name, String password) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
			
		//2.sql���
			String sql = "select * from user where username=? and password = ? ";
			System.out.println(sql);
		
		//3.��װ��   Statement.RETURN_GENERATED_KEYS��ȡ����id
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
		//4.ִ�����,����Ӱ�캯��
			 resultSet = preparedStatement.executeQuery();
//			 List<User> users = new ArrayList<>();
			 //ָ������һλ����Ϊ����ͷ��ϢҲ�������
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
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

}
