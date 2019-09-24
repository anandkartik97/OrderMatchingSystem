package com.pojos;

public class User {
	public int user_id;
	public String username;
	public String email_id;
	public long phone_no;
	public String address;
	
	public User() {
		user_id=0;
		username="no name";
		email_id="abc@gmail.com";
		phone_no= 0;
		address="Pune";
	}
	public User(int user_id, String username, String email_id, int phone_no, String address) {
		this.user_id = user_id;
		this.username = username;
		this.email_id = email_id;
		this.phone_no = phone_no;
		this.address = address;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public long getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String toString() {
		return user_id + ":" + username;
	}
}
