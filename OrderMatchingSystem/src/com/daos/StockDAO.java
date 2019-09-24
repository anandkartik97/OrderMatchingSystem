package com.daos;

import java.util.List;

import com.pojos.Stock;

public interface StockDAO {
	List<Stock> findAllStocks();
	boolean updateLTP(double LTP);
	boolean updateOpeningPrice(double openingPrice);
	boolean updateClosingPrice(double closingPrice);
	boolean updateLowestPrice(double lowestPrice);
	boolean updateHighestPrice(double highestPrice);
	boolean updateTotalTradedVolume(double totalTradedVolume);
	boolean updateMarketPrice(double marketPrice);
	//boolean updatebuyLimitTotal(int buyLimitTotal);
	//boolean updatesellLimitTotal(int sellLimitTotal);
}
