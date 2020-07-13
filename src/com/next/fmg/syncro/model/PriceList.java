package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	public void addPriceToProduct(PriceProduct product, Price price) {
		for(PriceProduct pp : this.products) {
			if(pp.getEAN().equals(product.getEAN())) {
				pp.addPrice(price);
			}
		}
	}
	
	public boolean existeProducto(String ean) {
		for(PriceProduct pp : this.products) {
			if(pp.getEAN().equals(ean))
				return true;
		}
		return false;
	}
	
	public void limpiarVacios() {
		for(Iterator it = this.products.iterator(); it.hasNext();) {
			PriceProduct pp = (PriceProduct) it.next();
			
			if(pp.getPrices().isEmpty()) {
				it.remove();
			}
		}

	}

}
