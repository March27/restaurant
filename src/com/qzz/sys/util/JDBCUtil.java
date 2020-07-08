package com.qzz.sys.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {
/*public static void main(String[] args) {
	try {
		System.out.println(JDBCUtil.getConnection());
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
}*/
	private static DataSource ds = new ComboPooledDataSource();
	 
	// 获取连接池
		public static DataSource getDataSource() {
			return ds;
		}
	 
	// 获取连接
		public static Connection getConnection() throws SQLException {
			return ds.getConnection();
		}
	 
		/**
		 * 关闭连接
		 * @param resultSet
		 * @param pst
		 * @param connection
		 */
		public static void close(ResultSet resultSet, PreparedStatement pst, Connection connection) {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

}
