package com.next.fmg.syncro.main;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.next.fmg.syncro.controller.ControllerRetailers;

public class Application {

	public static String CLIENT_ID;
	public static String CLIENT_SECRET;
	public static String DIR_LECTURA_DBF;
	public static String DIR_LECTURA_JSON;
	public static String DIR_JSON;
	
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
		DIR_JSON = doc.getElementsByTagName("DIR_JSON").item(0).getTextContent();
		
		//TEST POST RETAILER ACCOUNT
		ControllerRetailers.getInstance().postRetailers();
		ControllerRetailers.getInstance().downloadRetailers();
		
	}

}
