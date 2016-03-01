<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.WarehouseOrder" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.apache.ws.axis2.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
// Supply stock to warehouses.
CheckOrdersStub obj1=new CheckOrdersStub();
CheckOrdersStub.UpdateStock updateStock2 = new CheckOrdersStub.UpdateStock();
obj1.updateStock(updateStock2);
Connection con = null;
Statement st = null;
ResultSet rs2 = null;

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
    System.out.println("verified");
			Statement st2 = con.createStatement();
	st = con.createStatement();
			String query2 = "update order_to_warehouse set status='X'";
			out.println("<html> <body> <h2> Supplier Receipt for updation of Stock to Warehouses </h2> <br/>");
 rs2 = st.executeQuery("SELECT DISTINCT(warehouse_id) from ordertosupplier");
int s=st2.executeUpdate(query2);
if(rs2!=null)
{
	while(rs2.next())
	{%> <br/> <% 
	// Acknowledgement that Warehousestock is updated.
	out.println(" Warehouse Stock updated for Warehouse ID : " + rs2.getString(1));
	}
}

}catch(Exception e)
{ e.printStackTrace();

}
%>
