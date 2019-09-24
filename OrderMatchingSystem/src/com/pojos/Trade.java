package com.pojos;

import java.sql.Timestamp;

public class Trade {
	private int tradeID;
	private int buyOrderID;
	private int sellOrderID;
	private int buyerID;
	private int sellerID;
	private double price;
	private Timestamp timestamp;
	private int quantity;
	
	public Trade(int tradeID, int buyOrderID, int sellOrderID, int buyerID, int sellerID, double price, Timestamp timestamp,
			int quantity) {
		super();
		this.tradeID = tradeID;
		this.buyOrderID = buyOrderID;
		this.sellOrderID = sellOrderID;
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.price = price;
		this.timestamp = timestamp;
		this.quantity = quantity;
	}
	
	public Trade() {
		tradeID = 0;
		buyOrderID = 0;
		sellOrderID = 0;
		buyerID = 0;
		sellerID = 0;
		price = 0.0;
		timestamp = new Timestamp(2019,01, 01, 00, 00, 00, 00);
		quantity = 0;
		// TODO Auto-generated constructor stub
	}
	
	
	public int getTradeID() {
		return tradeID;
	}

	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}

	public int getBuyOrderID() {
		return buyOrderID;
	}

	public void setBuyOrderID(int buyOrderID) {
		this.buyOrderID = buyOrderID;
	}

	public int getSellOrderID() {
		return sellOrderID;
	}

	public void setSellOrderID(int sellOrderID) {
		this.sellOrderID = sellOrderID;
	}

	public int getBuyerID() {
		return buyerID;
	}

	public void setBuyerID(int buyerID) {
		this.buyerID = buyerID;
	}

	public int getSellerID() {
		return sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Trade [tradeID=" + tradeID + ", buyOrderID=" + buyOrderID + ", sellOrderID=" + sellOrderID
				+ ", buyerID=" + buyerID + ", sellerID=" + sellerID + ", price=" + price + ", timestamp=" + timestamp
				+ ", quantity=" + quantity + "]";
	}
	
}
