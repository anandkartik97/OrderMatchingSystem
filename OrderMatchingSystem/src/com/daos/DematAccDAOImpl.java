package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dbFactory.GetConnection;

public class DematAccDAOImpl implements DematAccDAO{	

	@Override
	public double getBalance(long acc_no) {
		double balance = 0;
		Connection conn = GetConnection.openConnection();
		String GET_BALANCE = "select balance from DEMAT where acc_no = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_BALANCE);
			ps.setLong(1, acc_no);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				balance = rs.getDouble("balance");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return balance;
	}

	@Override
	public boolean updateBalance(double balance, long acc_no) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_BALANCE="update DEMAT set balance=? where acc_no=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_BALANCE);
			ps.setDouble(1, balance);
			ps.setLong(2, acc_no);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

}
