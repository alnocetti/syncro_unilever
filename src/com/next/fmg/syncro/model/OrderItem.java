package com.next.fmg.syncro.model;

public class OrderItem {
	
	private String name;
	private String industry;
	private String original_price;
	private String price;
	private String price_incl_tax;
	private String qty_ordered;
	private String discount_amount;
	private String row_total;
	private String row_total_inclusive_tax;
	private String EAN;
	private String row_weight;
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice_incl_tax() {
		return price_incl_tax;
	}

	public void setPrice_incl_tax(String price_incl_tax) {
		this.price_incl_tax = price_incl_tax;
	}

	public String getQty_ordered() {
		return qty_ordered;
	}

	public void setQty_ordered(String qty_ordered) {
		this.qty_ordered = qty_ordered;
	}

	public String getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}

	public String getRow_total() {
		return row_total;
	}

	public void setRow_total(String row_total) {
		this.row_total = row_total;
	}

	public String getRow_total_inclusive_tax() {
		return row_total_inclusive_tax;
	}

	public void setRow_total_inclusive_tax(String row_total_inclusive_tax) {
		this.row_total_inclusive_tax = row_total_inclusive_tax;
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getRow_weight() {
		return row_weight;
	}

	public void setRow_weight(String row_weight) {
		this.row_weight = row_weight;
	}
	
	

}
