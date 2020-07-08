package com.next.fmg.syncro.model;

public class Price {
	
	private String store_id_ERP;
	private String price;
	private String special_price;
	private String special_price_start_date;
	private String special_price_end_date;
	private String discount_value;
	private String discount_type;
	private String quantity_min_product;
	private String quantity_max_product;
	private String valid_week_day;
	private String retailer_group;
	   
	public Price() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStore_id_ERP() {
		return store_id_ERP;
	}

	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSpecial_price() {
		return special_price;
	}

	public void setSpecial_price(String special_price) {
		this.special_price = special_price;
	}

	public String getSpecial_price_start_date() {
		return special_price_start_date;
	}

	public void setSpecial_price_start_date(String special_price_start_date) {
		this.special_price_start_date = special_price_start_date;
	}

	public String getSpecial_price_end_date() {
		return special_price_end_date;
	}

	public void setSpecial_price_end_date(String special_price_end_date) {
		this.special_price_end_date = special_price_end_date;
	}

	public String getDiscount_value() {
		return discount_value;
	}

	public void setDiscount_value(String discount_value) {
		this.discount_value = discount_value;
	}

	public String getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}

	public String getQuantity_min_product() {
		return quantity_min_product;
	}

	public void setQuantity_min_product(String quantity_min_product) {
		this.quantity_min_product = quantity_min_product;
	}

	public String getQuantity_max_product() {
		return quantity_max_product;
	}

	public void setQuantity_max_product(String quantity_max_product) {
		this.quantity_max_product = quantity_max_product;
	}

	public String getValid_week_day() {
		return valid_week_day;
	}

	public void setValid_week_day(String valid_week_day) {
		this.valid_week_day = valid_week_day;
	}

	public String getRetailer_group() {
		return retailer_group;
	}

	public void setRetailer_group(String retailer_group) {
		this.retailer_group = retailer_group;
	}
	
	public boolean equals(Price price) {
		if(price.getStore_id_ERP().equals(this.store_id_ERP) &&
				price.getPrice().equals(this.price) &&
				price.getSpecial_price().equals(this.special_price) &&
				price.getSpecial_price_start_date().equals(this.special_price_start_date) &&
				price.getSpecial_price_end_date().equals(this.special_price_end_date) &&
				price.getDiscount_value().equals(this.discount_value) &&
				price.getDiscount_type().equals(this.discount_type) &&
				price.getQuantity_min_product().equals(this.quantity_min_product) &&
				price.getQuantity_max_product().equals(this.quantity_max_product) &&
				price.getValid_week_day().equals(this.valid_week_day) &&
				price.getRetailer_group().equals(this.retailer_group))
			return true;
			
		return false;
	}

}
