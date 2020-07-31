package com.next.fmg.syncro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.next.fmg.syncro.ftp.FtpSalesOrderManager;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Order;
import com.next.fmg.syncro.model.OrderUpdate;
import com.next.fmg.syncro.model.RetailerAccount;
import com.next.fmg.syncro.model.SalesOrder;
import com.next.fmg.syncro.model.SalesOrderUpdate;
import com.next.fmg.syncro.reader.ReaderSalesOrder;
import com.next.fmg.syncro.rest.RestClient;
import com.next.fmg.syncro.rest.WebResponse;
import com.next.fmg.syncro.writer.WriterSalesOrder;

public class ControllerSalesOrder {
	
	public static ControllerSalesOrder instance;
	public RestClient restClient;
	
	
	public ReaderSalesOrder reader;
	public WriterSalesOrder writer;
	private FtpSalesOrderManager ftp;
	
	  public static ControllerSalesOrder getInstance() throws RuntimeException {
	        
		   instance = instance == null ? new ControllerSalesOrder() : instance;
     
	       return instance;
	   }
	  
		public ControllerSalesOrder() {
			super();
			this.restClient = new RestClient();
			this.reader = new ReaderSalesOrder();
			this.writer = new WriterSalesOrder();
			this.ftp = new FtpSalesOrderManager();
			// TODO Auto-generated constructor stub
		}
		
		public List<WebResponse> postSalesOrder() throws IOException{
			System.out.println("<-- postSalesOrder");
			
			SalesOrderUpdate salesOrderUpdate = new SalesOrderUpdate();
			List<OrderUpdate> orders = this.reader.readOrdersUpdate();
			salesOrderUpdate.setDistributor_code(Application.DISTRIBUTOR_CODE);
			salesOrderUpdate.setOrders(orders);
			
			if (orders.isEmpty()) {
				System.out.println("Nothing to send");
				return null;
			}
					
			List<WebResponse> respuestas = new ArrayList<WebResponse>();
					
			for (OrderUpdate order : salesOrderUpdate.getOrders()) {
					
					WebResponse webResponse = this.restClient.postSalesOrder(order);
					
					this.reader.saveResponseUpdate(order.getOrder_id(), webResponse.getResponseMessage());
					
					respuestas.add(webResponse);
			}
			
			return respuestas;
		}
		
		public void downloadSalesOrder() throws IOException {
			
			System.out.println("<-- downloadSalesOrder");
			
			this.writer.downloadSalesOrder();
			
		}
		
		public void updateSalesOrder() throws IOException{
			
			System.out.println("<-- updateSalesOrder");
			
			this.reader.updateSalesOrder();
		}
		
		public void downloadSalesOrderFTP() {
			
			System.out.println("<-- downloadSalesOrderByFTP");
			
			ftp.downloadOrders();
		}
}
