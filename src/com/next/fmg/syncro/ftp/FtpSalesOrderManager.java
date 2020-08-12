package com.next.fmg.syncro.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;

import com.next.fmg.syncro.main.Application;

public class FtpSalesOrderManager {
	
	FTPClient client;
	
	public FtpSalesOrderManager() {
		super();
		// TODO Auto-generated constructor stub
		this.client = new FTPClient();
	}

	public void downloadOrders() {
		
	       try {
	            client.connect(Application.FTP_URL);
	            
	            client.login(Application.FTP_USER, Application.FTP_PASSWORD);
	            
	            client.setControlEncoding("UTF-8");
	            
	            //Download Sales order
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    			            
	            String remoteFile = format.format(new Date()) + "_Sales_Order.json";
	            
	            File downloadFile = new File(Application.DIR_DOWNLOAD_FTP + "SalesOrder/"+ format.format(new Date()) + "_Sales_Order.json");
	            
	 	        FileOutputStream fos = new FileOutputStream(downloadFile);
	 	           
	 	        client.retrieveFile("/" + remoteFile, fos);
	 	        
	 	        //Download Sales orderUpdate
	            remoteFile = format.format(new Date()) + "_Sales_Order_Update.json";
	            
	            downloadFile = new File(Application.DIR_DOWNLOAD_FTP + "SalesOrder/"+ format.format(new Date()) + "_Sales_Order_Update.json");
	            
	 	        fos = new FileOutputStream(downloadFile);
	 	           
	 	        client.retrieveFile("/" + remoteFile, fos);
	 	        
		        fos.close();
	 			
	 	        client.disconnect();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                client.disconnect();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            

	 		
	        }
	       
	       
	  
	}
	

}
