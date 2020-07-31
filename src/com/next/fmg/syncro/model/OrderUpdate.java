package com.next.fmg.syncro.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderUpdate {
	
	private String order_source;
	private String order_id;
	private String store_id_ERP;
	private String erp_order_id;
	private String updated_at;
	private String total_qty_ordered;
	private String discount_amount;
	private String grand_total;
	private String subtotal;
	private String total_item_count;
	private String total_due;
	private String status;
	private List<OrderItem> items;
	private Map<String, String> items_changed;
	private OrderPayment payment;
	private OrderShipping shipping;
	private String shipment_type;
	private String payment_pending_amount;
	private String cancellation_reason;
	private String order_delivered_at;

	
	public OrderUpdate() {
		super();
		this.items = new ArrayList<OrderItem>();
		this.items_changed = new HashMap<String, String>();
		// TODO Auto-generated constructor stub
	}

	
	public String getOrder_source() {
		return order_source;
	}


	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}


	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getStore_id_ERP() {
		return store_id_ERP;
	}


	public void setStore_id_ERP(String store_id_ERP) {
		this.store_id_ERP = store_id_ERP;
	}


	public String getErp_order_id() {
		return erp_order_id;
	}


	public void setErp_order_id(String erp_order_id) {
		this.erp_order_id = erp_order_id;
	}


	public String getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}


	public String getTotal_qty_ordered() {
		return total_qty_ordered;
	}


	public void setTotal_qty_ordered(String total_qty_ordered) {
		this.total_qty_ordered = total_qty_ordered;
	}


	public String getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(String discount_amount) {
		this.discount_amount = discount_amount;
	}


	public String getGrand_total() {
		return grand_total;
	}


	public void setGrand_total(String grand_total) {
		this.grand_total = grand_total;
	}


	public String getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}


	public String getTotal_item_count() {
		return total_item_count;
	}


	public void setTotal_item_count(String total_item_count) {
		this.total_item_count = total_item_count;
	}


	public String getTotal_due() {
		return total_due;
	}


	public void setTotal_due(String total_due) {
		this.total_due = total_due;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<OrderItem> getItems() {
		return items;
	}


	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public OrderPayment getPayment() {
		return payment;
	}


	public void setPayment(OrderPayment payment) {
		this.payment = payment;
	}


	public OrderShipping getShipping() {
		return shipping;
	}


	public void setShipping(OrderShipping shipping) {
		this.shipping = shipping;
	}


	public String getPayment_pending_amount() {
		return payment_pending_amount;
	}


	public void setPayment_pending_amount(String payment_pending_amount) {
		this.payment_pending_amount = payment_pending_amount;
	}


	public String getCancellation_reason() {
		return cancellation_reason;
	}


	public void setCancellation_reason(String cancellation_reason) {
		this.cancellation_reason = cancellation_reason;
	}


	public String getOrder_delivered_at() {
		return order_delivered_at;
	}


	public void setOrder_delivered_at(String order_delivered_at) {
		this.order_delivered_at = order_delivered_at;
	}

	

	public String getShipment_type() {
		return shipment_type;
	}


	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}


	public void addItem(OrderItem item) {
		this.items.add(item);
	}


	public Map<String, String> getItems_changed() {
		return items_changed;
	}


	public void setItems_changed(Map<String, String> items_changed) {
		this.items_changed = items_changed;
	}
	
	public void addItemChanged(String ean, String reason) {
		this.items_changed.put(ean, reason);
	}
	
}
