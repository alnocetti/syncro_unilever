package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.main.Application;

public class Inventory {

	private String distributor_code;
	private List<StockProduct> sourceItems;
	public Inventory() {
		super();
		this.sourceItems = new ArrayList<StockProduct>();
		this.distributor_code = Application.DISTRIBUTOR_CODE;
		// TODO Auto-generated constructor stub
	}
	public String getDistributor_code() {
		return distributor_code;
	}
	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}
	public List<StockProduct> getSourceItems() {
		return sourceItems;
	}
	public void setSourceItems(List<StockProduct> sourceItems) {
		this.sourceItems = sourceItems;
	}
	
	public void addProduct(StockProduct stockProduct) {
		this.sourceItems.add(stockProduct);
	}
	
	
}
