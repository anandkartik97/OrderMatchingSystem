package com.pojos;

public class DematAcc extends User {
	private long acc_no;
	private double balance;
	
	
	public DematAcc() {
		super();
		acc_no=0;
		balance=0;
	}

	public DematAcc(int user_id, String username, String password) {
		super(user_id, username, password);
		// TODO Auto-generated constructor stub
	}

	public DematAcc(long acc_no, double balance) {
		super();
		this.acc_no = acc_no;
		this.balance = balance;
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

	@Override
	public String toString() {
		return "DematAcc [acc_no=" + acc_no + ", balance=" + balance + "]";
	}
	
}
