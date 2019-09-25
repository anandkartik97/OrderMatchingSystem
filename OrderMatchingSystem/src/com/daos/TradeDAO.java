package com.daos;

import java.util.List;

import com.pojos.Trade;

public interface TradeDAO {
	int addTrade(Trade trade);
	List<Trade> getTradesByUserID(int user_id);
	List<Trade> getAll();
}
