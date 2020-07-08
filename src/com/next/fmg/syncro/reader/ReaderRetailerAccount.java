package com.next.fmg.syncro.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.yasas.xbase4j.XBase;
import org.yasas.xbase4j.XBaseFile;

import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFRow;
import com.linuxense.javadbf.DBFUtils;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.RetailerAccount;

public class ReaderRetailerAccount {
	
	private DBFReader reader;
	public int cantidadLeida;

	public ReaderRetailerAccount() {
		super();
		this.cantidadLeida = 0;
		this.reader = null;
	}
	
	public List<RetailerAccount> readRetailers(){
		
		List<RetailerAccount> retailers = new ArrayList<RetailerAccount>();
		
		reader = null;
		
		try {
			
			reader = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbcuenta.dbf"));
		
			DBFRow row;
			
			while ((row = reader.nextRow()) != null) {
				
				if(row.getInt("CLIGRABO") == 2) {

					RetailerAccount retailer = new RetailerAccount();
					
					retailer.setDistributor_code(row.getString("CLICODDIS"));
					
					retailer.setProperty_name(row.getString("CLIRAZSOC"));
					
					retailer.setStore_id_ERP(row.getString("CLIENVNEXT"));
					
					retailer.setStore_Status(Integer.toString(row.getInt("CLIENVSTAT")));
					
					retailer.setRejection_reason(row.getString("CLIENVMOTI"));
					
					retailer.setCuit_dni_id(row.getString("CLICUIT"));
					
					retailer.setDocument_type_id(row.getString("CLITABCUIT"));
					
					retailer.setIngresos_brutos(row.getString("CLIIIBB"));
					
					retailer.setCustomer_type(row.getString("CLITABTIPO"));
					
					retailer.setStreet(row.getString("CLIDOMICI"));
					
					retailer.setNumber(row.getString("CLIALTURA"));
					
					retailer.setNeighborhood(row.getString("CLILOCALI"));
					
					retailer.setDistrict(row.getString("CLIPARTIDO"));
					
					retailer.setProvince(row.getString("CLIPROVIN"));
					
					retailer.setCountry(row.getString("CLIPAIS"));
					
					retailer.setPostal_code(row.getString("CLIPOSTAL"));
					
					retailer.setPhone_number(row.getString("CLITELEF"));
					
					retailer.setMobile_number(row.getString("CLITELEX"));
					
					retailer.setEmail(row.getString("CLIMAIL"));
					
					retailer.setFirstname(row.getString("CLINOMBRE"));
					
					retailer.setSurname(row.getString("CLIAPELLI"));
					
					retailer.setCustomer_credit_available(row.getFloat("CLIENVCRDA"));
					
					retailer.setCustomer_total_credit(row.getFloat("CLIENVCRDT"));
					
					retailer.setCustomer_pending_payment(row.getFloat("CLIENVPAYM"));
					
					retailer.setErp_seller(row.getString("CLIPREVEN"));
					
					retailers.add(retailer);
				}
				
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			DBFUtils.close(reader);
		}
		
		return retailers;
		
	}
	
	
	public void saveResponse(RetailerAccount ra, String response) {
		
		try {
			
			XBaseFile writer = new XBase().open(new File(Application.DIR_LECTURA_DBF + "wbcuenta.dbf"));
			
			writer.go(getNroRegistro(ra));	
							
			writer.setValue("CLIRESPUES", response);
			
			writer.setValue("CLIGRABO", 1);
											
			writer.closeQuietly();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public int getNroRegistro(RetailerAccount ra) {
		
		DBFReader readerNroRegistro = null;
		
		int registro = 0;
		

		// create a DBFReader object
		try {
			readerNroRegistro = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbcuenta.dbf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBFRow row;

		while ((row = readerNroRegistro.nextRow()) != null) {
					
			if(row.getString("CLIENVNEXT").equals(ra.getStore_id_ERP()) && row.getString("CLICUIT").equals(ra.getCuit_dni_id())) {
				
				return registro;
				
			}
			
			registro ++;

		}		
		
		DBFUtils.close(readerNroRegistro);

		return registro;
		
	}
	

}
