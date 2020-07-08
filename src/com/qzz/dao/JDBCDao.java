package com.qzz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qzz.util.JDBCUtil;

public class JDBCDao {
	

	/**
	 *  * 增加，删除，修改  
	 */
	public static void insertOrDeleteOrUpdate(String sql) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			int execute = pst.executeUpdate();
			System.out.println("执行语句：" + sql + "," + execute + "行数据受影响");
			JDBCUtil.close(null, pst, connection);
		} catch (SQLException e) {
			System.out.println("异常提醒：" + e);
		}
	}
 
	/**
	 *  * 查询，返回结果集  
	 */
	public static List<Map<String, Object>> select(String sql) {
		List<Map<String, Object>> returnResultToList = null;
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet resultSet = pst.executeQuery();
//	returnResultToList = returnResultToList(resultSet);
			JDBCUtil.close(resultSet, pst, connection);
		} catch (SQLException e) {
 
			System.out.println("异常提醒：" + e);
		}
		return returnResultToList;
	}
 
	/**
	 *  * 数据返回集合  * @param resultSet  * @return  * @throws SQLException  
	 */
	public static List<Map<String, Object>> returnResultToList(ResultSet resultSet) {
		List<Map<String, Object>> values = null;
		try {
			// 键: 存放列的别名, 值: 存放列的值.
			values = new ArrayList<>();
			// 存放字段名
			List<String> columnName = new ArrayList<>();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// 字段名
				columnName.add(rsmd.getColumnLabel(i + 1));
			}
 
			System.out.println("表字段为：");
			System.out.println(columnName);
			System.out.println("表数据为：");
			Map<String, Object> map = null;
			// 处理 ResultSet, 使用 while 循环
			while (resultSet.next()) {
				map = new HashMap<>();
				for (String column : columnName) {
					Object value = resultSet.getObject(column);
					map.put(column, value);
					System.out.print(value + "\t");
				}
				// 把一条记录的 Map 对象放入准备的 List 中
				values.add(map);
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("异常提醒：" + e);
		}
		return values;
	}

}
