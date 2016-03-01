<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.StoreToWarehouseOrder" %>


 <%@ page import="java.sql.*" %>
 
 <%
 
//JSP page to update retail store stock.

 class Product {

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

 CheckOrdersStub obj1=new CheckOrdersStub();
	GetOrders getOrders0=new GetOrders();
	getOrders0.setWarehouseId(1);
CheckOrdersStub.StoreToWarehouseOrder[] ar= obj1.getOrders(getOrders0).get_return();

Connection con = null;
Statement stmt = null;
ResultSet rs = null;

try {
	String pwd1=null;
	System.out.println("into try");
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("connected");
	String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
	System.out.println("into connect");
	String username = "root";
    String password = "ashwin92";
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
			Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory","root","ashwin92");
			Statement st2 = con2.createStatement();
			Statement st3 = con.createStatement();
    System.out.println("verified");
	stmt = con.createStatement();
			//SQL Query to update retail store stock for specific Retail Stores based on orders in warehouse.
			for( int i=0; ar[i]!=null; i++)
			{	
				String query ="update retail_store_stock set stock_left=stock_left + "+ar[i].getQty()+ " AND STORE_ID="+ar[i].getStoreId();
				String query2="update warehousestock set stock_left=stock_left - "+ar[i].getQty() + " AND WAREHOUSE_ID="+ar[i].getWarehouseId()+" AND PRODUCT_ID='"+ar[i].getProductId()+"'";
				String query3="update order_to_warehouse set status='X' WHERE STORE_ID="+ar[i].getStoreId();
				stmt.executeUpdate(query);
				st2.executeUpdate(query2);
				st3.executeUpdate(query3);
				out.print("Stock updated for Store with StoreID :" + ar[i].getStoreId());
			}
			
			
}catch(Exception e) {
	e.printStackTrace();
}
%>	
 