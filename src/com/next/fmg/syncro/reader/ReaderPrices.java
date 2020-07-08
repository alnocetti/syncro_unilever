package com.next.fmg.syncro.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.yasas.xbase4j.XBase;
import org.yasas.xbase4j.XBaseFile;

import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Price;
import com.next.fmg.syncro.model.PriceList;
import com.next.fmg.syncro.model.PriceProduct;
import com.next.fmg.syncro.model.StockProduct;

public class ReaderPrices {
	
	private DBFReader reader;
	public int cantidadLeida;

	public ReaderPrices() {
		super();
		this.cantidadLeida = 0;
		this.reader = null;
	}
	
	public PriceList readPriceList(){
		
		PriceList priceList = new PriceList();
				
		reader = null;
		
		try {
			
			reader = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbprecio.dbf"));
		
			DBFRow row;
						
			while ((row = reader.nextRow()) != null) {

				PriceProduct priceProduct = new PriceProduct();
				
				priceProduct.setEAN(row.getString("preean"));
				
				String auxEAN = row.getString("preean");
				
				while(row.getInt("pregrabo") == 2 && auxEAN.equals(row.getString("preean")) && row != null) {

					Price price = new Price();
										
					price.setStore_id_ERP(row.getString("precliente"));
					
					price.setPrice(Float.toString(row.getFloat("preprecio")));
					
					price.setSpecial_price(Float.toString(row.getFloat("prepreesp")));
					
					price.setSpecial_price_start_date(row.getString("predfecha"));
					
					price.setSpecial_price_end_date(row.getString("prehfecha"));
					
					price.setDiscount_value(Float.toString(row.getFloat("predescue")));
					
					price.setDiscount_type(Integer.toString(row.getInt("predestip")));
					
					price.setQuantity_min_product(Integer.toString(row.getInt("precmin")));
					
					price.setQuantity_max_product(Integer.toString(row.getInt("precmax")));
					
					price.setValid_week_day(row.getString("predias"));
					
					price.setRetailer_group(Integer.toString(row.getInt("precanal")));
					
					priceProduct.addPrice(price);
					
					row = reader.nextRow();
					
				}
				
				if (!priceProduct.getPrices().isEmpty())
					priceList.addPriceProduct(priceProduct);
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(reader);
		}
		
		return priceList;
		
	}
	
	
	public void saveResponse(Price price, String ean, String response) {
		
		try {
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wbprecio.dbf"));
			
			writer.go(getNroRegistro(price,ean));	
							
			writer.setValue("prerespues", response);
			
			writer.setValue("pregrabo", 1);
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(Price price, String ean) {
		
		DBFReader readerNroRegistro = null;
		
		int registro = 0;
		

		// create a DBFReader object
		try {
			readerNroRegistro = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbprecio.dbf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFRow row;

		while ((row = readerNroRegistro.nextRow()) != null) {
					
			if(row.getString("preprecio").equals(price.getPrice()) && row.getInt("pregrabo")==2 && row.getString("preean").equals(ean)) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	
	

}
