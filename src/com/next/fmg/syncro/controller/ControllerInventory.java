package com.next.fmg.syncro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.model.Inventory;
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
			System.out.println("<-- postInventory()");
			
			Inventory inventory = this.reader.readInventory();
			
			if (inventory.getSourceItems().isEmpty()) {
				System.out.println("Nada para enviar");
				return null;
			}
			
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
			
			WebResponse webResponse = this.restClient.postInventory(inventory);

			for (StockProduct stockProduct : inventory.getSourceItems()) {
				
				this.reader.saveResponse(stockProduct, webResponse.getResponseMessage());
				
			}
			
			return respuestas;
		}
		

}
