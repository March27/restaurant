
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
	//���ݲ�ϵid����ѯδɾ���Ĳ�Ʒ
	public List<Food> findByFoodTypeId(Integer foodTypeId) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "select * from food where disabled = 0 and foodtype_id=? ";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			 preparedStatement.setInt(1, foodTypeId);
//			 preparedStatement.setInt(2, (pageNo-1)*pageSize);
//			 preparedStatement.setInt(3, pageSize);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<Food> foods = new ArrayList<>();//����ѯ��������Ϣ���з�װ
			while(resultSet.next()) {//ָ������һλ����Ϊ����ͷ��ϢҲ��ѯ�����ˣ�����ͷ��Ϣ����Ҫ��װ
				Food food = new Food();
				//����������ȡ��ֵ����ʲô���͵ľ�getʲô����
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));

				foods.add(food);//��ӵ�������ȥ
//				System.out.println(foodTypes);
			}
			return foods;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	//ͨ����Ʒid����ѯ
	public Food findByFoodId(Integer foodid) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "select * from food where id=?";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			 preparedStatement.setInt(1, foodid);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 
			//List<Food> foods = new ArrayList<>();//����ѯ��������Ϣ���з�װ
			while(resultSet.next()) {//ָ������һλ����Ϊ����ͷ��ϢҲ��ѯ�����ˣ�����ͷ��Ϣ����Ҫ��װ
				Food food = new Food();
				//����������ȡ��ֵ����ʲô���͵ľ�getʲô����
				food.setId(resultSet.getInt("id"));
				food.setCreateDate(resultSet.getTimestamp("create_time"));
				food.setUpdateDate(resultSet.getTimestamp("update_time"));
				food.setDisabled(resultSet.getInt("disabled"));
				food.setFoodName(resultSet.getString("foodname"));
				food.setFoodTypeId(resultSet.getInt("foodtype_id"));
				food.setImg(resultSet.getString("img"));
				food.setPrice(resultSet.getDouble("price"));
				food.setRemark(resultSet.getString("remark"));

				//foods.add(food);//��ӵ�������ȥ
//				System.out.println(foodTypes);
				return food;
			}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public int getFoodCount(int foodid) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "SELECT COUNT(*) as total FROM food where disabled = 0 and foodtype_id=?";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			preparedStatement.setInt(1, foodid);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 resultSet.next();
			 int n = resultSet.getInt("total");
			
			return n;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return 0;
	}

}
