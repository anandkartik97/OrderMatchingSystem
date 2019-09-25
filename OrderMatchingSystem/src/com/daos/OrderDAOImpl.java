package com.daos;

import java.util.List;

import com.pojos.Order;

public class OrderDAOImpl implements OrderDAO{

	@Override
	public int addOrder(Order orders) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMatchedOrders(List<Order> orders) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> getWaitingOrders(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getRejectedOrders(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelOrder(int orderID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrders(int orderID, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

}
