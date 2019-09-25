package com.daos;

public interface DematAccDAO {
	double getBalance(long acc_no);
	boolean updateBalance(double balance, long acc_no);
}
