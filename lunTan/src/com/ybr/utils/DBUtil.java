package com.ybr.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	//自动去加载c3p0-config.xml文件中的默认配置
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return ds;
	}
	
	//目标获取connection对象
	public static Connection getConnection() throws SQLException {
		
		return ds.getConnection();
	}
	
	public static void close(Connection conn,Statement stmt) {
		try {
			if(null!=stmt) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(null!=conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		try {
			if(null!=rs) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn, stmt);
	}
}
