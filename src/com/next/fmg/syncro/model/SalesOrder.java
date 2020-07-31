package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

public class SalesOrder {
	
	private String distributor_code;
	
	private List<Order> orders;

	public SalesOrder() {
		super();
		this.orders = new ArrayList<Order>();
		// TODO Auto-generated constructor stub
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		
		this.orders.add(order);
	}
	
}
