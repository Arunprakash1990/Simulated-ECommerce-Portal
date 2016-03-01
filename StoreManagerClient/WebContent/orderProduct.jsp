<%@page import="org.apache.ws.axis2.OrderToWarehouseStub.OrderToWarehouse"%>
<%@page import="org.apache.ws.axis2.OrderToWarehouseStub"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!--  JSP Page to place order to warehouse for renewal stock in Retail Store -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%	String prodID=request.getParameter("param");
	String sid=request.getParameter("param1");
	System.out.println(sid);
	int storeID=Integer.parseInt(sid);
	out.println("Hello!");
	out.println("You are placing an order to your associated warehouse for the productID: " +prodID+"..");
	out.println("<html><body></br></br></body></html>");
	int wID=0;
	Connection con=null;
	Statement st=null;
	Statement stmt=null;
	ResultSet rs = null;
	String result=null;	
	try {
		//Database connectivity to connect to Retail Store database.
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/inventory";
		String username = "root";
        String password = "ashwin92";
        con = DriverManager.getConnection(connectionUrl,username,password);
        st = con.createStatement();
        stmt=con.createStatement();
        con.setAutoCommit(false);
		rs = st.executeQuery("SELECT WAREHOUSE_ID FROM RETAIL_STORE WHERE STORE_ID="+storeID+";");
			while (rs.next()) {
				wID=rs.getInt("WAREHOUSE_ID");
	    	}
			
			//Call to Web Service to place order to warehouse
		OrderToWarehouseStub obj=new OrderToWarehouseStub();
		OrderToWarehouse orderToWarehouse0=new OrderToWarehouse();
		orderToWarehouse0.setProductID(prodID);
		orderToWarehouse0.setStoreID(storeID);
		orderToWarehouse0.setWarehouseID(wID);
		orderToWarehouse0.setQty(200);
		result=obj.orderToWarehouse(orderToWarehouse0).get_return();
		out.println(result);
		} 
	
	catch (Exception e) {
		e.printStackTrace();
		} 

	finally {
		try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (st != null) st.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
	}

%>
</body>
</html>