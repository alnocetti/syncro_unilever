package com.next.fmg.syncro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.model.Inventory;
import com.next.fmg.syncro.model.Price;
import com.next.fmg.syncro.model.PriceList;
import com.next.fmg.syncro.model.PriceProduct;
import com.next.fmg.syncro.model.Product;
import com.next.fmg.syncro.model.StockProduct;
import com.next.fmg.syncro.reader.ReaderInventory;
import com.next.fmg.syncro.reader.ReaderPrices;
import com.next.fmg.syncro.rest.RestClient;
import com.next.fmg.syncro.rest.WebResponse;

public class ControllerPrices {
	
	public static ControllerPrices instance;
	public RestClient restClient;
	public ReaderPrices reader;
	
	  public static ControllerPrices getInstance() throws RuntimeException {
	        
		   instance = instance == null ? new ControllerPrices() : instance;
     
	       return instance;
	   }
	  
		public ControllerPrices() {
			super();
			this.restClient = new RestClient();
			this.reader = new ReaderPrices();
			// TODO Auto-generated constructor stub
		}
		
		public List<WebResponse> postPrices() throws IOException{
			
			int particion = 50;

			System.out.println("<-- postPrices()");
			
			PriceList priceList = this.reader.readPriceList();
			if (priceList.getProducts().isEmpty()) {
				System.out.println("Nothing to send");
				return null;
			}
			
			List<PriceProduct> products = priceList.getProducts();
			List<PriceProduct> subProducts = new ArrayList<PriceProduct>();
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
			WebResponse webResponse = new WebResponse();
			
			for (int i = 1; i <= (Math.floor((products.size() / particion))+1); i++) {
				
				System.out.println("Enviando: " + i + " de "+ (Math.floor((products.size() / particion))+1)  + " particiones");
				
				if(i == (Math.floor((products.size() / particion))+1))
					subProducts = products.subList((i-1) * particion, products.size());
				else
					subProducts = products.subList((i-1) * particion, (i * particion) -1);

				priceList.setProducts(subProducts);
				
				webResponse = this.restClient.postPrices(priceList);
				
				for (PriceProduct priceProduct : priceList.getProducts()) {
					
					System.out.println("Grabando respuesta producto: " + priceProduct.getEAN());

					for(Price price : priceProduct.getPrices()) {
						
						this.reader.saveResponse(price, priceProduct.getEAN(),webResponse.getResponseMessage());
					}
					
				}
			}

						

			
			return respuestas;
		}
		

}
