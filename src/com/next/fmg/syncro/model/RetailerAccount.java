package com.next.fmg.syncro.model;

public class RetailerAccount {
	
	/*
	 * Estado de retailers
	 * 0 = Descargado de magento, quedan en estado 0 los que se leen del json que recibo de magento
	 * 1 = ya leido, una vez que los leo los marco en estado 1
	 * 2 = como novedad para informar, solo se envían a magento los que están en estado 2
	 */
	
	private String distributor_code;
	private String property_name;
	private String store_id_ERP;
	private int Store_Status;
	private String rejection_reason;
	private String erp_seller;
	private String cuit_dni_id;
	private String document_type_id;
	private String ingresos_brutos;
	private String customer_type;
	private String street;
	private String number;
	private String Neighborhood;
	private String District;
	private String Province;
	private String country;
	private String postal_code;
	private String phone_number;
	private String mobile_number;
	private String email;
	private String firstname;
	private String surname;
	private float customer_credit_available;
	private float customer_total_credit;
	private float customer_pending_payment;
	
	
	public RetailerAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDistributor_code() {
		return distributor_code;
	}


	public void setDistributor_code(String distributor_code) {
		this.distributor_code = distributor_code;
	}


	public String getProperty_name() {
		return property_name;
	}


	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}


	public String getStore_id_ERP() {
		return store_id_ERP;
	}


	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
	}


	public int getStore_Status() {
		return Store_Status;
	}


	public void setStore_Status(int store_Status) {
		Store_Status = store_Status;
	}


	public String getRejection_reason() {
		return rejection_reason;
	}


	public void setRejection_reason(String rejection_reason) {
		this.rejection_reason = rejection_reason;
	}


	
	public String getErp_seller() {
		return erp_seller;
	}


	public void setErp_seller(String erp_seller) {
		this.erp_seller = erp_seller;
	}


	public String getCuit_dni_id() {
		return cuit_dni_id;
	}


	public void setCuit_dni_id(String cuit_dni_id) {
		this.cuit_dni_id = cuit_dni_id;
	}


	public String getDocument_type_id() {
		return document_type_id;
	}


	public void setDocument_type_id(String document_type_id) {
		this.document_type_id = document_type_id;
	}


	public String getIngresos_brutos() {
		return ingresos_brutos;
	}


	public void setIngresos_brutos(String ingresos_brutos) {
		this.ingresos_brutos = ingresos_brutos;
	}


	public String getCustomer_type() {
		return customer_type;
	}


	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getNeighborhood() {
		return Neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		Neighborhood = neighborhood;
	}


	public String getDistrict() {
		return District;
	}


	public void setDistrict(String district) {
		District = district;
	}


	public String getProvince() {
		return Province;
	}


	public void setProvince(String province) {
		Province = province;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostal_code() {
		return postal_code;
	}


	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getMobile_number() {
		return mobile_number;
	}


	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public float getCustomer_credit_available() {
		return customer_credit_available;
	}


	public void setCustomer_credit_available(float customer_credit_available) {
		this.customer_credit_available = customer_credit_available;
	}


	public float getCustomer_total_credit() {
		return customer_total_credit;
	}


	public void setCustomer_total_credit(float customer_total_credit) {
		this.customer_total_credit = customer_total_credit;
	}


	public float getCustomer_pending_payment() {
		return customer_pending_payment;
	}


	public void setCustomer_pending_payment(float customer_pending_payment) {
		this.customer_pending_payment = customer_pending_payment;
	}
	
	
	

}
