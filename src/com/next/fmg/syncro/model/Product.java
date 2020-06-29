package com.next.fmg.syncro.model;

public class Product {
	
	private String EAN;
	private String name_prod;
	private String status_prod;
	private int qty_ean_unit;
	private int qty_min_sell;
	private String industry_name;
	private String industry_code;
	private String short_description;
	private float default_price;
	private String brand;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEAN() {
		return EAN;
	}

	public void setEAN(String eAN) {
		EAN = eAN;
	}

	public String getName_prod() {
		return name_prod;
	}

	public void setName_prod(String name_prod) {
		this.name_prod = name_prod;
	}

	public String getStatus_prod() {
		return status_prod;
	}

	public void setStatus_prod(String status_prod) {
		this.status_prod = status_prod;
	}

	public int getQty_ean_unit() {
		return qty_ean_unit;
	}

	public void setQty_ean_unit(int qty_ean_unit) {
		this.qty_ean_unit = qty_ean_unit;
	}

	public int getQty_min_sell() {
		return qty_min_sell;
	}

	public void setQty_min_sell(int qty_min_sell) {
		this.qty_min_sell = qty_min_sell;
	}

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	public String getIndustry_code() {
		return industry_code;
	}

	public void setIndustry_code(String industry_code) {
		this.industry_code = industry_code;
	}

	public String getShort_description() {
		return short_description;
	}

	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}

	public float getDefault_price() {
		return default_price;
	}

	public void setDefault_price(float default_price) {
		this.default_price = default_price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	

}
