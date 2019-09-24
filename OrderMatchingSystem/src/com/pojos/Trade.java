package com.pojos;

import java.sql.Time;

public class Trade {
	private int tradeID;
	private int buyerID;
	private int sellerID;
	private double price;
	private Time timestamp;
	private int quantity;
	public Trade(int tradeID, int buyerID, int sellerID, double price, Time timestamp, int quantity) {
		super();
		this.tradeID = tradeID;
		this.buyerID = buyerID;
		this.sellerID = sellerID;
		this.price = price;
		this.timestamp = timestamp;
		this.quantity = quantity;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
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
	public Time getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Time timestamp) {
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
		return "Trade [tradeID=" + tradeID + ", buyerID=" + buyerID + ", sellerID=" + sellerID + ", price=" + price
				+ ", timestamp=" + timestamp + ", quantity=" + quantity + "]";
	}
	
	

}
