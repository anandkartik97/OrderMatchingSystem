package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbFactory.GetConnection;
import com.pojos.Stock;

public class StockDAOImpl implements StockDAO{

	@Override
	public List<Stock> findAllStocks() {
		List<Stock> stocks = new ArrayList<Stock>();
		Connection conn = GetConnection.openConnection();
		String GET_STOCKS = "select * from STOCK";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_STOCKS);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Stock stock = new Stock(rs.getInt("ISIN"), rs.getString("name"), rs.getString("ticker")
						, rs.getDouble("LTP"), rs.getDouble("openingPrice"), rs.getDouble("closingPrice"), 
						rs.getDouble("circuitBreaker"), rs.getDouble("tickSize"), rs.getDouble("marketPrice"),
						rs.getDouble("lowestPrice"), rs.getDouble("highestPrice"), rs.getInt("totalTradedVolume"));
				stocks.add(stock);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return stocks;
	}

	@Override
	public boolean updateLTP(int ISIN, double LTP) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_LTP="update STOCK set LTP=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_LTP);
			ps.setDouble(1, LTP);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateOpeningPrice(int ISIN, double openingPrice) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_OPENING_PRICE="update STOCK set openingPrice=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_OPENING_PRICE);
			ps.setDouble(1, openingPrice);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateClosingPrice(int ISIN, double closingPrice) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_CLOSING_PRICE="update STOCK set closingPrice=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_CLOSING_PRICE);
			ps.setDouble(1, closingPrice);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateLowestPrice(int ISIN, double lowestPrice) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_LOWEST_PRICE="update STOCK set lowestPrice=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_LOWEST_PRICE);
			ps.setDouble(1, lowestPrice);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateHighestPrice(int ISIN, double highestPrice) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_HIGHEST_PRICE="update STOCK set highestPrice=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_HIGHEST_PRICE);
			ps.setDouble(1, highestPrice);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateTotalTradedVolume(int ISIN, double totalTradedVolume) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_TOTAL_TRADED_VOLUME="update STOCK set totalTradedVolume=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_TOTAL_TRADED_VOLUME);
			ps.setDouble(1, totalTradedVolume);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}

	@Override
	public boolean updateMarketPrice(int ISIN, double marketPrice) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_MARKET_PRICE="update STOCK set marketPrice=? where ISIN=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_MARKET_PRICE);
			ps.setDouble(1, marketPrice);
			ps.setInt(2, ISIN);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}		
		return isUpdated;
	}
}
