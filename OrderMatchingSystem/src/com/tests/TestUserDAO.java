package com.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.daos.UserDAO;
import com.daos.UserDAOImpl;

class TestUserDAO {

	@Test
	void testAuthentication() {
		UserDAO obj = new UserDAOImpl();
		assertEquals(true, obj.authentication("prani", "prani"));
	}

}
