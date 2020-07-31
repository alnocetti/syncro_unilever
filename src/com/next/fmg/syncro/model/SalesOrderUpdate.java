package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderUpdate {
	
	private String distributor_code;
	
	private List<OrderUpdate> orders;

	public SalesOrderUpdate() {
		super();
		// TODO Auto-generated constructor stub
		this.orders = new ArrayList<OrderUpdate>();
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public List<OrderUpdate> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderUpdate> orders) {
		this.orders = orders;
	}

	public void addOrder(OrderUpdate order) {
		
		this.orders.add(order);
	}
	
}
