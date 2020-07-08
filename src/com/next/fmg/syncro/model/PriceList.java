package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.main.Application;

public class PriceList {
	
	private String distributor_code;
	private List<PriceProduct> products;

	public PriceList() {
		super();
		this.products = new ArrayList<PriceProduct>();
		this.distributor_code = Application.DISTRIBUTOR_CODE;

		// TODO Auto-generated constructor stub
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public List<PriceProduct> getProducts() {
		return products;
	}

	public void setProducts(List<PriceProduct> products) {
		this.products = products;
	}
	
	public void addPriceProduct(PriceProduct priceProduct) {
		this.products.add(priceProduct);
	}

}
