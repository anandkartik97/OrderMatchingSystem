package com.daos;

import java.util.List;

import com.pojos.Stock;

public interface StockDAO {
	List<Stock> findAllStocks();
	boolean updateLTP(int ISIN, double LTP);
	boolean updateOpeningPrice(int ISIN, double openingPrice);
	boolean updateClosingPrice(int ISIN, double closingPrice);
	boolean updateLowestPrice(int ISIN, double lowestPrice);
	boolean updateHighestPrice(int ISIN, double highestPrice);
	boolean updateTotalTradedVolume(int ISIN, double totalTradedVolume);
	boolean updateMarketPrice(int ISIN, double marketPrice);
	//boolean updatebuyLimitTotal(int buyLimitTotal);
	//boolean updatesellLimitTotal(int sellLimitTotal);
}
