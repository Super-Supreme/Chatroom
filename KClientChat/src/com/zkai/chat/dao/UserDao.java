package com.zkai.chat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zkai.chat.model.User;
import com.zkai.chat.util.DbUtil;


public class UserDao {
	public User login(Connection conn, User user) {
		User resultUser = null;
		String sql = "select * from c_user where username=? and password=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resultUser = new User();
				resultUser.setId(rs.getInt("id"));
				resultUser.setUsername(rs.getString("username"));
				resultUser.setPassword(rs.getString("password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeCon(conn);
		}
		return resultUser;
	}
	public boolean addUser(Connection conn,User user) {
		String sql="insert into c_user (username,password) values(?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DbUtil.closeCon(conn);
		}
		
		
	}
}
