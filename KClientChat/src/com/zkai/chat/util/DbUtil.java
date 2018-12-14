package com.zkai.chat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @ClassName: DbUtil  
 * @Description: 数据库连接类 
 * @author Kai  
 * @date 2018年12月9日  
 *
 */
public class DbUtil {
	private static final String url = "jdbc:mysql://localhost:3306/chatroom?serverTimezone=UTC";
	private static final String uid = "root";
	private static final String pwd = "root";
	/**
	 * 
	 * @Title: getConnect  
	 * @Description: 连接数据库
	 * @return Connection   返回连接上数据库
	 */
	public static Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url, uid, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	/**
	 * 
	 * @Title: closeCon  
	 * @Description: 关闭连接  
	 * @param @param conn    参数  
	 * @return void    返回类型  
	 * @throws
	 */
	public static void closeCon(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
