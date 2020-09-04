package com.next.fmg.syncro.main;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.next.fmg.syncro.controller.ControllerRetailers;
import com.next.fmg.syncro.controller.ControllerSalesOrder;

public class Application {

	public static String CLIENT_ID;
	public static String CLIENT_SECRET;
	public static String DIR_LECTURA_DBF;
	public static String DIR_LECTURA_JSON;
	public static String DIR_JSON;
	public static String DISTRIBUTOR_CODE;
	public static String DIR_DOWNLOAD_FTP;
	public static String DIR_LOCAL_DBF;
	
	public static String URL_RETAILERS;
	public static String URL_CATALOG;
	public static String URL_INVENTORY;
	public static String URL_PRICES;
	public static String URL_SALES_ORDER_UPDATE;
	
	public static String FTP_USER;
	public static String FTP_PASSWORD;
	public static String FTP_URL;
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		
		File fXmlFile = new File("Configuration.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		CLIENT_ID = doc.getElementsByTagName("CLIENT_ID").item(0).getTextContent();
		CLIENT_SECRET = doc.getElementsByTagName("CLIENT_SECRET").item(0).getTextContent();
		DIR_LECTURA_DBF = doc.getElementsByTagName("DIR_LECTURA_DBF").item(0).getTextContent();
		DIR_LECTURA_JSON = doc.getElementsByTagName("DIR_LECTURA_JSON").item(0).getTextContent();
		DIR_DOWNLOAD_FTP = doc.getElementsByTagName("DIR_DOWNLOAD_FTP").item(0).getTextContent();
		DIR_JSON = doc.getElementsByTagName("DIR_JSON").item(0).getTextContent();
		DISTRIBUTOR_CODE = doc.getElementsByTagName("DISTRIBUTOR_CODE").item(0).getTextContent();
		DIR_LOCAL_DBF = doc.getElementsByTagName("DIR_LOCAL_DBF").item(0).getTextContent();
		
		URL_RETAILERS = doc.getElementsByTagName("URL_RETAILERS").item(0).getTextContent();
		URL_CATALOG = doc.getElementsByTagName("URL_CATALOG").item(0).getTextContent();
		URL_INVENTORY = doc.getElementsByTagName("URL_INVENTORY").item(0).getTextContent();
		URL_PRICES = doc.getElementsByTagName("URL_PRICES").item(0).getTextContent();
		URL_SALES_ORDER_UPDATE = doc.getElementsByTagName("URL_SALES_ORDER_UPDATE").item(0).getTextContent();

		FTP_URL = doc.getElementsByTagName("FTP_URL").item(0).getTextContent();
		FTP_USER = doc.getElementsByTagName("FTP_USER").item(0).getTextContent();
		FTP_PASSWORD = doc.getElementsByTagName("FTP_PASSWORD").item(0).getTextContent();
		
		//Retailers va envio y recepción en un mismo jar
		////Envio retailers
		//ControllerRetailers.getInstance().postRetailers();
		////Recepción retailers
		//ControllerRetailers.getInstance().downloadRetailersFTP();
		//ControllerRetailers.getInstance().downloadRetailers();
		
		//Envio catalogo
		//ControllerCatalog.getInstance().postCatalog();
		
		//Envio inventario
		//ControllerInventory.getInstance().postInventory();
		
		//Envio precios
		//ControllerPrices.getInstance().postPrices();
		
		//Envio sales orders
		//ControllerSalesOrder.getInstance().postSalesOrder();
		
		//Recepción sales order
		//ControllerSalesOrder.getInstance().downloadSalesOrderFTP();
		//ControllerSalesOrder.getInstance().downloadSalesOrder();
		
		// NO SE USA, MAGENTO NO MANDA CAMBIOS DE ESTADO AL ERP
		//ControllerSalesOrder.getInstance().updateSalesOrder();
	
	
		System.out.println("End.");
		
	}

}
