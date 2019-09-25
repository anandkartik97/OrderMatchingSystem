package com.daos;

import java.util.List;

import com.pojos.Trade;

public interface TradeDAO {
	int addTrade(Trade trades);
	List<Trade> getTradesByUserID(int user_id);
	List<Trade> getAll();
}
