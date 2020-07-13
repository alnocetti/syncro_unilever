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
import com.next.fmg.syncro.model.RetailerAccount;

public class WriterRetailerAccount {

	public WriterRetailerAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void downloadRetaliers() throws IOException {
		
		
		//Read json into array
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		SimpleDateFormat formatAud = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		String file = Application.DIR_DOWNLOAD_FTP + "Retailers/" + format.format(new Date()) + "_Retailer.json";
		
		List<RetailerAccount>retailers = new ArrayList<RetailerAccount>();
		
		Type token = new TypeToken<ArrayList<RetailerAccount>>() {}.getType();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
		
		String line = null;
		
		StringBuilder json = new StringBuilder();
		
		while((line = br.readLine()) != null){

			json = json.append(line);

	    }
		
		br.close();
		
		//JsonReader reader = new JsonReader(new FileReader(file));
		
		retailers = gson.fromJson(json.toString(), token);
		
		if(retailers == null) {
			System.out.println("Nothing to download");
			return;
		}
		
		// Write records into dbf
	
		DBFWriter writer = null;
		
		for(RetailerAccount retailer : retailers) {
			
			if(!existeRetailer(retailer)) {
				
				System.out.println("<-- getRetailerAccount(" + retailer.getProperty_name() + ")");
				
				writer = new DBFWriter(new File(Application.DIR_LECTURA_DBF + "wbcuenta.dbf"));
				
				Object rowData[] = new Object[31];
				
				rowData[0] = retailer.getDistributor_code();
				rowData[1] = retailer.getProperty_name();
				rowData[2] = retailer.getCuit_dni_id();
				rowData[3] = "";
				rowData[4] = Integer.valueOf(retailer.getDocument_type_id());
				rowData[5] = retailer.getIngresos_brutos();
				rowData[6] = "";
				rowData[7] = Integer.valueOf(retailer.getCustomer_type());
				rowData[8] = retailer.getStreet();
				rowData[9] = retailer.getNumber();
				rowData[10] = retailer.getNeighborhood();
				rowData[11] = retailer.getDistrict();
				rowData[12] = retailer.getProvince();
				rowData[13] = retailer.getCountry();
				rowData[14] = retailer.getPostal_code();
				rowData[15] = retailer.getPhone_number();
				rowData[16] = retailer.getMobile_number();
				rowData[17] = retailer.getEmail();
				rowData[18] = retailer.getFirstname();
				rowData[19] = retailer.getSurname();
				rowData[20] = retailer.getStore_id_ERP();
				rowData[21] = 0;
				rowData[22] = "";
				rowData[23] = retailer.getCustomer_credit_available();
				rowData[24] = retailer.getCustomer_total_credit();	
				rowData[25] = retailer.getCustomer_pending_payment();
				rowData[26] = 0;
				rowData[27] = "";
				rowData[28] = retailer.getErp_seller();
				rowData[29] = "";
				rowData[30]	= formatAud.format(new Date());	
				writer.addRecord(rowData);
				
				writer.close();
				
			}
			
		}
		
		return;
		
	}
	
	private boolean existeRetailer(RetailerAccount retailer) throws FileNotFoundException {
		
		DBFReader reader = new DBFReader(new FileInputStream(Application.DIR_LECTURA_DBF + "wbcuenta.dbf"));
		
		DBFRow row;
		
		while ((row = reader.nextRow()) != null) {
			if(
//			   row.getString("CLICODDIS").equals(retailer.getDistributor_code()) &&
//			   row.getString("CLIRAZSOC").equals(retailer.getProperty_name()) &&
			   row.getString("CLICUIT").equals(retailer.getCuit_dni_id()) &&
			   row.getInt("CLITABCUIT")==Integer.valueOf(retailer.getDocument_type_id()) &&
//			   row.getString("CLIIIBB").equals(retailer.getIngresos_brutos()) &&
//			   row.getString("CLIDOMICI").equals(retailer.getStreet()) &&
			   row.getString("CLIALTURA").equals(retailer.getNumber()) &&		   
//			   row.getString("CLILOCALI").equals(retailer.getNeighborhood()) &&
//			   row.getString("CLIPARTIDO").equals(retailer.getDistrict()) &&
//			   row.getString("CLIPROVIN").equals(retailer.getProvince()) &&
//			   row.getString("CLIPAIS").equals(retailer.getCountry()) &&
			   row.getString("CLIPOSTAL").equals(retailer.getPostal_code()) &&
			   row.getString("CLITELEF").equals(retailer.getPhone_number()) &&
			   row.getString("CLITELEX").equals(retailer.getMobile_number()) &&
			   row.getString("CLIMAIL").equals(retailer.getEmail())
//			   row.getString("CLINOMBRE").equals(retailer.getFirstname()) &&
//			   row.getString("CLIAPELLI").equals(retailer.getSurname())
					) {
				
				return true;
				
			}
			
		}
		
		return false;
	}

}
