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
	 *  * ���ӣ�ɾ�����޸�  
	 */
	public static void insertOrDeleteOrUpdate(String sql) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement pst = connection.prepareStatement(sql);
			int execute = pst.executeUpdate();
			System.out.println("ִ����䣺" + sql + "," + execute + "��������Ӱ��");
			JDBCUtil.close(null, pst, connection);
		} catch (SQLException e) {
			System.out.println("�쳣���ѣ�" + e);
		}
	}
 
	/**
	 *  * ��ѯ�����ؽ����  
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
 
			System.out.println("�쳣���ѣ�" + e);
		}
		return returnResultToList;
	}
 
	/**
	 *  * ���ݷ��ؼ���  * @param resultSet  * @return  * @throws SQLException  
	 */
	public static List<Map<String, Object>> returnResultToList(ResultSet resultSet) {
		List<Map<String, Object>> values = null;
		try {
			// ��: ����еı���, ֵ: ����е�ֵ.
			values = new ArrayList<>();
			// ����ֶ���
			List<String> columnName = new ArrayList<>();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				// �ֶ���
				columnName.add(rsmd.getColumnLabel(i + 1));
			}
 
			System.out.println("���ֶ�Ϊ��");
			System.out.println(columnName);
			System.out.println("������Ϊ��");
			Map<String, Object> map = null;
			// ���� ResultSet, ʹ�� while ѭ��
			while (resultSet.next()) {
				map = new HashMap<>();
				for (String column : columnName) {
					Object value = resultSet.getObject(column);
					map.put(column, value);
					System.out.print(value + "\t");
				}
				// ��һ����¼�� Map �������׼���� List ��
				values.add(map);
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("�쳣���ѣ�" + e);
		}
		return values;
	}

}
