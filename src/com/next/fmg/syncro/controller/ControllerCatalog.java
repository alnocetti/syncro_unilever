package com.next.fmg.syncro.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.model.Catalog;
import com.next.fmg.syncro.model.Product;
import com.next.fmg.syncro.model.RetailerAccount;
import com.next.fmg.syncro.reader.ReaderCatalog;
import com.next.fmg.syncro.rest.RestClient;
import com.next.fmg.syncro.rest.WebResponse;

public class ControllerCatalog {

	public static ControllerCatalog instance;
	public RestClient restClient;
	public ReaderCatalog reader;
	
	  public static ControllerCatalog getInstance() throws RuntimeException {
	        
		   instance = instance == null ? new ControllerCatalog() : instance;
     
	       return instance;
	   }
	  
		public ControllerCatalog() {
			super();
			this.restClient = new RestClient();
			this.reader = new ReaderCatalog();
			// TODO Auto-generated constructor stub
		}
		
		public List<WebResponse> postCatalog() throws IOException{
			
			int particion = 50;
			
			System.out.println("<-- postCatalog()");
			
			Catalog catalog = this.reader.readCatalog();
			if (catalog.getProducts().isEmpty()) {
				System.out.println("Nothing to send");
				return null;
			}
			
			List<Product> products = catalog.getProducts();
			List<Product> subProducts = new ArrayList<Product>();
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
			WebResponse webResponse = new WebResponse();
			
			for (int i = 1; i <= (Math.floor((products.size() / particion))+1); i++) {
				System.out.println("Enviando: " + i + " de "+ (Math.floor((products.size() / particion))+1) + " particiones");

				if(i == (Math.floor((products.size() / particion))+1))
					subProducts = products.subList((i-1) * particion, products.size());
				else
					subProducts = products.subList((i-1) * particion, (i * particion) -1);
				
				catalog.setProducts(subProducts);
				
				webResponse = this.restClient.postCatalog(catalog);
				
				for (Product product : catalog.getProducts()) {
					System.out.println("Grabando respuesta producto: " + product.getName_prod());
					this.reader.saveResponse(product, webResponse.getResponseMessage());
					
				}
	        }
						
			return respuestas;
		}
		
}
