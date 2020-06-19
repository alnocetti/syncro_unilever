package com.next.fmg.syncro.writer;

import java.io.File;

import com.linuxense.javadbf.DBFWriter;
import com.next.fmg.syncro.main.Application;
import com.next.fmg.syncro.model.RetailerAccount;

public class WriterRetailerAccount {

	public WriterRetailerAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void WriteResponse(RetailerAccount ra, String response) {
		
		DBFWriter writer = null;
		
		writer = new DBFWriter(new File(Application.DIR_LECTURA + "wbcuenta.dbf"));
		
	}

}
