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
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "select * from foodtype where disabled = 0";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<FoodType> foodTypes = new ArrayList<>();//����ѯ��������Ϣ���з�װ
			while(resultSet.next()) {//ָ������һλ����Ϊ����ͷ��ϢҲ��ѯ�����ˣ�����ͷ��Ϣ����Ҫ��װ
				FoodType foodType = new FoodType();
				//����������ȡ��ֵ����ʲô���͵ľ�getʲô����
				foodType.setId(resultSet.getInt("id"));
				foodType.setTypeName(resultSet.getString("foodtype_name"));
				foodType.setCreateDate(resultSet.getTimestamp("create_time"));
				foodType.setUpdateDate(resultSet.getTimestamp("update_time"));
				foodType.setDisabled(resultSet.getInt("disabled"));
				
				foodTypes.add(foodType);//��ӵ�������ȥ
//				System.out.println(foodTypes);
			}
			return foodTypes;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
		
		
	}

}
