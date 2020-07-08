package com.qzz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.qzz.bean.Comment;
import com.qzz.bean.Food;
import com.qzz.bean.User;
import com.qzz.util.JDBCUtil;

public class CommentDao implements CommentDaoImpl {

	@Override
	public List<Comment> findByFoodId(int foodid) {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql���
			String sql = "select * from comment " + 
					"	left join user on comment.user_id=user.id" + 
					"	where food_id=? and disabled = 0";
			System.out.println(sql);
		
		//3.��װ��
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			 preparedStatement.setInt(1, foodid);
			 
			
		//4.ִ�����
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<Comment> comments = new ArrayList<>();//����ѯ��������Ϣ���з�װ
			while(resultSet.next()) {//ָ������һλ����Ϊ����ͷ��ϢҲ��ѯ�����ˣ�����ͷ��Ϣ����Ҫ��װ
				Comment comment = new Comment();
				//����������ȡ��ֵ����ʲô���͵ľ�getʲô����
				comment.setId(resultSet.getInt("id"));
				comment.setCreateTime(resultSet.getTimestamp("comment_time"));
				comment.setContent(resultSet.getString("content"));
				comment.setDisabled(resultSet.getInt("disabled"));
				comment.setFoodId(resultSet.getInt("food_id"));
				comment.setNameId(resultSet.getInt("user_id"));
				
				User user = new User();
				user.setName(resultSet.getString("username"));
				
				comment.setUser(user);
				comments.add(comment);//��ӵ�������ȥ
//				System.out.println(comments);
			}
			return comments;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public void comment(int userId, int foodId, String content) {
		// TODO �Զ����ɵķ������
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.�������ݿ�
		try {
			connection = JDBCUtil.getConnection();
			
			
		//2.sql���
			String sql = "insert into comment (user_id,food_id,content,comment_time) values (?,?,?,NOW())";
			System.out.println(sql);
		
		//3.��װ��   Statement.RETURN_GENERATED_KEYS��ȡ����id
			 preparedStatement = connection.prepareStatement(sql);
			 //������1��ʼ,��ʲô���͵ľ�setʲô����
			 preparedStatement.setInt(1, userId);
			 preparedStatement.setInt(2, foodId);
			 preparedStatement.setString(3, content);
			 
			 
			
		//4.ִ�����,����Ӱ�캯��
			 int result = preparedStatement.executeUpdate();
			 
			 
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.�ر�
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
	}

}
