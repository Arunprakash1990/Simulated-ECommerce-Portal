<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.StoreToWarehouseOrder" %>


 <%@ page import="java.sql.*" %>
 
 <%
 

//JSP page to acknowledge that order to supplier has been placed.
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
 Connection con = null;
 Statement stmt = null;
 ResultSet rs = null;
try {
	
	System.out.println("into try");
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("connected");
	String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
	System.out.println("into connect");
	String username = "root";
    String password = "ashwin92";
			String s1= (String)session.getAttribute("warehouseID");
    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
			Statement st = con.createStatement();
			String query = "select * from warehousestock where warehouse_id="+s1+" AND STOCK_LEFT<200";
			 Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
			Statement st2 = con2.createStatement();
			String query3;
			rs = st.executeQuery(query);
			String query2;
			System.out.println("executing query");
			Product s = new Product();
			int q = s.hashCode();
		while(rs.next())
		{
				System.out.println("iteration");
			query2="insert into ordertosupplier(order_ID,warehouse_id,product_id,quantity,price,orderdate,shipment_ID,status) values("+q+","+rs.getString(1)+",'"+rs.getString(2)+"',200,20,'2014-06-10',2024,'P')";
			st2.executeUpdate(query2);
		}
		
		
		System.out.println("Outside Rs.next()");
		//Retrieve Order being placed and show acknowledgement.
		ResultSet rs3 = st.executeQuery("select order_ID from ordertosupplier where warehouse_ID='"+s1+"' order by ORDER_ID desc limit 1");
		rs3.next();
		%> <html> <body> <p> Order placed for Warehouse with order ID:<%=rs3.getString(1) %> </p> </body></html>
			<% 
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		
		%>