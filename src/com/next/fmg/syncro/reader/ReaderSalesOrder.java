package com.next.fmg.syncro.reader;

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

import org.yasas.xbase4j.XBase;
import org.yasas.xbase4j.XBaseFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Order;
import com.next.fmg.syncro.model.OrderItem;
import com.next.fmg.syncro.model.OrderPayment;
import com.next.fmg.syncro.model.OrderShipping;
import com.next.fmg.syncro.model.OrderShippingAddress;
import com.next.fmg.syncro.model.OrderUpdate;
import com.next.fmg.syncro.model.RetailerAccount;
import com.next.fmg.syncro.model.SalesOrder;

public class ReaderSalesOrder {
	
	private DBFReader readerOrders;
	private DBFReader readerItems;
	private DBFReader readerItemsChanged;
	
	public int cantidadLeida;

	public ReaderSalesOrder() {
		super();
		this.cantidadLeida = 0;
		this.readerOrders = null;
		this.readerItems = null;

	}
	
	public List<Order> readOrders(){
		
		List<Order> orders = new ArrayList<Order>();
		
		readerOrders = null;
		readerItems = null;
		
		try {
			
			readerOrders = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborder.dbf"));
			
			readerItems = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborditm.dbf"));
		
			DBFRow rowOrder;
			
			while ((rowOrder = readerOrders.nextRow()) != null) {
				
				if(rowOrder.getInt("ordgrabo") == 2) {

					Order order = new Order();
					
					order.setOrder_id(rowOrder.getString("ordid"));
					
					order.setStore_id_ERP(rowOrder.getString("ordiderp"));

					order.setCreated_at(rowOrder.getString("ordfecha"));
					
					order.setTotal_qty_ordered(rowOrder.getString("ordcantot"));
					
					order.setCustomer_email(rowOrder.getString("ordemail"));
					
					order.setCustomer_firstname(rowOrder.getString("ordnombre"));
					
					order.setCustomer_lastname(rowOrder.getString("ordapelli"));
					
					order.setTotal_item_count(rowOrder.getString("ordcanitm"));
					
					order.setSubtotal(rowOrder.getString("ordsubtot"));
					
					order.setDiscount_amount(rowOrder.getString("orddescue"));
					
					order.setGrand_total(rowOrder.getString("ordgrtot"));
					
					order.setTotal_due(rowOrder.getString("ordtotdue"));
					
					order.setRemote_ip(rowOrder.getString("ordip"));
					
					order.setStatus(rowOrder.getString("ordstat"));
					
					DBFRow rowItem;
					
					while ((rowItem = readerItems.nextRow()) != null) {
						
						if(rowItem.getString("itmid").equals(order.getOrder_id())) {
							
							OrderItem item = new OrderItem();
							
							item.setName(rowItem.getString("itmname"));
							
							item.setIndustry(rowItem.getString("itmindstry"));
							
							item.setOriginal_price(rowItem.getString("itmoripr"));
							
							item.setPrice(rowItem.getString("itmprice"));
							
							item.setPrice_incl_tax(rowItem.getString("itmprctx"));
							
							item.setQty_ordered(rowItem.getString("itmqtyord"));
							
							item.setDiscount_amount(rowItem.getString("itmdisc"));
							
							item.setRow_total(rowItem.getString("itmrwtot"));
							
							item.setRow_total_inclusive_tax(rowItem.getString("itmrwtottx"));
							
							item.setEAN(rowItem.getString("itmean"));
							
							item.setRow_weight(rowItem.getString("itmrwwght"));
							
							order.addItem(item);
						}
						
					}
					
					OrderPayment payment = new OrderPayment();
					
					payment.setAmount_ordered(rowOrder.getString("ordpyamnt"));
					
					payment.setMethod(rowOrder.getString("ordpymthd"));
					
					order.setPayment(payment);
					
					OrderShipping shipping = new OrderShipping();
					
					OrderShippingAddress address = new OrderShippingAddress();
					
					address.setEmail(rowOrder.getString("ordademail"));
					
					address.setFirstname(rowOrder.getString("ordadnomb"));
					
					address.setLastname(rowOrder.getString("ordadape"));
					
					address.setPostcode(rowOrder.getString("ordadpc"));
					
					address.setStreet(rowOrder.getString("ordadstr"));
					
					address.setStreet_number(rowOrder.getString("ordadnum"));
					
					address.setDistrict(rowOrder.getString("ordaddist"));
					
					address.setProvince(rowOrder.getString("ordadprov"));
					
					address.setCountry(rowOrder.getString("ordadcount"));
					
					address.setNeighborhood(rowOrder.getString("ordadneig"));
					
					shipping.setAddress(address);
					
					order.setShipment_type(rowOrder.getString("ordshiptyp"));
					
					order.setShipping(shipping);
					
					orders.add(order);
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(readerOrders);
			DBFUtils.close(readerItems);
		}
		
		return orders;
		
	}
	
	public List<OrderUpdate> readOrdersUpdate(){
		
		List<OrderUpdate> orders = new ArrayList<OrderUpdate>();
		
		readerOrders = null;
		readerItems = null;
		readerItemsChanged = null;
		
		try {
			
			readerOrders = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborder.dbf"));
					
			DBFRow rowOrder;
			
			while ((rowOrder = readerOrders.nextRow()) != null) {
				
				if(rowOrder.getInt("ordgrabo") == 2) {
					
					readerItems = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborditm.dbf"));
					
					readerItemsChanged = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbordchg.dbf"));

					OrderUpdate order = new OrderUpdate();
					
					order.setOrder_source(rowOrder.getString("ordsource"));
					
					order.setErp_order_id(rowOrder.getString("orderpnro"));
					
					order.setOrder_id(rowOrder.getString("ordid"));
					
					order.setStore_id_ERP(rowOrder.getString("ordiderp"));

					order.setUpdated_at(rowOrder.getString("ordfecha"));
					
					order.setTotal_qty_ordered(rowOrder.getString("ordcantot"));
					
					order.setTotal_item_count(rowOrder.getString("ordcanitm"));
					
					order.setSubtotal(rowOrder.getString("ordsubtot"));
					
					order.setDiscount_amount(rowOrder.getString("orddescue"));
					
					order.setGrand_total(rowOrder.getString("ordgrtot"));
					
					order.setTotal_due(rowOrder.getString("ordtotdue"));
										
					order.setStatus(rowOrder.getString("ordstat"));
					
					DBFRow rowItem;
					
					while ((rowItem = readerItems.nextRow()) != null) {
						
						if(rowItem.getString("itmid").equals(order.getOrder_id())) {
							
							OrderItem item = new OrderItem();
							
							item.setName(rowItem.getString("itmname"));
							
							item.setIndustry(rowItem.getString("itmindstry"));
							
							item.setOriginal_price(rowItem.getString("itmoripr"));
							
							item.setPrice(rowItem.getString("itmprice"));
							
							item.setPrice_incl_tax(rowItem.getString("itmprctx"));
							
							item.setQty_ordered(rowItem.getString("itmqtyord"));
							
							item.setDiscount_amount(rowItem.getString("itmdisc"));
							
							item.setRow_total(rowItem.getString("itmrwtot"));
							
							item.setRow_total_inclusive_tax(rowItem.getString("itmrwtottx"));
							
							item.setEAN(rowItem.getString("itmean"));
							
							item.setRow_weight(rowItem.getString("itmrwwght"));
							
							order.addItem(item);
						}
						
					}
					
					DBFUtils.close(readerItems);
					
					//add items changed
					DBFRow rowItemChanged;
					
					while ((rowItemChanged = readerItemsChanged.nextRow()) != null) {
						
						if(rowItemChanged.getString("chgordid").equals(order.getOrder_id())) {
							
							order.addItemChanged(rowItemChanged.getString("chgordean"), rowItemChanged.getString("chgordreas"));
							
						}
					}

					DBFUtils.close(readerItemsChanged);

					
					OrderPayment payment = new OrderPayment();
					
					payment.setAmount_ordered(rowOrder.getString("ordpyamnt"));
					
					payment.setMethod(rowOrder.getString("ordpymthd"));
					
					order.setPayment(payment);
					
					OrderShipping shipping = new OrderShipping();
					
					OrderShippingAddress address = new OrderShippingAddress();
					
					address.setEmail(rowOrder.getString("ordademail"));
					
					address.setFirstname(rowOrder.getString("ordadnomb"));
					
					address.setLastname(rowOrder.getString("ordadape"));
					
					address.setPostcode(rowOrder.getString("ordadpc"));
					
					address.setStreet(rowOrder.getString("ordadstr"));
					
					address.setStreet_number(rowOrder.getString("ordadnum"));
					
					address.setDistrict(rowOrder.getString("ordaddist"));
					
					address.setProvince(rowOrder.getString("ordadprov"));
					
					address.setCountry(rowOrder.getString("ordadcount"));
					
					address.setNeighborhood(rowOrder.getString("ordadneig"));
					
					shipping.setAddress(address);
					
					order.setShipment_type(rowOrder.getString("ordshiptyp"));
					
					order.setShipping(shipping);
					
					order.setCancellation_reason(rowOrder.getString("ordcanrea"));
					
					orders.add(order);
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(readerOrders);
		}
		
		return orders;
		
	}
	
	
	public void updateSalesOrder() throws IOException {
		
		//Read json into array
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String file = Application.DIR_DOWNLOAD_FTP + "SalesOrder/" + format.format(new Date()) + "_Sales_Order_Update.json";
		
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
				
		salesOrders = gson.fromJson(json.toString(), token);
		
		if(salesOrders == null) {
			System.out.println("Nothing to update");
			return;
		}
		
		for(SalesOrder salesOrder : salesOrders) {
			
			for(Order order : salesOrder.getOrders()) {
				
				updateOrder(order.getOrder_id(), order.getStatus());
				
			}
		}
		
	}
	
	
	public void saveResponse(String orderId, String response) {
		
		try {

			SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wborder.dbf"));
			
			writer.go(getNroRegistro(orderId));	
							
			writer.setValue("ordrespues", response);
			
			writer.setValue("ordgrabo", 1);
			
			writer.setValue("ordaudsync", formatAud.format(new Date()));
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void saveResponseUpdate(String orderId, String response) {
		
		try {

			SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wborder.dbf"));
			
			writer.go(getNroRegistro(orderId));	
							
			writer.setValue("ordrespues", response);
			
			writer.setValue("ordgrabo", 3);
			
			writer.setValue("ordaudsync", formatAud.format(new Date()));
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(String orderId) {
		
		DBFReader readerNroRegistro = null;
		
		int registro = 0;
		

		// create a DBFReader object
		try {
			readerNroRegistro = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wborder.dbf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFRow row;

		while ((row = readerNroRegistro.nextRow()) != null) {
					
			if(row.getString("ordid").equals(orderId)) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	
	public void updateOrder(String orderId, String state) {
		
		try {

			SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wborder.dbf"));
			
			writer.go(getNroRegistro(orderId));	
							
			writer.setValue("ordstat", state);
			
			writer.setValue("ordgrabo", 1);
			
			writer.setValue("ordaudsync", formatAud.format(new Date()));
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
