<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.WarehouseOrder" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.apache.ws.axis2.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Welcome page after verifying login information  -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Store Manager</title>
</head>
<body>
<%  String usn=(String) request.getAttribute("user"); 
	System.out.println("\n inside welcome");
	out.println("<html><body><h3>Welcome "+usn+"</h3></body></html>");
	out.println("<h4>Tracking Product Requests from Warehouse</h4>");
	CheckOrdersStub obj1=new CheckOrdersStub();
	GetOrders getOrders0=new GetOrders();
		
	//Calling Webservice to get order reuqests from warehouses.
	CheckOrdersStub.WarehouseOrder[] ar= obj1.getOrders(getOrders0).get_return();
	System.out.println("Display Array");
	
	
    out.println("The following are the requests from Warehouses <br/>");
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
    	st = con.createStatement();
     rs2 = st.executeQuery("SELECT COUNT(DISTINCT(ORDER_ID)) from ordertosupplier where status='P'");
	if(rs2!=null){
		rs2.next();
	out.println("\n You have " + rs2.getString(1) + " Requests for supplies");
			
	 Connection con2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory", "root", "ashwin92");
	 Statement st2 = con2.createStatement();
	 
	%>
	<!-- Page displaying all orders present in supplier from various warehouses  -->
	<table border=1> <%
	for( int i=0; i<10; i++)
	{ 
		
		if(ar[i].getOrder_ID()!=0){
	%>
		<tr> <th> Warehouse ID :<%=ar[i].getWarehouse_ID() %> </th> </tr>
		
		<tr><td> ProductID </td> <td> Product Name </td> <td> Quantity Requested </td> 
		</tr>
		
		<%
		CheckOrdersStub.Product[] temp= ar[i].getPro();
		
		for( int j=0; temp[j]!=null; j++)
		{
			
			%> <tr><td><%=temp[j].getProduct_ID()%></td> <% 
					
			ResultSet rs = st2.executeQuery("select product_name from product where product_ID='"+temp[j].getProduct_ID()+"'"); 
			rs.next();%>
			<td><%=rs.getString(1) %> </td> <td> <%=temp[j].getQuantity() %> </tr><%
		}
		%> <br/> <br/> <%
	}
	
	}
	%>

	
	 <%	
	}
	
	else
		out.println("No Requests");
	}catch(Exception e)
	{ e.printStackTrace(); }
%>
</table>
<!-- Form to request products to supplier -->
	<p> Click the button below to supply Stock to the Warehouses :  </p>
	<br/>
<form action="supplystock.jsp">
	<input type="submit" name="submit" value="Supply stock to all the Warehouse Orders">
	</form>
</body>
</html>