package com.dbFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	
	private static Connection conn= null;
	
	public static Connection openConnection()
	{
		conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

}
