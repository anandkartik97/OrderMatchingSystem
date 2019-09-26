package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.daos.OrderDAO;
import com.daos.OrderDAOImpl;
import com.pojos.Order;
import com.pojos.Stock;

class TestOrderDAO {

//	@Test
//	void testAddOrder() {
//		OrderDAO obj = new OrderDAOImpl();
//		java.sql.Timestamp timestamp = new java.sql.Timestamp(2018, 9, 23, 03, 38, 20, 0);
//		double num = 1000;
//		Stock stock = new Stock(1, "Microsoft", "MSF", 77, 75, 77, 0, 0, 76, 73, 80, 1000);
//		Order order = new Order(timestamp, num, "sell", "rejected", 75, "market", "allornone", 2, 1, stock);
//		assertEquals(1, obj.addOrder(order));
//	}
	
//	@Test
//	void testGetAll()	{
//		OrderDAO obj = new OrderDAOImpl();
//		List<Order> orders = obj.getAll();
//		assertEquals(2, orders.size());
//		Order order = orders.get(1);
//		assertEquals(7, order.getOrderID());
//		assertEquals("sell", order.getCategory());
//	}
	
//	@Test
//	void testDeleteOrder() {
//		OrderDAO obj = new OrderDAOImpl();
//		assertEquals(1, obj.deleteOrder(8));
//	}
	
//	@Test
//	void testGetWaitingOrders()	{
//		OrderDAO obj = new OrderDAOImpl();
//		List<Order> orders = obj.getWaitingOrders(2);
//		assertEquals(1, orders.size());
//		Order order = orders.get(0);
//		assertEquals(10, order.getOrderID());
//		assertEquals("sell", order.getCategory());
//	}
	
//	@Test
//	void testGetWaitingOrders()	{
//		OrderDAO obj = new OrderDAOImpl();
//		List<Order> orders = obj.getRejectedOrders(2);
//		assertEquals(1, orders.size());
//		Order order = orders.get(0);
//		assertEquals(11, order.getOrderID());
//		assertEquals("sell", order.getCategory());
//	}
	
//	@Test
//	void testUpdateQuantity()	{
//		OrderDAO obj = new OrderDAOImpl();
//		assertEquals(true, obj.updateQuantity(11, 70));
//	}
	
	@Test
	void testUpdatePrice()	{
		OrderDAO obj = new OrderDAOImpl();
		assertEquals(true, obj.updatePrice(11, 900));
	}

}
