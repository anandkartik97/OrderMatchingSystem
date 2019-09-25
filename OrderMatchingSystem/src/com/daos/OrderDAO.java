package com.daos;

import java.util.List;

import com.pojos.Order;

public interface OrderDAO {
	int addOrder(Order order);
	List<Order> getAll();
	int deleteOrder(int orderID);
	List<Order> getWaitingOrders(int user_id);
	List<Order> getRejectedOrders(int user_id);
	boolean updateQuantity(int orderID,int quantity);
	boolean updatePrice(int orderID,int price);
}
