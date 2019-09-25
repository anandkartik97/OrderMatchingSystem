package com.pojos;
import java.sql.Timestamp;

public class Order {
	private int orderID;
	private Timestamp timestamp;	//sql.timestamp
	private double price;
	private String category; //buy or sell
	private String status; //pending,rejected and waiting
	private int quantity;
	private String type; //market or limit
	private String condition; //all or none,minimum fill etc.
	private int traderID; //user class object
	//private int ISIN;//stock object
	private int disclosedQuantity;
	private Stock stock;
	public Order() {
		super();
		orderID =0;
		timestamp = new Timestamp(2019, 01, 01, 00, 00, 00, 00);
		price = 0.0;
		category = "";
		status = "";
		quantity = 0;
		type = "";
		condition = "";
		traderID = 0;
		//ISIN = 0;
		disclosedQuantity = 0;
		stock = new Stock();
	}
	public Order(int orderID, Timestamp timestamp, double price, String category, String status, int quantity,
			String type, String condition, int traderID, int disclosedQuantity, Stock stock) {
		super();
		this.orderID = orderID;
		this.timestamp = timestamp;
		this.price = price;
		this.category = category;
		this.status = status;
		this.quantity = quantity;
		this.type = type;
		this.condition = condition;
		this.traderID = traderID;
		//ISIN = iSIN;
		this.disclosedQuantity = disclosedQuantity;
		this.stock = stock;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getTraderID() {
		return traderID;
	}
	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}
	public int getDisclosedQuantity() {
		return disclosedQuantity;
	}
	public void setDisclosedQuantity(int disclosedQuantity) {
		this.disclosedQuantity = disclosedQuantity;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", timestamp=" + timestamp + ", price=" + price + ", category=" + category
				+ ", status=" + status + ", quantity=" + quantity + ", type=" + type + ", condition=" + condition
				+ ", traderID=" + traderID + ", disclosedQuantity=" + disclosedQuantity + ", stock=" + stock + "]";
	}
	
	
}
