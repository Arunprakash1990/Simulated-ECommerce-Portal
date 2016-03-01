
package org.apache.ws.axis2;
public class Product {

	private String product_ID;
	private int quantity;
	private int price;
	public String getProduct_ID() {
		return product_ID;
	}
	public void setProduct_ID(String product_ID) {
		this.product_ID = product_ID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
        String display = " Product ID : " + product_ID + " \n Quantity : " + quantity + " \n Price : " + price + " \n\n\n";
        return display;
    }
	
}



