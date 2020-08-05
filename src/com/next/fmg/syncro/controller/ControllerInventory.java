package com.next.fmg.syncro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.model.Inventory;
import com.next.fmg.syncro.model.Product;
import com.next.fmg.syncro.model.StockProduct;
import com.next.fmg.syncro.reader.ReaderInventory;
import com.next.fmg.syncro.rest.RestClient;
import com.next.fmg.syncro.rest.WebResponse;

public class ControllerInventory {
	
	public static ControllerInventory instance;
	public RestClient restClient;
	public ReaderInventory reader;
	
	  public static ControllerInventory getInstance() throws RuntimeException {
	        
		   instance = instance == null ? new ControllerInventory() : instance;
     
	       return instance;
	   }
	  
		public ControllerInventory() {
			super();
			this.restClient = new RestClient();
			this.reader = new ReaderInventory();
			// TODO Auto-generated constructor stub
		}
		
		public List<WebResponse> postInventory() throws IOException{
			
			int particion = 50;

			System.out.println("<-- postInventory()");
			
			Inventory inventory = this.reader.readInventory();
			if (inventory.getSourceItems().isEmpty()) {
				System.out.println("Nothing to send");
				return null;
			}
			
			List<StockProduct> products = inventory.getSourceItems();
			List<StockProduct> subProducts = new ArrayList<StockProduct>();
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
			WebResponse webResponse = new WebResponse();
			
			for (int i = 1; i <= (Math.floor((products.size() / particion))+1); i++) {
				System.out.println("Enviando: " + i + " de "+ (Math.floor((products.size() / particion))+1) + " particiones");

				if(i == (Math.floor((products.size() / particion))+1))
					subProducts = products.subList((i-1) * particion, products.size());
				else
					subProducts = products.subList((i-1) * particion, (i * particion) -1);
				
				inventory.setSourceItems(subProducts);
				
				webResponse = this.restClient.postInventory(inventory);

				for (StockProduct stockProduct : inventory.getSourceItems()) {
					System.out.println("Grabando respuesta producto: " + stockProduct.getEAN());
					this.reader.saveResponse(stockProduct, webResponse.getResponseMessage());
					
				}
			}
			

			
			return respuestas;
		}
		

}
