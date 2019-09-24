package com.pojos;

public class DematAcc {
	public String username;
	private String password;
	private long acc_no;
	private double balance;
	protected int user_id;
	
	public DematAcc() {
		username="new name";
		password="xyz";
		acc_no=0;
		balance=0;
		user_id=0;		
	}
	public DematAcc(String username, String password, long acc_no, double balance, int user_id) {
		super();
		this.username = username;
		this.password = password;
		this.acc_no = acc_no;
		this.balance = balance;
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(long acc_no) {
		this.acc_no = acc_no;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String toString() {
		return user_id + ":" + username;
	}
}
