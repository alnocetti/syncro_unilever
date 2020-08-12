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
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Catalog;
import com.next.fmg.syncro.model.Inventory;
import com.next.fmg.syncro.model.OrderUpdate;
import com.next.fmg.syncro.model.PriceList;
import com.next.fmg.syncro.model.RetailerAccount;
import com.next.fmg.syncro.model.SalesOrderUpdate;

public class RestClient {
	
	public URL url;//your url i.e fetch data from .
	public HttpURLConnection conn;
	private int intentos;
	
	public RestClient() {
		super();
	}
	
	public WebResponse postRetailerAccount(RetailerAccount ra) throws IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		WebResponse webResponse = new WebResponse();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			System.out.println("<-- postRetailerAccount(" + ra.getProperty_name() + ")");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			
			url = new URL(Application.URL_RETAILERS);//your url i.e fetch data from .
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "Retailer/" + format.format(new Date()) + "_RetailerAccount_" + ra.getStore_id_ERP() + ".json");	
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
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		WebResponse webResponse = new WebResponse();	
		
		Catalog response = new Catalog();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			//System.out.println("<-- postCatalog()");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			//url = new URL("https://apiuat.unileverservices.com/sigma-magento-v1/sigmaapi/catalogDetail");//your url i.e fetch data from .
			url = new URL(Application.URL_CATALOG);
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "Catalog/" + format.format(new Date()) +  "_Catalog"  + ".json");	
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
			
			//Leo respuesta
			br = new BufferedReader(_is);
			
			StringBuilder builder = new StringBuilder();
			
			String output;
			
			while ((output = br.readLine()) != null) {
	
				builder.append(output);
			
			}
			
			String aux = builder.toString();
			
			webResponse.setResponseCode(conn.getResponseCode());
			
			if (conn.getResponseCode() < conn.HTTP_BAD_REQUEST) {
				
				/** Ver que hacer con este json que vuelve, es igual al que enviamos? **/
				
				TypeToken<Catalog> token = new TypeToken<Catalog>() {};
				
				response =  gson.fromJson(builder.toString(),  token.getType());
				
				/*******************************************************************/
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Catalog enviado correctamente");
				
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

	
	public WebResponse postInventory(Inventory inventory) throws IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		WebResponse webResponse = new WebResponse();	
		
		Inventory response = new Inventory();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			//System.out.println("<-- postCatalog()");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			url = new URL(Application.URL_INVENTORY);
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "Inventory/" + format.format(new Date()) +  "_Inventory"  + ".json");	
			gson.toJson(inventory, writer);
			writer.flush();
			writer.close();
			
			String auxi = gson.toJson(inventory);
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
			
			//Leo respuesta
			br = new BufferedReader(_is);
			
			StringBuilder builder = new StringBuilder();
			
			String output;
			
			while ((output = br.readLine()) != null) {
	
				builder.append(output);
			
			}
			
			String aux = builder.toString();
			
			webResponse.setResponseCode(conn.getResponseCode());
			
			if (conn.getResponseCode() < conn.HTTP_BAD_REQUEST) {
				
				/** Ver que hacer con este json que vuelve, es igual al que enviamos? **/
				
				TypeToken<Inventory> token = new TypeToken<Inventory>() {};
				
				response =  gson.fromJson(builder.toString(),  token.getType());
				
				/*******************************************************************/
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Stock enviado correctamente");
				
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

	
	public WebResponse postPrices(PriceList priceList) throws IOException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		
		WebResponse webResponse = new WebResponse();	
		
		PriceList response = new PriceList();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			//System.out.println("<-- postCatalog()");

		try {
			
			GsonBuilder builder = new GsonBuilder();
		   
			builder.serializeNulls();
		
			Gson gson = builder.setPrettyPrinting().create();
			
	//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
	//		gson.serializeNulls();
					
			url = new URL(Application.URL_PRICES);
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "Prices/" + format.format(new Date()) +  "_Prices"  + ".json");	
			gson.toJson(priceList, writer);
			writer.flush();
			writer.close();
			
			String auxi = gson.toJson(priceList);
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
			
			//Leo respuesta
			br = new BufferedReader(_is);
			
			StringBuilder builder1 = new StringBuilder();
			
			String output;
			
			while ((output = br.readLine()) != null) {
	
				builder1.append(output);
			
			}
			
			String aux = builder1.toString();
			
			webResponse.setResponseCode(conn.getResponseCode());
			
			if (conn.getResponseCode() < conn.HTTP_BAD_REQUEST) {
				
				/** Ver que hacer con este json que vuelve, es igual al que enviamos? **/
				
				TypeToken<PriceList> token = new TypeToken<PriceList>() {};
				
				response =  gson.fromJson(builder1.toString(),  token.getType());
				
				/*******************************************************************/
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Precio enviado correctamente");
				
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

	public WebResponse postSalesOrder(OrderUpdate order) throws IOException {
		
		SalesOrderUpdate salesOrderUpdate = new SalesOrderUpdate();
		
		salesOrderUpdate.setDistributor_code(Application.DISTRIBUTOR_CODE);
		
		salesOrderUpdate.addOrder(order);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		WebResponse webResponse = new WebResponse();
		
		intentos = 0;
		
		while(intentos <= 1) {
		
			System.out.println("<-- postSalesOrder(" + order.getOrder_id() + ")");

		try {
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
					
			
			url = new URL(Application.URL_SALES_ORDER_UPDATE);//your url i.e fetch data from .

			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			
			// seteo api-key 
			conn.addRequestProperty("client_id", Application.CLIENT_ID);
			conn.addRequestProperty("client_secret", Application.CLIENT_SECRET);
			
			conn.setReadTimeout(30000);
			conn.setConnectTimeout(30000);
			
			conn.setRequestProperty("Content-Type", "application/json");
			
			conn.setDoOutput(true);
			
			Writer writer =  new FileWriter(Application.DIR_JSON + "SalesOrder/" + format.format(new Date()) + "_SalesOrder_" + order.getOrder_id() + ".json");	
			gson.toJson(salesOrderUpdate, writer);
			writer.flush();
			writer.close();
			
			String auxi = gson.toJson(salesOrderUpdate);
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
				
				webResponse.setResponseMessage(conn.getResponseMessage() + ", Order enviada correctamente");
				
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
