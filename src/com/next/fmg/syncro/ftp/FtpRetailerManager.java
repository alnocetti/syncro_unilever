package com.next.fmg.syncro.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;

import com.next.fmg.syncro.main.Application;

public class FtpRetailerManager {
	
	FTPClient client;
	
	public FtpRetailerManager() {
		super();
		// TODO Auto-generated constructor stub
		this.client = new FTPClient();
	}

	public void downloadRetailers() {
		
		
	       try {
	            client.connect("ftp.disanmi.net");
	            
	            client.login("u153383246.magento", "biiBO5Ms");
	            
	            client.setControlEncoding("UTF-8");
	            
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    			            
	            String remoteFile = format.format(new Date()) + "_Retailer.json";
	            
	            File downloadFile = new File(Application.DIR_LECTURA_JSON + format.format(new Date()) + "_Retailer.json");
	             
	            
	 	        FileOutputStream fos = new FileOutputStream(downloadFile);
	 	           
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
