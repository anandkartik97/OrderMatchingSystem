package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.daos.OrderDAO;
import com.daos.OrderDAOImpl;
import com.daos.StockDAO;
import com.daos.StockDAOImpl;
import com.pojos.Stock;

class TestStockDAO {

	@Test
	void testFindAllStocks() {
		StockDAO obj = new StockDAOImpl();
		List<Stock> stocks = obj.findAllStocks();
		assertEquals(1, stocks.size());
		Stock stock = stocks.get(0);
		assertEquals("MSF", stock.getTicker());
		assertEquals(1, stock.getISIN());
	}
	
	@Test
	void testUpdateLTP() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateLTP(1, 70));
	}
	
	@Test
	void testUpdateOpeningPrice() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateOpeningPrice(1, 73));
	}
	
	@Test
	void testUpdateClosingPrice() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateClosingPrice(1, 78));
	}
	
	@Test
	void testUpdateLowestPrice() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateLowestPrice(1, 71));
	}
	
	@Test
	void testUpdateHighestPrice() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateHighestPrice(1, 78));
	}
	
	@Test
	void testUpdateTotalTradedVolume() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateTotalTradedVolume(1, 1010));
	}
	
	@Test
	void testUpdateMarketPrice() {
		StockDAO obj = new StockDAOImpl();
		assertEquals(true, obj.updateMarketPrice(1, 75));
	}

}
