package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbFactory.GetConnection;
import com.pojos.Stock;
import com.pojos.Trade;

public class TradeDAOImpl implements TradeDAO{

	@Override
	public int addTrade(Trade trade) {
		int rows = 0;
		Connection conn = GetConnection.openConnection();
		String ADD_TRADE = "insert into ORDER values(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(ADD_TRADE);
			ps.setInt(1, trade.getBuyOrderID());
			ps.setInt(2, trade.getSellOrderID());
			ps.setInt(3, trade.getBuyerID());
			ps.setInt(4, trade.getSellerID());
			ps.setDouble(5, trade.getPrice());
			//CHECK TYPE...............TIMESTAMP
			ps.setTimestamp(6, trade.getTimestamp());
			ps.setInt(7, trade.getQuantity());
			rows=ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return rows;
	}

	//CHANGE HERE__________________
	@Override
	public List<Trade> getTradesByUserID(int user_id) {
		List<Trade> trades = new ArrayList<Trade>();
		Connection conn = GetConnection.openConnection();
		String GET_TRADES_BY_USERID = "select * from TRADE where buyerID=? OR sellerID=?";
		String GET_STOCK = "select * from STOCK where ISIN = ?";
		int ISIN;
		PreparedStatement ps1, ps2;
		try {
			ps1 = conn.prepareStatement(GET_TRADES_BY_USERID);
			ps1.setInt(1, user_id);
			ps1.setInt(2, user_id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())	{
				ISIN = rs1.getInt("ISIN");
				ps2 = conn.prepareStatement(GET_STOCK);
				ResultSet rs2 = ps2.executeQuery();
				Stock stock = null;
				while(rs2.next())	{
					stock = new Stock(rs2.getInt("ISIN"), rs2.getString("name"), rs2.getString("ticker")
							, rs2.getDouble("LTP"), rs2.getDouble("openingPrice"), rs2.getDouble("closingPrice"), 
							rs2.getDouble("circuitBreaker"), rs2.getDouble("tickSize"), rs2.getDouble("marketPrice"),
							rs2.getDouble("lowestPrice"), rs2.getDouble("highestPrice"), rs2.getInt("totalTradedVolume"));	
				}
				Trade trade = new Trade(rs1.getInt("tradeID"), rs1.getInt("buyOrderID"), rs1.getInt("sellOrderID"), rs1.getInt("buyerID"), rs1.getInt("sellerID"), 
						rs1.getDouble("price"), rs1.getTimestamp("timestamp"), rs1.getInt("quantity"), stock);
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
		String GET_STOCK = "select * from STOCK where ISIN = ?";
		int ISIN;
		PreparedStatement ps1, ps2;
		try {
			ps1 = conn.prepareStatement(GET_TRADES);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())	{
				ISIN = rs1.getInt("ISIN");
				ps2 = conn.prepareStatement(GET_STOCK);
				ResultSet rs2 = ps2.executeQuery();
				Stock stock = null;
				while(rs2.next())	{
					stock = new Stock(rs2.getInt("ISIN"), rs2.getString("name"), rs2.getString("ticker")
							, rs2.getDouble("LTP"), rs2.getDouble("openingPrice"), rs2.getDouble("closingPrice"), 
							rs2.getDouble("circuitBreaker"), rs2.getDouble("tickSize"), rs2.getDouble("marketPrice"),
							rs2.getDouble("lowestPrice"), rs2.getDouble("highestPrice"), rs2.getInt("totalTradedVolume"));	
				}
				Trade trade = new Trade(rs1.getInt("tradeID"), rs1.getInt("buyOrderID"), rs1.getInt("sellOrderID"), rs1.getInt("buyerID"), rs1.getInt("sellerID"), 
						rs1.getDouble("price"), rs1.getTimestamp("timestamp"), rs1.getInt("quantity"), stock);
				trades.add(trade);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return trades;
	}

}
