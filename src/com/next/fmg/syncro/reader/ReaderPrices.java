package com.next.fmg.syncro.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

				if(row.getInt("pregrabo") == 2) {
					
					PriceProduct priceProduct = new PriceProduct();
					
					priceProduct.setEAN(row.getString("preean"));
					
					String auxEAN = row.getString("preean");
					
					if (!priceList.existeProducto(auxEAN))
						priceList.addPriceProduct(priceProduct);
				
					Price price = new Price();
											
					if(row.getString("precliente").isEmpty()) 
						price.setStore_id_ERP(null);
					else 
						price.setStore_id_ERP(row.getString("precliente"));
					
					price.setPrice(Float.toString(row.getFloat("preprecio")));
					
					if(row.getFloat("prepreesp") == 0)
						price.setSpecial_price(null);
					else
						price.setSpecial_price(Float.toString(row.getFloat("prepreesp")));
					
					if(row.getString("predfecha").isEmpty())
						price.setSpecial_price_start_date(null);
					else
						price.setSpecial_price_start_date(row.getString("predfecha"));
					
					if(row.getString("prehfecha").isEmpty())
						price.setSpecial_price_end_date(null);
					else
						price.setSpecial_price_end_date(row.getString("prehfecha"));
					
					if(row.getFloat("predescue") == 0)
						price.setDiscount_value(null);
					else
						price.setDiscount_value(Float.toString(row.getFloat("predescue")));
					
					if(row.getInt("predestip") == 0)
						price.setDiscount_type(null);
					else
						price.setDiscount_type(Integer.toString(row.getInt("predestip")));
					
					if(row.getInt("precmin") == 0)
							price.setQuantity_min_product(null);
					else
						price.setQuantity_min_product(Integer.toString(row.getInt("precmin")));
					
					if(row.getInt("precmax") == 0)
						price.setQuantity_max_product(null);
					else
						price.setQuantity_max_product(Integer.toString(row.getInt("precmax")));
					
					if(row.getString("predias").isEmpty())
						price.setValid_week_day(null);
					else
						price.setValid_week_day(row.getString("predias"));
					
					if(row.getString("precanal").isEmpty()) 
						price.setRetailer_group(null);
					else 
						price.setRetailer_group(row.getString("precanal"));
					
					priceList.addPriceToProduct(priceProduct, price);
				
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(reader);
		}
		
		priceList.limpiarVacios();
		
		return priceList;
		
	}
	
	
	public void saveResponse(Price price, String ean, String response) {
		
		try {
			
			SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wbprecio.dbf"));
			
			writer.go(getNroRegistro(ean));	
							
			writer.setValue("prerespues", response);
			
			writer.setValue("pregrabo", 1);
			
			writer.setValue("preaudsync", formatAud.format(new Date()));
							
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(String ean) {
		
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
					
			if(row.getInt("pregrabo")==2 && row.getString("preean").equals(ean)) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	
	

}
