package com.next.fmg.syncro.model;

public class StockProduct {
	
	private String EAN;
	private String stockitem_qty;
	private String stockitem_is_in_stock;
	private String min_thersold_qty;
	
	
	public StockProduct() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getEAN() {
		return EAN;
	}


	public void setEAN(String eAN) {
		EAN = eAN;
	}


	public String getStockitem_qty() {
		return stockitem_qty;
	}


	public void setStockitem_qty(String stockitem_qty) {
		this.stockitem_qty = stockitem_qty;
	}


	public String getStockitem_is_in_stock() {
		return stockitem_is_in_stock;
	}


	public void setStockitem_is_in_stock(String stockitem_is_in_stock) {
		this.stockitem_is_in_stock = stockitem_is_in_stock;
	}


	public String getMin_thersold_qty() {
		return min_thersold_qty;
	}


	public void setMin_thersold_qty(String min_thersold_qty) {
		this.min_thersold_qty = min_thersold_qty;
	}

}
