package com.next.fmg.syncro.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Catalog;
import com.next.fmg.syncro.model.Product;
import com.next.fmg.syncro.model.RetailerAccount;

public class RestClient {
	
	public URL url;//your url i.e fetch data from .
	public HttpURLConnection conn;
	private int intentos;
	
	public RestClient() {
		super();
	}
	
	public WebResponse postRetailerAccount(RetailerAccount ra) throws IOException {
		
		WebResponse webResponse = new WebResponse();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			System.out.println("<-- postRetailerAccount(" + ra.getProperty_name() + ")");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			
			url = new URL("https://apiuat.unileverservices.com/sigma-magento-v1/sigmaapi/retailerRegisterationStatus");//your url i.e fetch data from .
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "RetailerAccount" + ra.getStore_id_ERP() + ".json");	
			gson.toJson(ra, writer);
			writer.flush();
			writer.close();
			
			String auxi = gson.toJson(ra);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			bw.write(auxi);
			bw.flush();
			bw.close();

					
			InputStreamReader _is;
			BufferedReader br;
			
			if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
			    _is = new InputStreamReader(conn.getInputStream());
			} else {
			     /* error from server */
			    _is = new InputStreamReader(conn.getErrorStream());
			}
			
			br = new BufferedReader(_is);
			
			StringBuilder builder = new StringBuilder();
			
			String output;
			
			while ((output = br.readLine()) != null) {
	
				builder.append(output);
			
			}
			
			String aux = builder.toString();
			
			webResponse.setResponseCode(conn.getResponseCode());
			
			if (conn.getResponseCode() < conn.HTTP_BAD_REQUEST) {
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Retailer enviado correctamente");
				
			} else {
			     /* error from server */
				webResponse.setResponseMessage(aux);
				
			}
		
			conn.disconnect();

		} catch (Exception e) {
			
			System.out.println("Exception in NetClientGet:- " + e);
			intentos++;
			continue;
		}
		
		return webResponse;
		}
		return webResponse;

	}
	
	
	
	
	public WebResponse postCatalog(Catalog catalog) throws IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
		
		WebResponse webResponse = new WebResponse();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			System.out.println("<-- postCatalog()");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			
			url = new URL("https://apiuat.unileverservices.com/sigma-magento-v1/sigmaapi/catalogDetail");//your url i.e fetch data from .
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + format.format(new Date()) +  "_Catalog"  + ".json");	
			gson.toJson(catalog, writer);
			writer.flush();
			writer.close();
			
			String auxi = gson.toJson(catalog);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			bw.write(auxi);
			bw.flush();
			bw.close();

					
			InputStreamReader _is;
			BufferedReader br;
			
			if (conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
			    _is = new InputStreamReader(conn.getInputStream());
			} else {
			     /* error from server */
			    _is = new InputStreamReader(conn.getErrorStream());
			}
			
			br = new BufferedReader(_is);
			
			StringBuilder builder = new StringBuilder();
			
			String output;
			
			while ((output = br.readLine()) != null) {
	
				builder.append(output);
			
			}
			
			String aux = builder.toString();
			
			webResponse.setResponseCode(conn.getResponseCode());
			
			if (conn.getResponseCode() < conn.HTTP_BAD_REQUEST) {
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Retailer enviado correctamente");
				
			} else {
			     /* error from server */
				webResponse.setResponseMessage(aux);
				
			}
		
			conn.disconnect();

		} catch (Exception e) {
			
			System.out.println("Exception in NetClientGet:- " + e);
			intentos++;
			continue;
		}
		
		return webResponse;
		}
		return webResponse;

	}


}
