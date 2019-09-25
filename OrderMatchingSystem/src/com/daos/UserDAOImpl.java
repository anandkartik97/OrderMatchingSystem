package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbFactory.GetConnection;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean authentication(String username, String password) {
		boolean isAuthenticated = false;
		Connection conn = GetConnection.openConnection();
		String GET_USER = "select * from USERS where username=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_USER);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				if(rs.getString("password").equals(password))	{
					isAuthenticated = true;
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return isAuthenticated;
	}

}
