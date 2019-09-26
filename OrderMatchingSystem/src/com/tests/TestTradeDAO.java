package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.daos.TradeDAO;
import com.daos.TradeDAOImpl;
import com.pojos.Stock;
import com.pojos.Trade;

class TestTradeDAO {

//	@Test
//	void testAddOrder() {
//		TradeDAO obj = new TradeDAOImpl();
//		java.sql.Timestamp timestamp = new java.sql.Timestamp(2018, 9, 23, 03, 38, 20, 0);
//		double num = 75;
//		Stock stock = new Stock(1, "Microsoft", "MSF", 77, 75, 77, 0, 0, 76, 73, 80, 1000);
//		Trade trade = new Trade(6, 7, 1, 2, num, timestamp, 10, stock);
//		assertEquals(1, obj.addTrade(trade));
//	}
	
	@Test
	void testGetTradesByUserID()	{
		TradeDAO obj = new TradeDAOImpl();
		List<Trade> trades = obj.getTradesByUserID(1);
		assertEquals(2, trades.size());
		Trade trade = trades.get(1);
		assertEquals(2, trade.getTradeID());
		assertEquals(6, trade.getBuyOrderID());
	}
	
	@Test
	void testGetAll() {
		TradeDAO obj = new TradeDAOImpl();
		List<Trade> trades = obj.getAll();
		assertEquals(2, trades.size());
		Trade trade = trades.get(1);
		assertEquals(2, trade.getTradeID());
		assertEquals(6, trade.getBuyOrderID());
	}

}
