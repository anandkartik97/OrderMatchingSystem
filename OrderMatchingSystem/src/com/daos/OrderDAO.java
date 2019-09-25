package com.daos;

import java.util.List;

import com.pojos.Order;

public interface OrderDAO {
	int addOrder(Order orders);
	List<Order> getAll();
	int deleteMatchedOrders(List<Order> orders);
	List<Order> getWaitingOrders(int user_id);
	List<Order> getRejectedOrders(int user_id);
	boolean cancelOrder(int orderID);
	boolean updateOrders(int orderID,int quantity);
}
