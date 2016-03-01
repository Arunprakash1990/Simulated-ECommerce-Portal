<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub" %>
<%@ page import="org.apache.ws.axis2.CheckOrdersStub.GetOrders" %>
<%@ page import="org.apache.ws.axis2.StoreToWarehouseOrder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Store Manager</title>
</head>
<body>
<!-- Profile Welcome page for Warehouse Manager after logging in -->
<%  String usn=(String) request.getAttribute("user"); 
System.out.println("WarehouseID " + session.getAttribute("warehouseID"));
	session.setAttribute("user",usn);
	out.println("<html><body><h3>Welcome "+usn+"</h3></body></html>");
	out.println("<h4>Tracking product stock..</h4>");
	%>
	
	<label> What would you like to do? </label>
	<br/>
	<!--  Options to check supply request from stores or to request stock to supplier -->
	 Check Supply Request from Stores
	<form name="form1" action="orderProduct.jsp" method="get">
	<input type="submit" name="submit" value="Check Supply Request from Stores">
	</form>
	
	<br/>
	<br/>
	<br/>
	<br/>
	 Request stock to Supplier
	<form name="form2" action="RequestStock.jsp" method="get">
	<input type="submit" name="submit" value="Request stock to Supplier">
	</form>
</body>
</html>