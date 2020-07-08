package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.List;

public class PriceProduct {
	
	private String EAN;
	private List<Price> prices;
	
	public PriceProduct() {
		super();
		this.prices = new ArrayList<Price>();
		// TODO Auto-generated constructor stub
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public void addPrice(Price price) {
		this.prices.add(price);
	}
	
}
