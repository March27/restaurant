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
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
		
		//2.sql语句
			String sql = "select * from comment " + 
					"	left join user on comment.user_id=user.id" + 
					"	where food_id=? and disabled = 0";
			System.out.println(sql);
		
		//3.集装箱
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setInt(1, foodid);
			 
			
		//4.执行语句
			 resultSet = preparedStatement.executeQuery();
			 
			 
			List<Comment> comments = new ArrayList<>();//将查询出来的信息进行封装
			while(resultSet.next()) {//指针下移一位，因为将表头信息也查询出来了，而表头信息不需要封装
				Comment comment = new Comment();
				//根据列名获取列值，是什么类型的就get什么类型
				comment.setId(resultSet.getInt("id"));
				comment.setCreateTime(resultSet.getTimestamp("comment_time"));
				comment.setContent(resultSet.getString("content"));
				comment.setDisabled(resultSet.getInt("disabled"));
				comment.setFoodId(resultSet.getInt("food_id"));
				comment.setNameId(resultSet.getInt("user_id"));
				
				User user = new User();
				user.setName(resultSet.getString("username"));
				
				comment.setUser(user);
				comments.add(comment);//添加到集合里去
//				System.out.println(comments);
			}
			return comments;
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public void comment(int userId, int foodId, String content) {
		// TODO 自动生成的方法存根
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		//1.连接数据库
		try {
			connection = JDBCUtil.getConnection();
			
			
		//2.sql语句
			String sql = "insert into comment (user_id,food_id,content,comment_time) values (?,?,?,NOW())";
			System.out.println(sql);
		
		//3.集装箱   Statement.RETURN_GENERATED_KEYS获取主键id
			 preparedStatement = connection.prepareStatement(sql);
			 //索引从1开始,是什么类型的就set什么类型
			 preparedStatement.setInt(1, userId);
			 preparedStatement.setInt(2, foodId);
			 preparedStatement.setString(3, content);
			 
			 
			
		//4.执行语句,返回影响函数
			 int result = preparedStatement.executeUpdate();
			 
			 
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			//5.关闭
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
	}

}
