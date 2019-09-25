package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbFactory.GetConnection;
import com.pojos.Trade;

public class TradeDAOImpl implements TradeDAO{

	@Override
	public int addTrade(Trade trade) {
		int rows = 0;
		Connection conn = GetConnection.openConnection();
		String ADD_TRADE = "insert into ORDER values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(ADD_TRADE);
			ps.setInt(1, trade.getTradeID());
			ps.setInt(2, trade.getBuyOrderID());
			ps.setInt(3, trade.getSellOrderID());
			ps.setInt(4, trade.getBuyerID());
			ps.setInt(5, trade.getSellerID());
			ps.setDouble(6, trade.getPrice());
			//CHECK TYPE...............TIMESTAMP
			ps.setTimestamp(7, trade.getTimestamp());
			ps.setInt(8, trade.getQuantity());
			rows=ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return rows;
	}

	@Override
	public List<Trade> getTradesByUserID(int user_id) {
		List<Trade> trades = new ArrayList<Trade>();
		Connection conn = GetConnection.openConnection();
		String GET_TRADES_BY_USERID = "select * from TRADE where buyerID=? OR sellerID=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_TRADES_BY_USERID);
			ps.setInt(1, user_id);
			ps.setInt(2, user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Trade trade = new Trade(rs.getInt("tradeID"), rs.getInt("buyOrderID"), rs.getInt("sellOrderID"), rs.getInt("buyerID"), rs.getInt("sellerID"), 
						rs.getDouble("price"), rs.getTimestamp("timestamp"), rs.getInt("quantity"));
				trades.add(trade);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return trades;
	}

	@Override
	public List<Trade> getAll() {
		List<Trade> trades = new ArrayList<Trade>();
		Connection conn = GetConnection.openConnection();
		String GET_TRADES = "select * from TRADE";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_TRADES);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Trade trade = new Trade(rs.getInt("tradeID"), rs.getInt("buyOrderID"), rs.getInt("sellOrderID"), rs.getInt("buyerID"), rs.getInt("sellerID"), 
						rs.getDouble("price"), rs.getTimestamp("timestamp"), rs.getInt("quantity"));
				trades.add(trade);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return trades;
	}

}
