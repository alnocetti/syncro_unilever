package com.next.fmg.syncro.writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFWriter;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Order;
import com.next.fmg.syncro.model.OrderItem;
import com.next.fmg.syncro.model.SalesOrder;

public class WriterSalesOrder {

	public WriterSalesOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void downloadSalesOrder() throws IOException {
		
		//Read json into array
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String file = Application.DIR_DOWNLOAD_FTP + "SalesOrder/" + format.format(new Date()) + "_Sales_Order.json";
		
		List<SalesOrder>salesOrders = new ArrayList<SalesOrder>();
		
		Type token = new TypeToken<ArrayList<SalesOrder>>() {}.getType();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		
		String line = null;
		
		StringBuilder json = new StringBuilder();
		
		while((line = br.readLine()) != null){

			json = json.append(line);

	    }
		
		br.close();
		
		//JsonReader reader = new JsonReader(new FileReader(file));
		
		salesOrders = gson.fromJson(json.toString(), token);
		
		if(salesOrders == null) {
			System.out.println("Nothing to download");
			return;
		}
		
		// Write records into dbf
	
		DBFWriter writerOrder = null;
		DBFWriter writerItem = null;
		
		for(SalesOrder salesOrder : salesOrders) {
			
			for(Order order : salesOrder.getOrders()) {
			
				if(!existeOrder(order)) {
					
					System.out.println("<-- getSalesOrder(" + order.getOrder_id() + ")");
					
					writerOrder = new DBFWriter(new File(Application.DIR_LECTURA_DBF + "wborder.dbf"));
					
					Object rowOrder[] = new Object[34];
					
					rowOrder[0] = order.getOrder_id();
					rowOrder[1] = order.getStore_id_ERP();
					rowOrder[2] = order.getCreated_at();
					rowOrder[3] = order.getTotal_qty_ordered();
					rowOrder[4] = order.getCustomer_email();
					rowOrder[5] = order.getCustomer_firstname();
					rowOrder[6] = order.getCustomer_lastname();
					rowOrder[7] = order.getTotal_item_count();
					rowOrder[8] = order.getSubtotal();
					rowOrder[9] = order.getDiscount_amount();
					rowOrder[10] = order.getGrand_total();
					rowOrder[11] = order.getTotal_due();
					rowOrder[12] = order.getRemote_ip();
					rowOrder[13] = order.getStatus();
					rowOrder[14] = order.getPayment().getAmount_ordered();
					rowOrder[15] = order.getPayment().getMethod();
					rowOrder[16] = order.getShipping().getAddress().getEmail();
					rowOrder[17] = order.getShipping().getAddress().getFirstname();
					rowOrder[18] = order.getShipping().getAddress().getLastname();
					rowOrder[19] = order.getShipping().getAddress().getPostcode();
					rowOrder[20] = order.getShipping().getAddress().getStreet();
					rowOrder[21] = order.getShipping().getAddress().getStreet_number();
					rowOrder[22] = order.getShipping().getAddress().getDistrict();
					rowOrder[23] = order.getShipping().getAddress().getProvince();
					rowOrder[24] = order.getShipping().getAddress().getCountry();
					rowOrder[25] = order.getShipping().getAddress().getNeighborhood();
					rowOrder[26] = order.getShipment_type();
					rowOrder[27] = 1;
					rowOrder[28] = "";
					rowOrder[29] = "";
					rowOrder[30]	= formatAud.format(new Date());
					rowOrder[31] = "";
					rowOrder[32] = "";	
					rowOrder[33] = "";
					writerOrder.addRecord(rowOrder);
					
					writerOrder.close();
					
					for(OrderItem item : order.getItems()) {
						
						writerItem = new DBFWriter(new File(Application.DIR_LECTURA_DBF + "wborditm.dbf"));
						
						Object rowItem[] = new Object[12];
						
						rowItem[0] = order.getOrder_id();
						rowItem[1] = item.getName();
						rowItem[2] = item.getIndustry();
						rowItem[3] = item.getOriginal_price();
						rowItem[4] = item.getPrice();
						rowItem[5] = item.getPrice_incl_tax();
						rowItem[6] = item.getQty_ordered();
						rowItem[7] = item.getDiscount_amount();
						rowItem[8] = item.getRow_total();
						rowItem[9] = item.getRow_total_inclusive_tax();
						rowItem[10] = item.getEAN();
						rowItem[11] = item.getRow_weight();
						
						writerItem.addRecord(rowItem);
						
						writerItem.close();
					}
					
				}
			}
		}
		
		return;
		
	}
	
	private boolean existeOrder(Order order) throws FileNotFoundException {
		
		DBFReader reader = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborder.dbf"));
		
		DBFRow row;
		
		while ((row = reader.nextRow()) != null) {
			if(row.getString("ordid").equals(order.getOrder_id())) {
				
				return true;
				
			}
			
		}
		
		return false;
	}

}
