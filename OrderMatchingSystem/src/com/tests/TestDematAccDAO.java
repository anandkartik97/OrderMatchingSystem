package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.daos.DematAccDAO;
import com.daos.DematAccDAOImpl;

class TestDematAccDAO {

	@Test
	void testGetBalance() {
		DematAccDAO obj = new DematAccDAOImpl();
		double balance = obj.getBalance(101);
		double num = 1000;
		assertEquals(balance, num);
	}
	
	@Test
	void testUpdateBalance() {
		DematAccDAO obj = new DematAccDAOImpl();
		assertEquals(obj.updateBalance(5000, 101), true);
	}

}
