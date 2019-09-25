package com.pojos;

public class Stock {
	private int ISIN;
	private String name;
	private String ticker;	//Stock name in short
	private double LTP;
	private double openingPrice;
	private double closingPrice;
	private double circuitBreaker;
	private double tickSize;
	private double marketPrice;
	private double lowestPrice;
	private double highestPrice;
	private int totalTradedVolume;
	//private int buyLimitTotal;
	//private int sellLimitTotal;

	public Stock(int iSIN, String name, String ticker, double lTP, double openingPrice, double closingPrice,
			double circuitBreaker, double tickSize, double marketPrice, double lowestPrice, double highestPrice,
			int totalTradedVolume) {
		super();
		ISIN = iSIN;
		this.name = name;
		this.ticker = ticker;
		LTP = lTP;
		this.openingPrice = openingPrice;
		this.closingPrice = closingPrice;
		this.circuitBreaker = circuitBreaker;
		this.tickSize = tickSize;
		this.marketPrice = marketPrice;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
		this.totalTradedVolume = totalTradedVolume;
	}


	public Stock() {
		ISIN = 0;
		name = "";
		ticker = "";
		LTP = 0;
		openingPrice = 0;
		closingPrice = 0;
		circuitBreaker = 0;
		tickSize = 0;
		marketPrice = 0;
		lowestPrice = 0;
		highestPrice = 0;
		totalTradedVolume = 0;
	}


	public int getISIN() {
		return ISIN;
	}


	public void setISIN(int iSIN) {
		ISIN = iSIN;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTicker() {
		return ticker;
	}


	public void setTicker(String ticker) {
		this.ticker = ticker;
	}


	public double getLTP() {
		return LTP;
	}


	public void setLTP(double lTP) {
		LTP = lTP;
	}


	public double getOpeningPrice() {
		return openingPrice;
	}


	public void setOpeningPrice(double openingPrice) {
		this.openingPrice = openingPrice;
	}


	public double getClosingPrice() {
		return closingPrice;
	}


	public void setClosingPrice(double closingPrice) {
		this.closingPrice = closingPrice;
	}


	public double getCircuitBreaker() {
		return circuitBreaker;
	}


	public void setCircuitBreaker(double circuitBreaker) {
		this.circuitBreaker = circuitBreaker;
	}


	public double getTickSize() {
		return tickSize;
	}


	public void setTickSize(double tickSize) {
		this.tickSize = tickSize;
	}


	public double getMarketPrice() {
		return marketPrice;
	}


	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}


	public double getLowestPrice() {
		return lowestPrice;
	}


	public void setLowestPrice(double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}


	public double getHighestPrice() {
		return highestPrice;
	}


	public void setHighestPrice(double highestPrice) {
		this.highestPrice = highestPrice;
	}


	public int getTotalTradedVolume() {
		return totalTradedVolume;
	}


	public void setTotalTradedVolume(int totalTradedVolume) {
		this.totalTradedVolume = totalTradedVolume;
	}


	@Override
	public String toString() {
		return "Stock [ISIN=" + ISIN + ", name=" + name + ", ticker=" + ticker + ", LTP=" + LTP + ", openingPrice="
				+ openingPrice + ", closingPrice=" + closingPrice + ", circuitBreaker=" + circuitBreaker + ", tickSize="
				+ tickSize + ", marketPrice=" + marketPrice + ", lowestPrice=" + lowestPrice + ", highestPrice="
				+ highestPrice + ", totalTradedVolume=" + totalTradedVolume + "]";
	}
	
}