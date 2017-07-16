package com.forest.domain;

public class Order {
	private Integer id;
	private Double money;
	private String receiverInfo;
	private Customer c;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer id, Double money, String receiverInfo, Customer c) {
		super();
		this.id = id;
		this.money = money;
		this.receiverInfo = receiverInfo;
		this.c = c;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(String receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public Customer getC() {
		return c;
	}

	public void setC(Customer c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", money=" + money + ", receiverInfo=" + receiverInfo + ", c=" + c + "]";
	}
	
}
