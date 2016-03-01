package org.apache.ws.axis2;



public class WarehouseOrder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int order_ID;
	private int warehouse_ID;
	private Product[] pro=new Product[50];
	private String OrderDate;
	private int ShipmentID;
	public int getOrder_ID() {
		return order_ID;
	}
	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}
	public int getWarehouse_ID() {
		return warehouse_ID;
	}
	public void setWarehouse_ID(int warehouse_ID) {
		this.warehouse_ID = warehouse_ID;
	}
	public Product[] getPro() {
		return pro;
	}
	public void setPro(Product[] pro) {
		this.pro = pro;
	}
	public String getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public int getShipmentID() {
		return ShipmentID;
	}
	public void setShipmentID(int shipmentID) {
		ShipmentID = shipmentID;
	}
	
}
