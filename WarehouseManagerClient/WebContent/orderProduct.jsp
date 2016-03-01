<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.StoreToWarehouseOrder" %>


 <%@ page import="java.sql.*" %>
 <%
 
//JSP page to reset stock to retail stores

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

 	System.out.println("Step 1");
 // Calling a webservice to check orders of retail stores present for warehouses. 
 CheckOrdersStub obj1=new CheckOrdersStub();
	GetOrders getOrders0=new GetOrders();
	getOrders0.setWarehouseId(1);
	System.out.println("Step 2");
CheckOrdersStub.StoreToWarehouseOrder[] ar= obj1.getOrders(getOrders0).get_return();
 out.println("The following products are requested by the stores <br/>");
 System.out.println(ar[0].getProductId());
 int i=0;
 	while(ar[i]!=null)
 {
	 System.out.println("\n Step 3");
 	if(ar[i]!=null)
 	{
 		CheckOrdersStub.StoreToWarehouseOrder x= ar[i];
 		out.println("<br/> ProductID: "+x.getProductId());
 		//out.println("<html><body><form name='form1' action='orderProduct.jsp' method='post'><a href='orderProduct.jsp'>Click here to order product this product</a></body></html>");
 		//out.println("<html><body><a href='orderProduct.jsp?param="+x+"'>click</a><br/></body></html>");
 	}
 	
 	else {}
 	i++;
 }
 out.println("<html> <body> <a href='order2.jsp'> Click here to Order all the products </a> <br/> </body></html>");
%>