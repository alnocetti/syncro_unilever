package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.main.Application;

public class Catalog {
	
	private String distributor_code;
	private List<Product> products;
	
	public Catalog() {
		super();
		this.products = new ArrayList<Product>();
		this.distributor_code = Application.DISTRIBUTOR_CODE;
		// TODO Auto-generated constructor stub
	}

	public String getDistributor_code() {
		return distributor_code;
	}

	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	
	

}
