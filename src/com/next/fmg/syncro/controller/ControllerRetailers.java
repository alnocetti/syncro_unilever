package com.next.fmg.syncro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.model.RetailerAccount;
import com.next.fmg.syncro.reader.ReaderRetailerAccount;
import com.next.fmg.syncro.rest.RestClient;
import com.next.fmg.syncro.rest.WebResponse;

public class ControllerRetailers {
	
	public static ControllerRetailers instance;
	public RestClient restClient;
	public ReaderRetailerAccount reader;
	
	  public static ControllerRetailers getInstance() throws RuntimeException {
	        
		   instance = instance == null ? new ControllerRetailers() : instance;
     
	       return instance;
	   }
	  
		public ControllerRetailers() {
			super();
			this.restClient = new RestClient();
			this.reader = new ReaderRetailerAccount();
			
			// TODO Auto-generated constructor stub
		}
		
		public List<WebResponse> postRetailers() throws IOException{
			System.out.println("<-- postArticulos");
			
			List<RetailerAccount> retailers = this.reader.readRetailers();
					
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
					
			for (RetailerAccount retailer : retailers) {
					
					WebResponse webResponse = this.restClient.postRetailerAccount(retailer);
					
					this.reader.saveResponse(retailer, webResponse.getResponseMessage());
					
					respuestas.add(webResponse);
			}
			
			return respuestas;
		}
}
