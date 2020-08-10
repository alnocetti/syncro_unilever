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
import com.next.fmg.syncro.model.Inventory;
import com.next.fmg.syncro.model.StockProduct;

public class ReaderInventory {
	
	private DBFReader reader;
	public int cantidadLeida;

	public ReaderInventory() {
		super();
		this.cantidadLeida = 0;
		this.reader = null;
	}
	
	public Inventory readInventory(){
		
		Inventory inventory = new Inventory();
		
		reader = null;
		
		try {
			
			reader = new DBFReader(new FileInputStream(Application.DIR_LOCAL_DBF + "wbstock.dbf"));
		
			DBFRow row;
			
			while ((row = reader.nextRow()) != null) {
				
				if(row.getInt("stkgrabo") == 2) {

					StockProduct stockProduct = new StockProduct();
										
					stockProduct.setEAN(row.getString("stkean"));
					
					stockProduct.setStockitem_qty(Integer.toString(row.getInt("stkstock")));
					
					stockProduct.setMin_thersold_qty(Integer.toString(row.getInt("stkminimo")));
					
					stockProduct.setStockitem_is_in_stock(Integer.toString(row.getInt("stkinout")));
					
					inventory.addProduct(stockProduct);
					
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(reader);
		}
		
		return inventory;
		
	}
	
	
	public void saveResponse(StockProduct stockProduct, String response) {
		
		try {
			
			SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			XBaseFile writer = new XBase().open(new File(Application.DIR_LOCAL_DBF + "wbstock.dbf"));
			
			writer.go(getNroRegistro(stockProduct));	
							
			writer.setValue("stkrespues", response);
			
			writer.setValue("stkgrabo", 1);
			
			writer.setValue("stkaudsync", formatAud.format(new Date()));
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(StockProduct stockProduct) {
		
		DBFReader readerNroRegistro = null;
		
		int registro = 0;
		

		// create a DBFReader object
		try {
			readerNroRegistro = new DBFReader(new FileInputStream(Application.DIR_LOCAL_DBF + "wbstock.dbf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFRow row;

		while ((row = readerNroRegistro.nextRow()) != null) {
					
			if(row.getString("stkean").equals(stockProduct.getEAN()) && row.getInt("stkgrabo")==2) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	
	

}
