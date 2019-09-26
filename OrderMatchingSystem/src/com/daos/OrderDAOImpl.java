package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbFactory.GetConnection;
import com.pojos.Order;
import com.pojos.Stock;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order order) {
		int rows = 0;
		Connection conn = GetConnection.openConnection();
		String ADD_ORDER = "insert into ORDERS (TIMESTAMP, PRICE, CATEGORY, STATUS, QUANTITY, TYPE, CONDITION, TRADERID, ISIN, DISCLOSEDQUANTITY) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(ADD_ORDER);
			//CHECK TYPE...............TIMESTAMP
			ps.setTimestamp(1, order.getTimestamp());
			ps.setDouble(2, order.getPrice());
			ps.setString(3, order.getCategory());
			ps.setString(4, order.getStatus());
			ps.setInt(5, order.getQuantity());
			ps.setString(6, order.getType());
			ps.setString(7, order.getCondition());
			ps.setInt(8, order.getTraderID());
			ps.setInt(9, order.getStock().getISIN());
			ps.setInt(10, order.getDisclosedQuantity());
			rows=ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return rows;
	}

	@Override
	public List<Order> getAll() {
		List<Order> orders = new ArrayList<Order>();
		Connection conn = GetConnection.openConnection();
		String GET_ORDERS = "select * from ORDERS";
		String GET_STOCK = "select * from STOCK where ISIN = ?";
		int ISIN;
		PreparedStatement ps1, ps2;
		try {
			ps1 = conn.prepareStatement(GET_ORDERS);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())	{
				ISIN = rs1.getInt("ISIN");
				ps2 = conn.prepareStatement(GET_STOCK);
				ps2.setInt(1, ISIN);
				ResultSet rs2 = ps2.executeQuery();
				Stock stock = null;
				while(rs2.next())	{
					stock = new Stock(rs2.getInt("ISIN"), rs2.getString("name"), rs2.getString("ticker")
							, rs2.getDouble("LTP"), rs2.getDouble("openingPrice"), rs2.getDouble("closingPrice"), 
							rs2.getDouble("circuitBreaker"), rs2.getDouble("tickSize"), rs2.getDouble("marketPrice"),
							rs2.getDouble("lowestPrice"), rs2.getDouble("highestPrice"), rs2.getInt("totalTradedVolume"));	
				}
				Order order = new Order(rs1.getInt("orderID"), rs1.getTimestamp("timestamp"), rs1.getDouble("price"), rs1.getString("category"), rs1.getString("status"), 
						rs1.getInt("quantity"), rs1.getString("type"), rs1.getString("condition"), rs1.getInt("traderID"), rs1.getInt("disclosedQuantity"), stock);
				orders.add(order);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return orders;
	}

	@Override
	public int deleteOrder(int orderID) {
		int rows = 0;
		Connection conn = GetConnection.openConnection();
		String DELETE_ORDER = "delete from ORDERS where orderID=?";
		PreparedStatement ps;
		try {
				ps = conn.prepareStatement(DELETE_ORDER);
				ps.setInt(1, orderID);
				rows = ps.executeUpdate();
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return rows;
	}

	@Override
	public List<Order> getWaitingOrders(int user_id) {
		List<Order> orders = new ArrayList<Order>();
		Connection conn = GetConnection.openConnection();
		String GET_WAITING_ORDERS = "select * from ORDERS where status=? AND TRADERID=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_WAITING_ORDERS);
			ps.setString(1, "waiting");
			ps.setInt(2, user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Stock stock = new Stock();
				stock.setISIN(rs.getInt("ISIN"));
				Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("timestamp"), rs.getDouble("price"), rs.getString("category"), rs.getString("status"), 
						rs.getInt("quantity"), rs.getString("type"), rs.getString("condition"), rs.getInt("traderID"), rs.getInt("disclosedQuantity"), stock);
				orders.add(order);

			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return orders;
	}

	@Override
	public List<Order> getRejectedOrders(int user_id) {
		List<Order> orders = new ArrayList<Order>();
		Connection conn = GetConnection.openConnection();
		String GET_WAITING_ORDERS = "select * from ORDERS where status=? AND TRADERID=?";
		String GET_STOCK = "select * from STOCK where ISIN = ?";
		int ISIN;
		PreparedStatement ps1, ps2;
		try {
			ps1 = conn.prepareStatement(GET_WAITING_ORDERS);
			ps1.setString(1, "rejected");
			ps1.setInt(2, user_id);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())	{
				ISIN = rs1.getInt("ISIN");
				ps2 = conn.prepareStatement(GET_STOCK);
				ps2.setInt(1, ISIN);
				ResultSet rs2 = ps2.executeQuery();
				Stock stock = null;
				while(rs2.next())	{
					stock = new Stock(rs2.getInt("ISIN"), rs2.getString("name"), rs2.getString("ticker")
							, rs2.getDouble("LTP"), rs2.getDouble("openingPrice"), rs2.getDouble("closingPrice"), 
							rs2.getDouble("circuitBreaker"), rs2.getDouble("tickSize"), rs2.getDouble("marketPrice"),
							rs2.getDouble("lowestPrice"), rs2.getDouble("highestPrice"), rs2.getInt("totalTradedVolume"));	
				
					Order order = new Order(rs1.getInt("orderID"), rs1.getTimestamp("timestamp"), rs1.getDouble("price"), rs1.getString("category"), rs1.getString("status"), 
							rs1.getInt("quantity"), rs1.getString("type"), rs1.getString("condition"), rs1.getInt("traderID"), rs1.getInt("disclosedQuantity"), stock);
					orders.add(order);
				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return orders;
	}

	@Override
	public boolean updateQuantity(int orderID, int quantity) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_QUANTITY="update ORDERS set quantity=? where orderID=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_QUANTITY);
			ps.setInt(1, quantity);
			ps.setLong(2, orderID);
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
	public boolean updatePrice(int orderID, int price) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_PRICE="update ORDERS set price=? where orderID=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_PRICE);
			ps.setInt(1, price);
			ps.setLong(2, orderID);
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
