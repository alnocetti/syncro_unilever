package com.next.fmg.syncro.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.yasas.xbase4j.XBase;
import org.yasas.xbase4j.XBaseFile;

import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.Catalog;
import com.next.fmg.syncro.model.Product;

public class ReaderCatalog {

	private DBFReader reader;
	public int cantidadLeida;

	public ReaderCatalog() {
		super();
		this.cantidadLeida = 0;
		this.reader = null;
	}
	
	public Catalog readCatalog(){
		
		Catalog catalog = new Catalog();
		
		reader = null;
		
		try {
			
			reader = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbitems.dbf"));
		
			DBFRow row;
			
			while ((row = reader.nextRow()) != null) {
				
				if(row.getInt("itmgrabo") == 2) {

					Product product = new Product();
										
					product.setEAN(row.getString("itmean"));
					
					product.setName_prod(row.getString("itmdescrip"));
					
					product.setStatus_prod(Integer.toString(row.getInt("itmstatus")));
					
					product.setQty_ean_unit(row.getInt("itmcanuni"));
					
					product.setQty_min_sell(row.getInt("itmcanvta"));
					
					product.setIndustry_name(row.getString("itmindnom"));
					
					product.setIndustry_code(row.getString("itmindcod"));
					
					product.setShort_description(row.getString("itmdescor"));
					
					product.setDefault_price(row.getFloat("itmprecio"));
					
					product.setBrand(row.getString("itmmarca"));
					
					catalog.addProduct(product);
					
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(reader);
		}
		
		return catalog;
		
	}
	
	
	public void saveResponse(Product product, String response) {
		
		try {
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wbitems.dbf"));
			
			writer.go(getNroRegistro(product));	
							
			writer.setValue("itmrespues", response);
			
			writer.setValue("itmgrabo", 1);
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(Product product) {
		
		DBFReader readerNroRegistro = null;
		
		int registro = 0;
		

		// create a DBFReader object
		try {
			readerNroRegistro = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbitems.dbf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFRow row;

		while ((row = readerNroRegistro.nextRow()) != null) {
					
			if(row.getString("itmean").equals(product.getEAN()) && row.getInt("itmgrabo")==2) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	
	
}
