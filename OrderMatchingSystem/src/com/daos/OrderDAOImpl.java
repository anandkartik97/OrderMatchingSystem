package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbFactory.GetConnection;
import com.pojos.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order order) {
		int rows = 0;
		Connection conn = GetConnection.openConnection();
		String ADD_ORDER = "insert into ORDER values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(ADD_ORDER);
			ps.setInt(1, order.getOrderID());
			//CHECK TYPE...............TIMESTAMP
			ps.setTimestamp(2, order.getTimestamp());
			ps.setDouble(3, order.getPrice());
			ps.setString(4, order.getCategory());
			ps.setString(5, order.getStatus());
			ps.setInt(6, order.getQuantity());
			ps.setString(7, order.getType());
			ps.setString(8, order.getCondition());
			ps.setInt(9, order.getTraderID());
			ps.setInt(10, order.getISIN());
			ps.setInt(11, order.getDisclosedQuantity());
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
		String GET_ORDERS = "select * from ORDER";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_ORDERS);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("timestamp"), rs.getDouble("price"), rs.getString("category"), rs.getString("status"), 
						rs.getInt("quantity"), rs.getString("type"), rs.getString("condition"), rs.getInt("traderID"), rs.getInt("ISIN"), rs.getInt("disclosedQuantity"));
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
		String DELETE_ORDER = "delete from ORDER where orderID=?";
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
		String GET_WAITING_ORDERS = "select * from ORDER where status=? AND user_id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_WAITING_ORDERS);
			ps.setString(1, "waiting");
			ps.setInt(2, user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("timestamp"), rs.getDouble("price"), rs.getString("category"), rs.getString("status"), 
						rs.getInt("quantity"), rs.getString("type"), rs.getString("condition"), rs.getInt("traderID"), rs.getInt("ISIN"), rs.getInt("disclosedQuantity"));
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
		String GET_WAITING_ORDERS = "select * from ORDER where status=? AND user_id=?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(GET_WAITING_ORDERS);
			ps.setString(1, "rejected");
			ps.setInt(2, user_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())	{
				Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("timestamp"), rs.getDouble("price"), rs.getString("category"), rs.getString("status"), 
						rs.getInt("quantity"), rs.getString("type"), rs.getString("condition"), rs.getInt("traderID"), rs.getInt("ISIN"), rs.getInt("disclosedQuantity"));
				orders.add(order);
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
			String UPDATE_QUANTITY="update ORDER set quantity=? where orderID=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_QUANTITY);
			ps.setInt(1, quantity);
			ps.setLong(2, orderID);
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
	
	@Override
	public boolean updatePrice(int orderID, int price) {
		boolean isUpdated = false;
		Connection conn = GetConnection.openConnection();
		try	{
			String UPDATE_PRICE="update ORDER set price=? where orderID=?";
			PreparedStatement ps = conn.prepareStatement(UPDATE_PRICE);
			ps.setInt(1, price);
			ps.setLong(2, orderID);
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
