package com.zkai.chat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @ClassName: DbUtil  
 * @Description: ���ݿ������� 
 * @author Kai  
 * @date 2018��12��9��  
 *
 */
public class DbUtil {
	private static final String url = "jdbc:mysql://localhost:3306/chatroom?serverTimezone=UTC";
	private static final String uid = "root";
	private static final String pwd = "root";
	/**
	 * 
	 * @Title: getConnect  
	 * @Description: �������ݿ�
	 * @return Connection   �������������ݿ�
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
	 * @Description: �ر�����  
	 * @param @param conn    ����  
	 * @return void    ��������  
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
